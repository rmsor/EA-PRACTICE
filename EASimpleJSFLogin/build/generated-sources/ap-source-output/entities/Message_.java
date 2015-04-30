package entities;

import entities.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-01-31T11:10:36")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, Long> id;
    public static volatile SingularAttribute<Message, String> myTextMessage;
    public static volatile SingularAttribute<Message, User> mySender;
    public static volatile SingularAttribute<Message, User> myReceiver;

}