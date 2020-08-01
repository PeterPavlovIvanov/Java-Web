package projectdefence.committer.demo.models.bindings;

import org.hibernate.validator.constraints.Length;

public class UserLoginBindModel {
    private String nickname;
    private String password;

    public UserLoginBindModel() {
    }

    public UserLoginBindModel(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    @Length(min = 4, max = 18, message = "The nickname must be between 4 and 18 symbols.")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Length(min = 4, max = 18, message = "The password must be between 4 and 18 symbols.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
