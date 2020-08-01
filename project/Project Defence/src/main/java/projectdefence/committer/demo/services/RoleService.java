package projectdefence.committer.demo.services;

import projectdefence.committer.demo.models.entities.RoleName;
import projectdefence.committer.demo.models.services.RoleServiceModel;

public interface RoleService {
    void init();
    RoleServiceModel getByRoleName(RoleName roleName);
}
