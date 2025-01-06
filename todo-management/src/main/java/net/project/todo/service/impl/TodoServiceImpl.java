package net.project.todo.service.impl;

import lombok.AllArgsConstructor;
import net.project.todo.dto.TodoDto;
import net.project.todo.entity.Todo;
import net.project.todo.exception.ResourceNotFoundException;
import net.project.todo.repository.TodoRepository;
import net.project.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private  TodoRepository todoRepository;
    private ModelMapper modelMapper;

   @Override
    public TodoDto addTodo(TodoDto todoDto) {
       Todo todo =modelMapper.map(todoDto,Todo.class);
      Todo savedTodo=  todoRepository.save(todo);
       TodoDto savedTodoDto = modelMapper.map(savedTodo,TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
       Todo todo = todoRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("not found"+ id));
        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
       List<Todo> todos = todoRepository.findAll();

        return todos.stream().map((todo)->modelMapper.map(todo,TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
    Todo todo=   todoRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("not found"+ id));
    todo.setTitle(todoDto.getTitle());
    todo.setDescription(todoDto.getDescription());
    todo.setCompleted(todoDto.isCompleted());
    Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
  Todo todo=  todoRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("deleted"+ id));

  todoRepository.deleteById(id);

    }

    @Override
    public TodoDto completeTodo(Long id) {
     Todo todo=  todoRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("not found"+ id));
       todo.setCompleted(Boolean.TRUE);
     Todo updatedTodo=  todoRepository.save(todo);

        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo=  todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("not found"+ id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo=  todoRepository.save(todo);

        return modelMapper.map(updatedTodo,TodoDto.class);
    }


}
