package softuni.one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.one.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
