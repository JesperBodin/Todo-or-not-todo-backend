package todo.ornot.todobackend.todo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import todo.ornot.todobackend.todo.entity.Todo;
import todo.ornot.todobackend.todo.exception.NotFoundException;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CustomTodoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomTodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Todo> findAll() {
        String sql = "SELECT * FROM todo";
        return jdbcTemplate.query(sql, this::mapRowToTodo);
    }

    public Todo findById(Long id) {
        String sql = "SELECT * FROM todo WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToTodo, id);
    }

    public Todo save(String new_todo, LocalDate due_date, boolean done) {
        String sql = "INSERT INTO todo (new_todo, due_date, done) VALUES (?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, new_todo);
            ps.setTimestamp(2, Timestamp.valueOf(due_date.atStartOfDay()));
            ps.setBoolean(3, done);
            return ps;
        });
        return new Todo(new_todo, due_date, done);
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM todo WHERE id = ?";
        int deletedRows = jdbcTemplate.update(sql, id);

        if (deletedRows == 0) {
            throw new NotFoundException("Failed to delete, no todo with id " + id + " found");
        }

    }

    public void deleteAll() {
        String sql = "DELETE FROM todo";
        jdbcTemplate.update(sql);

    }

    public Todo updateTodo(Long id, String newTodo, LocalDate dueDate, Boolean done) {
        String sql = "UPDATE todo SET new_todo = ?, due_date = ?, done = ? WHERE id = ?";
        System.out.println("SQL Query in updateTodo: " + sql);
        int updatedRows = jdbcTemplate.update(sql, newTodo, Timestamp.valueOf(dueDate.atStartOfDay()), done, id);

        if (updatedRows == 0) {
            throw new NotFoundException("Todo with id " + id + " not found");
        }
        return new Todo(id, newTodo, dueDate, done);
    }

    private Todo mapRowToTodo(ResultSet resultSet, int rowNum) throws SQLException {
        return new Todo(
                resultSet.getLong("id"),
                resultSet.getString("new_todo"),
                resultSet.getDate("due_date").toLocalDate(),
                resultSet.getBoolean("done")
        );
    }
}
