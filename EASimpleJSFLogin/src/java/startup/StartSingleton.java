/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package startup;

import boundary.UserFacade;
import entities.User;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
@Singleton
@Startup
public class StartSingleton {
    //private Message myMessage;
    private User myUser;
    //@Inject
    //private MessageFacade myMessageFacade;
    @Inject
    private UserFacade myUserFacade;
    private String myMessageString;
    private String emailAddress;

    @PostConstruct
    private void initApp(){
        System.out.println("StartSingleton in initApp()");
        for (int i=0; i < 5; i++){
       //myMessage = new Message();
       myUser = new User();
       emailAddress = "user" + i +"@mum.edu";
       myUser.setEmailAddress(emailAddress);
       myUser.setFirstName("firstName"+ i);
       myUser.setLastName("lastName"+ i);
       //myUser.addMessageToRcvList(myMessage);
       //myUser.addMessageToSentList(myMessage);
       this.myUserFacade.create(myUser);
       
       //myMessageString = "fromStartSingleton" + i;
       //myMessage.setMyTextMessage(myMessageString);
      //this.myMessageFacade.create(myMessage);
       
            
        }
        
        
    }
}
