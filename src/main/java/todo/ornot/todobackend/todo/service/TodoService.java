package todo.ornot.todobackend.todo.service;

import todo.ornot.todobackend.todo.entity.Todo;
import todo.ornot.todobackend.todo.exception.NotFoundException;
import todo.ornot.todobackend.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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


    public Optional<Todo> findById(Long id){
        return todoRepository.findById(id);
    }


    public Todo save(String newTodo, LocalDate dueDate, boolean done) {
            Todo todo = new Todo(newTodo, dueDate, done);
            return todoRepository.save(todo);
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

    public Todo updateTodo(Long id, String newTodo, LocalDate dueDate, Boolean done) {
        Optional<Todo> existingTodoOptional = todoRepository.findById(id);

        if (existingTodoOptional.isPresent()) {
            Todo existingTodo = existingTodoOptional.get();

            existingTodo.setNewTodo(newTodo);
            existingTodo.setDueDate(dueDate);
            existingTodo.setDone(done);

            return todoRepository.save(existingTodo);
        } else {
            throw new NotFoundException("Todo with id " + id + " not found");
        }
    }
}
