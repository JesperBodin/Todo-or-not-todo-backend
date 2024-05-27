//package todo.ornot.todobackend.todo.service;
//
//import todo.ornot.todobackend.todo.entity.Todo;
//import todo.ornot.todobackend.todo.exception.NotFoundException;
// import todo.ornot.todobackend.todo.repository.CustomTodoRepository;
// import todo.ornot.todobackend.todo.repository.CustomTodoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public class CustomTodoService {
//
//    private final CustomTodoRepository customTodoRepository;
//
//    @Autowired
//    public TodoService(CustomTodoRepository customTodoRepository) {
//        this.customTodoRepository = customTodoRepository;
//    }
//
//    public List<Todo> findAll(){
//        return customTodoRepository.findAll();
//    }
//
//    public Todo findById(Long id){
//        return customTodoRepository.findById(id);
//    }
//
//    public Todo save(String newTodo, LocalDate dueDate, boolean done){
//        return customTodoRepository.save(newTodo, dueDate, done);
//    }
//
//    public void deleteById(Long id){
//        customTodoRepository.deleteById(id);
//    }
//
//    public void deleteAll(){
//        customTodoRepository.deleteAll();
//    }
//
//    public Todo updateTodo(Long id, String newTodo, LocalDate dueDate, boolean done){
//        return customTodoRepository.updateTodo(id, newTodo, dueDate, done);
//    }
//
//}
