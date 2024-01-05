package todo.ornot.todobackend.todo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import todo.ornot.todobackend.todo.dto.TodoDTO;
import todo.ornot.todobackend.todo.entity.Todo;
import todo.ornot.todobackend.todo.exception.NotFoundException;
import todo.ornot.todobackend.todo.service.TodoService;
import todo.ornot.todobackend.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/todo")
@CrossOrigin("http://localhost:5173/")
@Validated
public class TodoController {

    private final TodoService todoService;
    private final UserRepository userRepository;

    @Autowired
    public TodoController(TodoService todoService, UserRepository userRepository) {
        this.todoService = todoService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<TodoDTO> getAllTodos(Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        List<Todo> todos = todoService.findAllByUserId(userId);
        return todos.stream().map(Todo::todoDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TodoDTO getTodoById(@PathVariable Long id, Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        Todo todo = todoService.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException("Todo with id " + id + " not found"));
        return todo.todoDTO();
    }

    @PostMapping("/add")
    public TodoDTO saveEntity(@RequestBody @Valid TodoDTO todoDTO, Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        Todo savedTodo = todoService.save(todoDTO.getNewTodo(), todoDTO.getDueDate(), todoDTO.getDone(), userId);
        return savedTodo.todoDTO();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEntityById(@PathVariable Long id, Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        todoService.deleteByIdAndUserId(id, userId);
    }

    @DeleteMapping("/delete")
    public void deleteAllEntities(Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        todoService.deleteAllByUserId(userId);
    }

    @PutMapping("/update/{id}")
    public TodoDTO updateTodo(
            @PathVariable Long id,
            @RequestBody @Valid TodoDTO todoDTO,
            Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        Todo updatedTodo = todoService.updateTodo(userId, id, todoDTO.getNewTodo(), todoDTO.getDueDate(), todoDTO.getDone());
        return updatedTodo.todoDTO();
    }

    private Long getUserIdFromAuthentication(Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found")).getId();
    }
}