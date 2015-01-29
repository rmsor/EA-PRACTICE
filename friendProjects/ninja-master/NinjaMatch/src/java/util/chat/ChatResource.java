/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.chat;

/**
 *
 * @author FAlegre
 */
import java.io.Serializable;
import org.primefaces.push.EventBus;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnClose;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PathParam;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContext;

@PushEndpoint("/{room}/{user}")
@Singleton
public class ChatResource implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(ChatResource.class);

    @PathParam("room")
    private String room;

    @PathParam("user")
    private String username;

    @Inject
    private ServletContext ctx;

    @OnOpen
    public void onOpen(RemoteEndpoint r, EventBus eventBus) {
        System.out.println("=============" + r.toString());
        System.out.println("=============" + eventBus.toString());
        logger.info("OnOpen {}", r);

            eventBus.publish(room + "/*", new Message(String.format("%s has entered the room '%s'", username, room), true));

    }

    @OnClose
    public void onClose(RemoteEndpoint r, EventBus eventBus) {
        ChatUsers users = (ChatUsers) ctx.getAttribute("chatUsers");
        users.remove(username);

        eventBus.publish(room + "/*", new Message(String.format("%s has left the room", username), true));
    }

    @OnMessage(decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
    public Message onMessage(Message message) {
        return message;
    }

}
