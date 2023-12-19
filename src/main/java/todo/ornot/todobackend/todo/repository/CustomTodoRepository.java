package todo.ornot.todobackend.todo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import todo.ornot.todobackend.todo.entity.Todo;
import todo.ornot.todobackend.todo.exception.NotFoundException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

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
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("todo")
                .usingGeneratedKeyColumns("id");

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("new_todo", new_todo)
                .addValue("due_date", due_date)
                .addValue("done", done);

        Long id = simpleJdbcInsert.executeAndReturnKey(parameters).longValue();

        return new Todo(id, new_todo, due_date, done);
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

        int updatedRows;

        if (dueDate != null) {
            updatedRows = jdbcTemplate.update(sql, newTodo, Timestamp.valueOf(dueDate.atStartOfDay()), done, id);
        } else {
            updatedRows = jdbcTemplate.update(sql, newTodo, null, done, id);
        }

        if (updatedRows == 0) {
            throw new NotFoundException("Todo with id " + id + " not found");
        }
        return new Todo(id, newTodo, dueDate, done);
    }

    private Todo mapRowToTodo(ResultSet resultSet, int rowNum) throws SQLException {
        long id = resultSet.getLong("id");
        String newTodo = resultSet.getString("new_todo");

        LocalDate dueDate = null;
        Date date = resultSet.getDate("due_date");
        if (date != null) {
            dueDate = date.toLocalDate();
        }

        boolean done = resultSet.getBoolean("done");

        return new Todo(id, newTodo, dueDate, done);
    }
}
