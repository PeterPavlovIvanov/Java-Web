package projectdefence.committer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectdefence.committer.demo.models.entities.FollowedUser;

@Repository
public interface FollowerRepository extends JpaRepository<FollowedUser, String> {
    FollowedUser findByFollowed_Id(String followedId);
    void deleteById(String id);
}
