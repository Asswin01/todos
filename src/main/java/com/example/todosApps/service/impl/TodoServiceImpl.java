package com.example.todosApps.service.impl;

import com.example.todosApps.dto.TodoDto;
import com.example.todosApps.entity.Todo;
import com.example.todosApps.exception.ResourceNotFoundException;
import com.example.todosApps.repository.TodoRepository;
import com.example.todosApps.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // convert TodoDto into Todo Jpa entity
        Todo todo = modelMapper.map(todoDto, Todo.class);

        // Todo Jpa entity
        Todo savedTodo = todoRepository.save(todo);

        // Convert saved Todo Jpa entity object into TodoDto object

        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;


//        Todo todo = new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());
//
//        Todo savedTodo = todoRepository.save(todo);
//
//        TodoDto savedTodoDto = new TodoDto();
//        savedTodoDto.setId(savedTodo.getId());
//        savedTodoDto.setTitle(savedTodo.getTitle());
//        savedTodoDto.setDescription(savedTodo.getDescription());
//        savedTodoDto.setCompleted(savedTodoDto.isCompleted());
//        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {

        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not found with id:" + id));
        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(todo -> modelMapper.map(todo,TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found with id:" + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedtodo = todoRepository.save(todo);
        return modelMapper.map(updatedtodo,TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }


}
