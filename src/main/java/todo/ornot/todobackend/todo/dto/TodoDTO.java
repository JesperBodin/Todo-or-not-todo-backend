package todo.ornot.todobackend.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;


@Data
public class TodoDTO {
    private Long id;
    @NotBlank(message = "New todo must not be empty")
    private String newTodo;
    private LocalDate dueDate;
    private Boolean done;

    public TodoDTO(Long id, String newTodo, LocalDate dueDate, boolean done) {
        this.id = id;
        this.newTodo = newTodo;
        this.dueDate = dueDate;
        this.done = done;
    }

}

