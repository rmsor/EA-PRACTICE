package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author xtrememe
 */
@Entity(name="admin_table")
public class Admin implements Serializable {
    @Id
    private Long id;
    public Admin(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
