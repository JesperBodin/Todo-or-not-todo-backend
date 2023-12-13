package todo.ornot.todobackend.todo.controller;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import todo.ornot.todobackend.todo.dto.TodoDTO;
import todo.ornot.todobackend.todo.exception.NotFoundException;
import todo.ornot.todobackend.todo.service.TodoService;
import todo.ornot.todobackend.todo.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/todo")
@CrossOrigin("http://localhost:5173/")
@Validated
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoDTO> getAllEntities() {
        List<Todo> todos = todoService.findAll();
        return todos.stream()
                .map(Todo::todoDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TodoDTO getEntityById(@PathVariable Long id) {
        Todo todo = todoService.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo with id " + id + " not found"));
        return todo.todoDTO();
    }

    @PostMapping("/add")
    public TodoDTO saveEntity(@RequestBody @Valid TodoDTO todoDTO) {
        Todo savedTodo = todoService.save(todoDTO.getNewTodo(), todoDTO.getDueDate(), todoDTO.getDone());
        return savedTodo.todoDTO();
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
    public TodoDTO updateTodo(
            @PathVariable Long id,
            @RequestBody TodoDTO todoDTO) {
        Todo updatedTodo = todoService.updateTodo(id, todoDTO.getNewTodo(), todoDTO.getDueDate(), todoDTO.getDone());
        return updatedTodo.todoDTO();
    }
}
