package projectdefence.committer.demo.models.services;

import projectdefence.committer.demo.models.entities.RoleName;

public class RoleServiceModel extends BaseServiceModel{
    private RoleName roleName;

    public RoleServiceModel() {
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
