package todo.ornot.todobackend.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.ornot.todobackend.todo.entity.Todo;
import todo.ornot.todobackend.user.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Find todos by the user
    List<Todo> findByUser(UserEntity user);

    // Find all todos for a specific user by user ID
    List<Todo> findByUserId(Long userId);

    // Find a specific todo by ID and user ID
    Optional<Todo> findByIdAndUserId(Long id, Long userId);

    // Delete all todos for a specific user
    void deleteByUserId(Long userId);
}

