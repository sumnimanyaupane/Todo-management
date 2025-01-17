package net.project.todo.controller;

import lombok.AllArgsConstructor;
import net.project.todo.dto.TodoDto;
import net.project.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("api/todos")
public class TodoController {
    private TodoService todoService;

    //build add todo rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo( @RequestBody TodoDto todoDto) {
    TodoDto savedTodo =   todoService.addTodo(todoDto);
    return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);

    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long TodoId){
        TodoDto todoDto= todoService.getTodo(TodoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        todoService.getAllTodos();
        List<TodoDto> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo( @RequestBody TodoDto todoDto, @PathVariable("id") Long todoId) {
        TodoDto updatedTodo =   todoService.updateTodo(todoDto, todoId);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
            public ResponseEntity<String>deleteTodo(@PathVariable("id") Long TodoId) {
        todoService.deleteTodo(TodoId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    //build complete todo rest api
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long TodoId) {
      TodoDto updatedTodo= todoService.completeTodo(TodoId);
      return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long TodoId) {
        TodoDto updatedTodo= todoService.inCompleteTodo(TodoId);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

}

