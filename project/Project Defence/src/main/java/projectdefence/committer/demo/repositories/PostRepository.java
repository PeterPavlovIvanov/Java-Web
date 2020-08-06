package projectdefence.committer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectdefence.committer.demo.models.entities.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findAll();
    Optional<Post> findById(String id);
    void deleteById(String id);
    Optional<Post> findByTitle(String title);
}
