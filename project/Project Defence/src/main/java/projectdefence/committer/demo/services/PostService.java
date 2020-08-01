package projectdefence.committer.demo.services;

import projectdefence.committer.demo.models.bindings.PostAddBindModel;
import projectdefence.committer.demo.models.entities.Post;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.models.services.PostServiceModel;
import projectdefence.committer.demo.models.services.UserServiceModel;

import java.util.List;

public interface PostService {
    List<Post> getAll();

    void addACommit(PostAddBindModel postAddBindModel, String id);

    Post getById(String id);

    Post delete(String id);

    void vote(User user, Post post, int vote);
}
