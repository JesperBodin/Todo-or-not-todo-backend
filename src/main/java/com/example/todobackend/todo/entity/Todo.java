package com.example.todobackend.todo.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Todo {
    private Long id;
    private String newTodo;
    private LocalDate dueDate;
    private boolean done;

    public Todo() {
    }

    public Todo(String newTodo, LocalDate dueDate, boolean done) {
        this.newTodo = newTodo;
        this.dueDate = dueDate;
        this.done = done;
    }

    public Todo(Long id, String newTodo, LocalDate dueDate, boolean done) {
        this.id = id;
        this.newTodo = newTodo;
        this.dueDate = dueDate;
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", newTodo='" + newTodo + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}
