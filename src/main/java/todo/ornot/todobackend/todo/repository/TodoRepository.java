package todo.ornot.todobackend.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.ornot.todobackend.todo.entity.Todo;
import todo.ornot.todobackend.user.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUserId(Long userId);
    Optional<Todo> findByIdAndUserId(Long id, Long userId);
    void deleteByUserId(Long userId);
}

