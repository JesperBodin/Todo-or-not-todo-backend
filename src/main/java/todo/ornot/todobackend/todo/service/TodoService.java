package todo.ornot.todobackend.todo.service;

import todo.ornot.todobackend.todo.entity.Todo;
import todo.ornot.todobackend.todo.exception.NotFoundException;
import todo.ornot.todobackend.todo.exception.UnprocessableEntityException;
import todo.ornot.todobackend.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;


    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo with id " + id + " not found")));
    }

    public Todo save(Todo todo) {
        if (!todo.getNewTodo().isBlank()) {
            return todoRepository.save(todo);
        } else {
            throw new UnprocessableEntityException("Text field must not be empty! ");
        }
    }

    public void deleteById(Long id) {
        todoRepository.findById(id)
                .ifPresentOrElse(
                        todo -> todoRepository.deleteById(id),
                        () -> {
                            throw new NotFoundException("Failed to delete, no todo with id " + id + " found");
                        }
                );
    }

    public void deleteAll() {
        if(todoRepository.count() > 0) {
            todoRepository.deleteAll();
        } else {
            throw new NotFoundException("You have no todos to delete!");
        }
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        Optional<Todo> existingTodoOptional = todoRepository.findById(id);

        if (existingTodoOptional.isPresent()) {
            Todo existingTodo = existingTodoOptional.get();

            existingTodo.setNewTodo(updatedTodo.getNewTodo());
            existingTodo.setDueDate(updatedTodo.getDueDate());
            existingTodo.setDone(updatedTodo.isDone());

            return todoRepository.save(existingTodo);
        } else {
            throw new NotFoundException("Todo with id " + id + " not found");
        }
    }
}
