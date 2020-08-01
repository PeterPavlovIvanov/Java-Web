package projectdefence.committer.demo.models.services;

public class UserServiceModel extends BaseServiceModel{

    private String nickname;
    private String password;
    private String email;
    private RoleServiceModel roleServiceModel;


    public UserServiceModel() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleServiceModel getRoleServiceModel() {
        return roleServiceModel;
    }

    public void setRoleServiceModel(RoleServiceModel roleServiceModel) {
        this.roleServiceModel = roleServiceModel;
    }
}
