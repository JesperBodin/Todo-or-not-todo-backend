package com.example.todobackend.todo.service;

import com.example.todobackend.todo.entity.Todo;
import com.example.todobackend.todo.exception.NotFoundException;
import com.example.todobackend.todo.repository.TodoRepository;
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
        return todoRepository.findById(id);
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public void deleteAll() {
        todoRepository.deleteAll();
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
