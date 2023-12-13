package todo.ornot.todobackend.todo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
