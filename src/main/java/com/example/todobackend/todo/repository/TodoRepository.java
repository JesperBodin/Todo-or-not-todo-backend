package com.example.todobackend.todo.repository;

import com.example.todobackend.todo.entity.Todo;

import java.util.List;

public interface TodoRepository {

    List<Todo> findAll();

    Todo findById(Long id);

    void add(Todo todo);

    void deleteById(Long id);

    void deleteAll();

    Todo update(Long id, Todo updateTodo);
}

