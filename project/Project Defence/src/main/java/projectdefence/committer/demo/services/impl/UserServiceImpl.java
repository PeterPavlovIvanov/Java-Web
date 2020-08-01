package projectdefence.committer.demo.services.impl;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import projectdefence.committer.demo.models.bindings.UserChangeRoleBindModel;
import projectdefence.committer.demo.models.entities.Post;
import projectdefence.committer.demo.models.entities.Role;
import projectdefence.committer.demo.models.entities.RoleName;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.models.services.PostServiceModel;
import projectdefence.committer.demo.models.services.UserServiceModel;
import projectdefence.committer.demo.models.views.UserViewModel;
import projectdefence.committer.demo.repositories.UserRepository;
import projectdefence.committer.demo.services.RoleService;
import projectdefence.committer.demo.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        if (this.userRepository.count() == 0) {
            userServiceModel.setRoleServiceModel(this.roleService.getByRoleName(RoleName.ADMIN));
        } else {
            userServiceModel.setRoleServiceModel(this.roleService.getByRoleName(RoleName.USER));
        }
        User user = this.modelMapper.map(userServiceModel, User.class);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserServiceModel getByEmail(String email) {
        return this.userRepository
                .findByEmail(email)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserServiceModel getByNickname(String nickname) {
        return this.userRepository
                .findByNickname(nickname)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public User getById(String id) {
        return this.userRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public void changeRole(UserChangeRoleBindModel userChangeRoleBindModel) {
        User user = this.userRepository
                .findByNickname(userChangeRoleBindModel.getNickname())
                .orElse(null);

        user.setRole(this.modelMapper.map(this.roleService.getByRoleName(userChangeRoleBindModel.getRoleName()), Role.class));
        this.userRepository.save(user);
    }

    @Override
    public List<User> searchUsersByAlikeNicknames(String stringSearch) {
        List<User> byNicknameStartingWith = this.userRepository.findByNicknameStartingWith(stringSearch);
        return byNicknameStartingWith;
    }

    @Override
    public void delete(PostServiceModel postServiceModel) {
        User user = this.userRepository.findByNickname(postServiceModel.getCommitter().getNickname()).orElse(null);
        Post post = this.modelMapper.map(postServiceModel, Post.class);

        for (int i = 0; i < user.getPosts().size(); i++) {
            if (user.getPosts().get(i).getId().equals(post.getId())) {
                user.getPosts().remove(i);
                break;
            }
        }

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<UserViewModel> getAll() {
        List<User> users = this.userRepository.findAll();
        List<UserViewModel> userViewModels = new ArrayList<>();
        for (User u : users) {
            userViewModels.add(this.modelMapper.map(u, UserViewModel.class));
        }
        return userViewModels;
    }


}
