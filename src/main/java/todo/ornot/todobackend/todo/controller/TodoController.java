package todo.ornot.todobackend.todo.controller;

import todo.ornot.todobackend.todo.service.TodoService;
import todo.ornot.todobackend.todo.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/todo")
@CrossOrigin("http://localhost:5173/")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllEntities() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Todo> getEntityById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @PostMapping("/add")
    public Todo saveEntity(@RequestBody Todo todo) {
        todoService.save(todo);
        return todo;
    }

    @PostMapping("/add/multiple")
    public void saveEntities(@RequestBody List<Todo> todos) {
        for (Todo todo : todos) {
            todoService.save(todo);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEntityById(@PathVariable Long id) {
        todoService.deleteById(id);
    }

    @DeleteMapping("/delete")
    public void deleteAllEntities() {
        todoService.deleteAll();
    }

    @PutMapping("/update/{id}")
    public Todo updateTodo(
            @PathVariable Long id,
            @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo);
    }
}
