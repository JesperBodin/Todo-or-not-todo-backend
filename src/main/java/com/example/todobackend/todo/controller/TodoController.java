package com.example.todobackend.todo.controller;

import com.example.todobackend.todo.service.TodoService;
import com.example.todobackend.todo.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Todo getEntityById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @PostMapping("/add")
    public Todo saveEntity(@RequestBody Todo todo) {
        todoService.add(todo);
        return todo;
    }

    @PostMapping("/add/multiple")
    public void saveEntities(@RequestBody List<Todo> todos) {
        for (Todo todo : todos) {
            todoService.add(todo);
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