package projectdefence.committer.demo.models.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Event extends BaseEntity {
    private String name;
    private LocalDateTime dateTime;
    private User committer;
    private List<User> participants;

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

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
