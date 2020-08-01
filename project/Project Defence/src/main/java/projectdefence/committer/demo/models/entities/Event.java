package projectdefence.committer.demo.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Event extends BaseEntity {
    private String name;
    private LocalDateTime dateTime;
    private User committer;

    public Event() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @ManyToOne
    public User getCommitter() {
        return committer;
    }

    public void setCommitter(User committer) {
        this.committer = committer;
    }
}
