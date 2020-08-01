package projectdefence.committer.demo.models.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    private String id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
