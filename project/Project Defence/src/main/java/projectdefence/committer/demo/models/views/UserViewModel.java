package projectdefence.committer.demo.models.views;

import projectdefence.committer.demo.models.entities.Role;

public class UserViewModel {
    private String nickname;
    private String id;

    public UserViewModel() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
