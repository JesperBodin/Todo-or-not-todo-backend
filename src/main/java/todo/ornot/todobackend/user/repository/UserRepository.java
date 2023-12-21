package todo.ornot.todobackend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import todo.ornot.todobackend.user.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_roles WHERE user_id = ?1", nativeQuery = true)
    void deleteUserRolesByUserId(Long id);
}
