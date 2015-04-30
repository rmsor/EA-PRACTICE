package ejb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Protocol;
import model.User;

/**
 *
 * @author xtrememe
 */
@Stateless
public class EmailSessionBean {
    @EJB
    private UserFacade userFacade;
    private User user;
    /**
     * port 587 for TLS/STARTTLS
     * port 465 for SSL
     */
    private int port = 465;
    private String host = "smtp.gmail.com";
    private String from = "mmaharjan@mum.edu";
    private boolean auth = true;
    private String username = "mmaharjan@mum.edu";
    private String password = "CatsAndD0gs";
    private Protocol protocol = Protocol.SMTPS;
    private boolean debug = true;

    
    /**
     * Sends an email as designated by the to params using the Gmail SMTP.
     * 
     * @params to Email id to which to send an email
     */
    public boolean sendEmail(String to){
        boolean flag = false;
        String subject = "New Password - FindRoomie";
        String body = "New Password for you FindRoomie account: " + generateNewPassword();
                
        
        // update the password in the table
        user = userFacade.findByEmail(to);

        if(user != null){
            
            System.out.println("user is not null");
            
            user.setPassword(encryptToSha256(generateNewPassword()));
            userFacade.edit(user);
        
        
                
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
        
            switch(protocol){
                case SMTPS:
                    props.put("mail.smtp.ssl.enable", true);
                    break;
                case TLS:
                   props.put("mail.smtp.starttls.enable", true);
                    break;
            }
        
        
            Authenticator authenticator = null;
        
            if (auth) {
                props.put("mail.smtp.auth", true);
                    authenticator = new Authenticator() {
                        private PasswordAuthentication pa = new PasswordAuthentication(username, password);

                        @Override
                        public PasswordAuthentication getPasswordAuthentication() {
                        return pa;
                    }
                };
            }

            Session session = Session.getInstance(props, authenticator);
            session.setDebug(debug);

            MimeMessage message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                message.setRecipients(Message.RecipientType.TO, address);
                message.setSubject(subject);
                message.setSentDate(new Date());
                message.setText(body);

                Transport.send(message);

                flag = true;
                
            } catch (MessagingException ex) {

                Logger.getLogger(EmailSessionBean.class.getName()).log(Level.SEVERE, null, ex);

                flag = false;
            }
        }
        
        return flag;
        
    }
    
    
    private String generateNewPassword(){
        // TODO Need an elaborate algorithm to randomly generate a strong password
        String password = "Str0ngPwd@@";
        
        return password;
    }
    
    
    
    /**
     * Produces a SHA-256 hashing of a string and then converts the bytes[] of 
     * that operation to a Base64 encoding.
     *
     * @params password String to be encoded
     */
    public String encryptToSha256(String password){
        String encodedPwd = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            
            byte[] byteData = md.digest();
            
            // converting the byte to Base64 format
            encodedPwd = new String(Base64.getEncoder().encode(byteData));
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EmailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return encodedPwd;
    }
}
