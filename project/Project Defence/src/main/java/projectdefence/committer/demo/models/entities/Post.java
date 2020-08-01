package projectdefence.committer.demo.models.entities;

import javax.persistence.*;

@Entity
@Table
public class Post extends BaseEntity {
    private String title;
    private String text;
    private User committer;
    private int points;

    public Post() {
    }

    @Column(nullable = false, unique = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    public User getCommitter() {
        return committer;
    }

    public void setCommitter(User committer) {
        this.committer = committer;
    }

    @Column
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
