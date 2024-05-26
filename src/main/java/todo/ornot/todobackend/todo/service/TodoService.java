package todo.ornot.todobackend.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.ornot.todobackend.todo.entity.Todo;
import todo.ornot.todobackend.todo.exception.NotFoundException;
import todo.ornot.todobackend.todo.repository.TodoRepository;
import todo.ornot.todobackend.user.entity.UserEntity;
import todo.ornot.todobackend.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<Todo> findAllByUserId(Long userId) {
        return todoRepository.findByUserId(userId);
    }

    public Optional<Todo> findByIdAndUserId(Long id, Long userId) {
        return todoRepository.findByIdAndUserId(id, userId);
    }

    public Todo save(String newTodo, LocalDate dueDate, boolean done, Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Todo todo = new Todo();
        todo.setNewTodo(newTodo);
        todo.setDueDate(dueDate);
        todo.setDone(done);
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public void deleteByIdAndUserId(Long id, Long userId) {
        Todo todo = todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException("Todo not found or does not belong to user"));
        todoRepository.delete(todo);
    }

    @Transactional
    public void deleteAllByUserId(Long userId) {
        todoRepository.deleteByUserId(userId);
    }

    public Todo updateTodo(Long userId, Long id, String newTodo, LocalDate dueDate, boolean done) {
        Todo todo = todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException("Todo not found or does not belong to user"));

        todo.setNewTodo(newTodo);
        todo.setDueDate(dueDate);
        todo.setDone(done);

        return todoRepository.save(todo);
    }
}