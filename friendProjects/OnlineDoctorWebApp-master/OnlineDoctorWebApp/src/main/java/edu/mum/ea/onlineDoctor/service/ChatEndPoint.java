/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.service;

/**
 *
 * @author Fetiya
 */
import edu.mum.ea.onlineDoctor.serviceI.AppointmentServiceBeanLocal;
import edu.mum.ea.onlineDoctor.serviceI.MedicalRecordingServiceBeanLocal;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.CloseReason;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{room}", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class ChatEndPoint {

    private final Logger log = Logger.getLogger(getClass().getName());
    private Hashtable OngoingchatMessages = new Hashtable();

    @Inject
    private MedicalRecordingServiceBeanLocal medicalRecordingService;

    @Inject
    private AppointmentServiceBeanLocal appointmentService;

    @OnOpen
    public void open(final Session session, @PathParam("room") final String room) {
        log.info("session openend and bound to room: " + room);

//        try {=
//            for (Session s : session.getOpenSessions()) {
//                if (s.isOpen() && room.equals(s.getUserProperties().get("room"))) {
//                    System.out.println("Message is getting sentttttttttttttttt");
////                    String messages = (String) OngoingchatMessages.get(room);
////                    if (messages != null) {
////                        for (String message : (messages).split("\n")) {
////                            s.getBasicRemote().sendObject(message);
////                        }
////                        
////                    }
//                }
//            }
//        } catch (IOException | EncodeException e) {
//            log.log(Level.WARNING, "onMessage failed", e);
//        }
//        try {
//            for (Session s : session.getOpenSessions()) {
//                if (s.isOpen() && room.equals(s.getUserProperties().get("room"))) {
//                    System.out.println("Message is getting sentttttttttttttttt");
////                    String messages = (String) OngoingchatMessages.get(room);
////                    if (messages != null) {
////                        for (String message : (messages).split("\n")) {
////                            s.getBasicRemote().sendObject(message);
////                        }
////                        return;
////                    }
//                }
//            }
//        } catch (IOException | EncodeException e) {
//            log.log(Level.WARNING, "onMessage failed", e);
//        }
        session.getUserProperties().put("room", room);
        OngoingchatMessages.put(room, "");
    }

    @OnMessage
    public void onMessage(final Session session, final ChatMessage chatMessage) {
        System.out.println("Message arrived----------------------" + chatMessage.getMessage());
        ChatMessageEncoder encoder = new ChatMessageEncoder();
        String room = (String) session.getUserProperties().get("room");
        try {
            for (Session s : session.getOpenSessions()) {
                System.out.println("Session");
                if (s.isOpen() && room.equals(s.getUserProperties().get("room"))) {
                    System.out.println("Message is getting sentttttttttttttttt");
                    s.getBasicRemote().sendObject(chatMessage);
                    String messages = (String) OngoingchatMessages.get(room);
                    OngoingchatMessages.replace(room, messages + "\n" + encoder.encode(chatMessage));
                    return;
                }

            }
            String messages = (String) OngoingchatMessages.get(room);
            OngoingchatMessages.replace(room, messages + "\n" + encoder.encode(chatMessage));

        } catch (IOException | EncodeException e) {
            log.log(Level.WARNING, "onMessage failed", e);
        }
    }

    @OnClose
    public void close(final Session session, CloseReason closeReason) {
        log.info(String.format("Session %s closed because of %s", session.getId(), closeReason));

        String room = (String) session.getUserProperties().get("room");
        try {
            for (Session s : session.getOpenSessions()) {
                System.out.println("Close Session");
                if (s.isOpen() && room.equals(s.getUserProperties().get("room")) && s != session) {
                    System.out.println("session notified to be closed");
                    s.close(closeReason);
                }
            }

        } catch (Exception e) {
            log.log(Level.WARNING, "closing the other side session failed");
        } finally {
            //persisting the chat messages
            medicalRecordingService.recordchatMessages(appointmentService.findAppointmentByID(Long.parseLong(room)), (String) OngoingchatMessages.get(Long.parseLong(room)));

        }
    }
}
