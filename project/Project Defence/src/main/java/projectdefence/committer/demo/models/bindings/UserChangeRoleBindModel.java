package projectdefence.committer.demo.models.bindings;

import org.hibernate.validator.constraints.Length;
import projectdefence.committer.demo.models.entities.RoleName;

import javax.validation.constraints.NotNull;

public class UserChangeRoleBindModel {
    private String nickname;
    private RoleName roleName;

    public UserChangeRoleBindModel() {
    }

    public UserChangeRoleBindModel(String nickname, RoleName roleName) {
        this.nickname = nickname;
        this.roleName = roleName;
    }

    @Length(min = 4, max = 18, message = "The nickname must be between 4 and 18 symbols.")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @NotNull(message = "Role not selected!")
    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
