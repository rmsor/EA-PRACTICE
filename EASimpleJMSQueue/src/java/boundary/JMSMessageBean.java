/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boundary;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Steve
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myQueue")
})
public class JMSMessageBean implements MessageListener {
    
    public JMSMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        // if we want to see the text message that came from the queue use;
        //TextMessage text =(TextMessage) message;
        //text.getText()
        System.out.println("receiving the message " + message + " in JMSMessageBean");
    }
    
}
