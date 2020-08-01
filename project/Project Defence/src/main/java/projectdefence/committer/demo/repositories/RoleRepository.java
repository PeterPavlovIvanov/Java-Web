package projectdefence.committer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectdefence.committer.demo.models.entities.Role;
import projectdefence.committer.demo.models.entities.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRoleName(RoleName roleName);
}
