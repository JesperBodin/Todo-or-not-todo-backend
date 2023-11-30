package com.example.todobackend.todo.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Todo {
    private Long id;
    private String text;
    private LocalDate deadLine;
    private boolean done;

    public Todo() {
    }

    public Todo(String text, LocalDate deadLine, boolean done) {
        this.text = text;
        this.deadLine = deadLine;
        this.done = done;
    }

    public Todo(Long id, String text, LocalDate deadLine, boolean done) {
        this.id = id;
        this.text = text;
        this.deadLine = deadLine;
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", deadLine=" + deadLine +
                '}';
    }
}
