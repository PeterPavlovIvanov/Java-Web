package projectdefence.committer.demo.services;

import projectdefence.committer.demo.models.entities.User;

import java.util.List;
import java.util.Set;

public interface FollowerService {
    void follow(String followerId, String followedId);
    void unfollow(String followerId, String followedId);
    List<User> getFollowers(User user);
}
