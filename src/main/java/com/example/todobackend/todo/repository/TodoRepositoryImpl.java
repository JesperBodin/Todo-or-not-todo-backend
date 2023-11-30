package com.example.todobackend.todo.repository;

import com.example.todobackend.todo.entity.Todo;
import com.example.todobackend.todo.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    private final Map<Long, Todo> todoMap = new HashMap<>();
    private long currentId = 1;

    @Override
    public List<Todo> findAll() {
        List<Todo> todos = new ArrayList<>(todoMap.values());

        if (todos.isEmpty()) {
            throw new NotFoundException("List of todos is empty");
        }
        return todos;
    }

    @Override
    public Todo findById(Long id) {
        Todo todo = todoMap.get(id);
        if (todo == null) {
            throw new NotFoundException("Todo with id " + id + " not found");
        }
        return todo;
    }

    @Override
    public void add(Todo todo) {
        if (todo.getId() == null) {
            todo.setId(currentId++);
        }
        todoMap.put(todo.getId(), todo);
    }

    @Override
    public void deleteById(Long id) {
        if (!todoMap.containsKey(id)) {
            throw new NotFoundException("Todo with id " + id + " not found");
        }
        todoMap.remove(id);
    }

    @Override
    public void deleteAll() {
        todoMap.clear();
    }

    @Override
    public void update(Long id, Todo updatedTodo) {
        Todo existingTodo = todoMap.get(id);

        if (existingTodo != null) {
            existingTodo.setText(updatedTodo.getText());
            existingTodo.setDeadLine(updatedTodo.getDeadLine());
            existingTodo.setDone(updatedTodo.isDone());
        } else {
            throw new NotFoundException("Todo with id " + id + " not found");
        }
    }
}
