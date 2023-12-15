package todo.ornot.todobackend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.ornot.todobackend.user.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}
