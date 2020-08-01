package projectdefence.committer.demo.models.services;

import projectdefence.committer.demo.services.UserService;

import java.time.LocalDateTime;

public class EventServiceModel extends BaseServiceModel{
    private String name;
    private LocalDateTime dateTime;
    private UserServiceModel committer;

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
}
