package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Post;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-02-19T10:05:47")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> role;
    public static volatile SingularAttribute<User, String> gender;
    public static volatile SingularAttribute<User, Date> registeredDate;
    public static volatile SingularAttribute<User, String> profilePic;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile ListAttribute<User, Post> userPosts;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phoneNumber;
    public static volatile SingularAttribute<User, String> dType;
    public static volatile SingularAttribute<User, String> addressLine1;
    public static volatile SingularAttribute<User, String> addressLine2;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> age;

}