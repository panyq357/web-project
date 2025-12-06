package cn.ac.panlab.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.ac.panlab.backend.dto.CreateTodoDTO;
import cn.ac.panlab.backend.dto.ResultDTO;
import cn.ac.panlab.backend.dto.UpdateTodoDTO;
import cn.ac.panlab.backend.exception.TodoException;
import cn.ac.panlab.backend.model.Todo;
import cn.ac.panlab.backend.service.TodoService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("${app.frontend.url}")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/todos")
    public ResultDTO createTodo(@RequestBody CreateTodoDTO dto) throws TodoException {
        todoService.createTodo(dto);
        return ResultDTO.success("New Todo added.");
    }

    @DeleteMapping("/todos/{id}")
    public ResultDTO deleteTodo(@PathVariable("id") Long id) throws TodoException {
        todoService.deleteTodo(id);
        return ResultDTO.success("Todo id:" + id + " deleted.");
    }

    @GetMapping("/todos")
    public ResultDTO getTodoList() {

        List<Todo> todos = todoService.getTodoListForCurrentUser();
        return ResultDTO.success("Todo list get.", todos);
    }

    @PutMapping("/todos/{id}")
    public ResultDTO updateTodo(
        @PathVariable Long id,
        @RequestBody UpdateTodoDTO dto
    ) throws TodoException {
        dto.setId(id);
        todoService.updateTodo(dto);
        return ResultDTO.success("Todo Updated.");
    }
}
