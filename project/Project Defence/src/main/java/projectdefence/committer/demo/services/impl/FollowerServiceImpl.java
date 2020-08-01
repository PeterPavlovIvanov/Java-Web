package projectdefence.committer.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import projectdefence.committer.demo.models.entities.FollowedUser;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.repositories.FollowerRepository;
import projectdefence.committer.demo.repositories.UserRepository;
import projectdefence.committer.demo.services.FollowerService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FollowerServiceImpl implements FollowerService {
    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public FollowerServiceImpl(FollowerRepository followerRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.followerRepository = followerRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void follow(String followerId, String followedId) {
        FollowedUser followedUser = new FollowedUser();

        if (this.followerRepository.findByFollowed_Id(followedId) == null) {
            followedUser.setFollowed(this.userRepository.findById(followedId).orElse(null));

            List<User> followers = new ArrayList<>();
            followers.add(this.userRepository.findById(followerId).orElse(null));
            followedUser.setFollowers(followers);
        } else {
            followedUser = this.followerRepository.findByFollowed_Id(followedId);

            followedUser.getFollowers().add(this.userRepository.findById(followerId).orElse(null));
        }

        System.out.println();
        this.followerRepository.save(followedUser);
    }

    @Override
    public void unfollow(String followerId, String followedId) {
        FollowedUser followedPerson = this.followerRepository.findByFollowed_Id(followedId);

        User userToDel = this.userRepository.findById(followerId).orElse(null);

        int i = 0;
        for (User u : followedPerson.getFollowers()){
            if(u.getId().equals(followerId)){
                followedPerson.getFollowers().remove(i);
                break;
            }
            i++;
        }

        followedPerson.getFollowers().remove(userToDel);
        System.out.println();
        this.followerRepository.saveAndFlush(followedPerson);
    }

    @Override
    public List<User> getFollowers(User user) {
        FollowedUser byFollowed_id = this.followerRepository.findByFollowed_Id(user.getId());
        if (byFollowed_id == null) {
            return new ArrayList<>();
        }
        return byFollowed_id.getFollowers();
    }
}
