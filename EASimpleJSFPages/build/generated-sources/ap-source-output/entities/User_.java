package entities;

import entities.Message;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-01-30T10:00:01")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> name;
    public static volatile ListAttribute<User, Message> userMessageList;
    public static volatile SingularAttribute<User, String> firstName;

}