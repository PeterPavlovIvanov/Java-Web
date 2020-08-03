package projectdefence.committer.demo.models.bindings;

import org.hibernate.validator.constraints.Length;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.models.services.UserServiceModel;

public class PostAddBindModel {
    private String title;
    private String text;
    private UserServiceModel committer;

    public PostAddBindModel() {
    }

    public PostAddBindModel(String title, String text, UserServiceModel committer) {
        this.title = title;
        this.text = text;
        this.committer = committer;
    }

    @Length(min = 4, max = 20, message = "The Title must be between 4 and 20 symbols.")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Length(min = 10, message = "The text must be at least 10  symbols.")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserServiceModel getCommitter() {
        return committer;
    }

    public void setCommitter(UserServiceModel committer) {
        this.committer = committer;
    }
}
