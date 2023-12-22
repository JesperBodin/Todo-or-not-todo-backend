package todo.ornot.todobackend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.ornot.todobackend.user.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
