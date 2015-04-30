/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package startup;

import boundary.MessageFacade;
import entities.Message;
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
    private Message myMessage;
    @Inject
    private MessageFacade myMessageFacade;
    private String myMessageString;

    @PostConstruct
    private void initApp(){
        System.out.println("StartSingleton in initApp()");
        for (int i=0; i < 5; i++){
       myMessage = new Message();
       myMessageString = "fromStartSingleton" + i;
       myMessage.setMyTextMessage(myMessageString);
       this.myMessageFacade.create(myMessage);
            
        }
        
        
    }
}
