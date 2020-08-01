package projectdefence.committer.demo.models.bindings;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class UserRegisterBindModel {
    private String nickname;
    private String password;
    private String email;
    private String repeatPassword;

    public UserRegisterBindModel() {
    }

    public UserRegisterBindModel(String nickname, String password, String email, String repeatPassword) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.repeatPassword = repeatPassword;
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

    @Email(message = "Not a well-formed email address.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
