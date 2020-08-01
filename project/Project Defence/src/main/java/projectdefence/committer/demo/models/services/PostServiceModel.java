package projectdefence.committer.demo.models.services;

import java.util.List;

public class PostServiceModel extends BaseServiceModel{
    private String title;
    private String text;
    private int points;
    private UserServiceModel committer;
    private List<UserServiceModel> upVoted;
    private List<UserServiceModel> downVoted;

    public PostServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<UserServiceModel> getUpVoted() {
        return upVoted;
    }

    public void setUpVoted(List<UserServiceModel> upVoted) {
        this.upVoted = upVoted;
    }

    public List<UserServiceModel> getDownVoted() {
        return downVoted;
    }

    public void setDownVoted(List<UserServiceModel> downVoted) {
        this.downVoted = downVoted;
    }

    public UserServiceModel getCommitter() {
        return committer;
    }

    public void setCommitter(UserServiceModel committer) {
        this.committer = committer;
    }

}
