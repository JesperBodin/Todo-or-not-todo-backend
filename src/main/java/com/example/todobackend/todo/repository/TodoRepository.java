package com.example.todobackend.todo.repository;

import com.example.todobackend.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

//    List<Todo> findAll();
//
//    Todo findById(Long id);
//
//    void add(Todo todo);
//
//    void deleteById(Long id);
//
//    void deleteAll();
//
//    Todo update(Long id, Todo updateTodo);
}

