package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-02-19T10:05:47")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, String> description;
    public static volatile SingularAttribute<Comment, Long> id;
    public static volatile SingularAttribute<Comment, User> userid;

}