package projectdefence.committer.demo.models.services;

import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.services.UserService;

import java.time.LocalDateTime;
import java.util.List;

public class EventServiceModel extends BaseServiceModel{
    private String name;
    private LocalDateTime dateTime;
    private UserServiceModel committer;
    private List<User> participants;

    public EventServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public UserServiceModel getCommitter() {
        return committer;
    }

    public void setCommitter(UserServiceModel committer) {
        this.committer = committer;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
