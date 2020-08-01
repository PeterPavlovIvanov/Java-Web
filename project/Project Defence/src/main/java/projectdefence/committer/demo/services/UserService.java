package projectdefence.committer.demo.services;

import projectdefence.committer.demo.models.bindings.UserChangeRoleBindModel;
import projectdefence.committer.demo.models.entities.Post;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.models.services.PostServiceModel;
import projectdefence.committer.demo.models.services.UserServiceModel;
import projectdefence.committer.demo.models.views.UserViewModel;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);
    UserServiceModel getByEmail(String email);
    UserServiceModel getByNickname(String nickname);
    User getById(String id);
    void changeRole(UserChangeRoleBindModel userChangeRoleBindModel);
    List<User> searchUsersByAlikeNicknames(String stringSearch);
    void delete(PostServiceModel postServiceModel);
    List<UserViewModel> getAll();
}
