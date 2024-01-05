package todo.ornot.todobackend.todo.entity;

import jakarta.persistence.*;
import lombok.Data;
import todo.ornot.todobackend.todo.dto.TodoDTO;
import todo.ornot.todobackend.user.entity.UserEntity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Todo(Long userId, String newTodo, LocalDate dueDate, boolean done, UserEntity user) {
    }


    public TodoDTO todoDTO() {
        return new TodoDTO(this.id, this.newTodo, this.dueDate, this.done);
    }

    public Todo() {
    }

    public Todo(String newTodo, LocalDate dueDate, boolean done, UserEntity user) {
        this.newTodo = newTodo;
        this.dueDate = dueDate;
        this.done = done;
        this.user = user;
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
