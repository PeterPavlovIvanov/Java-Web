package projectdefence.committer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectdefence.committer.demo.models.entities.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,String > {
    List<Event> findAll();
    void deleteById(String id);
}
