package projectdefence.committer.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import projectdefence.committer.demo.models.bindings.PostAddBindModel;
import projectdefence.committer.demo.models.entities.Post;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.models.services.PostServiceModel;
import projectdefence.committer.demo.models.services.UserServiceModel;
import projectdefence.committer.demo.repositories.PostRepository;
import projectdefence.committer.demo.repositories.UserRepository;
import projectdefence.committer.demo.services.PostService;
import projectdefence.committer.demo.services.UserService;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public PostServiceImpl(ModelMapper modelMapper, PostRepository postRepository, UserService userService, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = this.postRepository.findAll();
        Collections.reverse(posts);
        //reverse so the most recent post is on the top
        return posts;
    }

    @Override
    public void addACommit(PostAddBindModel postAddBindModel, String id) {
        PostServiceModel postServiceModel = this.modelMapper.map(postAddBindModel, PostServiceModel.class);
        Post post = this.modelMapper.map(postServiceModel, Post.class);
        User user = this.userRepository.findById(id).orElse(null);
        post.setCommitter(user);

        if (user.getPosts() == null) {
            List<Post> posts = new ArrayList<>();
            posts.add(post);
            user.setPosts(posts);
        } else {
            user.getPosts().add(post);
        }
        this.userRepository.save(user);
        //this.postRepository.save(post);
    }

    @Override
    public Post getById(String id) {
        return this.postRepository
                .findById(id)
                .map(p -> this.modelMapper.map(p, Post.class))
                .orElse(null);
    }

    @Override
    public Post delete(String id) {
        Post post = this.postRepository.findById(id).orElse(null);
        this.postRepository.deleteById(id);
        return post;
    }

    @Override
    public void vote(User user, Post post, int vote) {
        Post postVoted = this.postRepository.findById(post.getId()).orElse(null);
        postVoted.setPoints(postVoted.getPoints() + 1);
        this.postRepository.saveAndFlush(postVoted);
    }
}
