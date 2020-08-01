package projectdefence.committer.demo.models.entities;

import javax.persistence.*;
@Entity
@Table
public class Role extends BaseEntity {
    private RoleName roleName;
    private String authority;

    public Role() {
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    //@Override
    public String getAuthority() {
        if (this.getRoleName() == RoleName.ADMIN || this.getRoleName().toString().equals(RoleName.ADMIN.toString())) {
            return "ADMIN";
        } else {
            return "USER";
        }
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
