package projectdefence.committer.demo.models.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class FollowedUser extends BaseEntity{
    private User followed;
    private List<User> followers;

    public FollowedUser() {
    }

    @OneToOne
    public User getFollowed() {
        return followed;
    }

    public void setFollowed(User followed) {
        this.followed = followed;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.DETACH)
    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}
