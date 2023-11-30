package com.example.todobackend.todo.service;

import com.example.todobackend.todo.entity.Todo;
import com.example.todobackend.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Todo findById(Long id) {
        return todoRepository.findById(id);
    }

    public void add(Todo todo) {
        todoRepository.add(todo);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public void deleteAll() {
        todoRepository.deleteAll();
    }

    public void updateTodo(Long id, Todo updatedTodo) {
        todoRepository.update(id, updatedTodo);
    }
}