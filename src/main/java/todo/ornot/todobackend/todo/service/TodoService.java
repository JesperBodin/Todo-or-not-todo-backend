package todo.ornot.todobackend.todo.service;

import todo.ornot.todobackend.todo.entity.Todo;
import todo.ornot.todobackend.todo.exception.NotFoundException;
import todo.ornot.todobackend.todo.repository.CustomTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

//    private final TodoRepository todoRepository;

    private final CustomTodoRepository customTodoRepository;


    @Autowired
    public TodoService(CustomTodoRepository customTodoRepository) {
        this.customTodoRepository = customTodoRepository;
    }

//    public List<Todo> findAll() {
//        return todoRepository.findAll();
//    }

    public List<Todo> findAll(){
        return customTodoRepository.findAll();
    }

//    public Optional<Todo> findById(Long id){
//        return todoRepository.findById(id);
//    }

    public Todo findById(Long id){
        return customTodoRepository.findById(id);
    }


//    public Todo save(String newTodo, LocalDate dueDate, boolean done) {
//            Todo todo = new Todo(newTodo, dueDate, done);
//            return todoRepository.save(todo);
//    }

    public Todo save(String newTodo, LocalDate dueDate, boolean done){
        return customTodoRepository.save(newTodo, dueDate, done);
    }

//    public void deleteById(Long id) {
//        todoRepository.findById(id)
//                .ifPresentOrElse(
//                        todo -> todoRepository.deleteById(id),
//                        () -> {
//                            throw new NotFoundException("Failed to delete, no todo with id " + id + " found");
//                        }
//                );
//    }

    public void deleteById(Long id){
        customTodoRepository.deleteById(id);
    }

//    public void deleteAll() {
//        if(todoRepository.count() > 0) {
//            todoRepository.deleteAll();
//        } else {
//            throw new NotFoundException("You have no todos to delete!");
//        }
//    }

    public void deleteAll(){
        customTodoRepository.deleteAll();
    }

//    public Todo updateTodo(Long id, String newTodo, LocalDate dueDate, Boolean done) {
//        Optional<Todo> existingTodoOptional = todoRepository.findById(id);
//
//        if (existingTodoOptional.isPresent()) {
//            Todo existingTodo = existingTodoOptional.get();
//
//            existingTodo.setNewTodo(newTodo);
//            existingTodo.setDueDate(dueDate);
//            existingTodo.setDone(done);
//
//            return todoRepository.save(existingTodo);
//        } else {
//            throw new NotFoundException("Todo with id " + id + " not found");
//        }
//    }

    public Todo updateTodo(Long id, String newTodo, LocalDate dueDate, boolean done){
        return customTodoRepository.updateTodo(id, newTodo, dueDate, done);
    }
}
