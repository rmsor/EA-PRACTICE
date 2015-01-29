/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.dao;

import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author noman-pc
 */
@Stateless
public class EmailSender {

    @Asynchronous
    public void sendEmail(String msg, String subject, String toEmail, String fromEmail)
            throws Exception {
        String toEmails[] = toEmail.split(",");
        System.out.println("TO:::FROM:::SUBJ:::BODY:::" + toEmail + "-" + fromEmail + "-" + subject + "-" + msg);

        Session session = setSessionAuthentication();
        InternetAddress from = new InternetAddress(fromEmail);
        InternetAddress to[] = new InternetAddress[toEmails.length];
        for (int c = 0; c < toEmails.length; c++) {
            to[c] = new InternetAddress(toEmails[c]);
        }
        MimeMessage message = new MimeMessage(session);
        message.setFrom(from);
        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject(subject);
        message.setText(msg);
        Transport.send(message);
    // msg="OK Msg Posted Successfully";
        //return "EMail Sent Successfully";
    }

    /**
     *
     * @return @throws Exception
     */
    public Session setSessionAuthentication()
            throws Exception {
        final String username = "uscabimail@gmail.com";
        final String password = "uscabi12345";
    //Using SSL
        //Properties props = new Properties();
        //props.put("mail.smtp.host", "smtp.gmail.com");
        //props.put("mail.smtp.socketFactory.port", "465");
        //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.port", "465");
        // USING TLS
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.debug", "true");
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.socketFactory", sf);
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

}
