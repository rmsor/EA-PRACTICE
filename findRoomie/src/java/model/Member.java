package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author xtrememe
 */
@Entity
public class Member implements Serializable {
    @Id
    private Long id;
    
    // TODO add other properties, posts, messages

    public Member(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
        
}


