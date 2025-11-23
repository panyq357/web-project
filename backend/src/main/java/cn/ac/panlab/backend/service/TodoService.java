package cn.ac.panlab.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ac.panlab.backend.context.UserContext;
import cn.ac.panlab.backend.dto.TodoDTO;
import cn.ac.panlab.backend.exception.TodoException;
import cn.ac.panlab.backend.mapper.TodoMapper;
import cn.ac.panlab.backend.model.Todo;
import cn.ac.panlab.backend.model.User;

@Component
public class TodoService {

    @Autowired
    private TodoMapper todoMapper;

    public Long createTodo(TodoDTO dto) throws TodoException {

        User user = UserContext.get();

        Todo todo = new Todo();
        todo.setUserId(user.getId());
        todo.setMessage(dto.getMessage());

        try {
            todoMapper.insertTodo(todo);  // MyBatis will write id back to todo if insert success.

            if (todo.getId() == null) {
                throw new TodoException("TODO creation failed.");
            }
            return todo.getId();
        } catch (Exception e) {
            throw new TodoException(e.getMessage());
        }
    }

    public void deleteTodo(Long id) throws TodoException {

        Todo todo = todoMapper.getTodoById(id);
        User user = UserContext.get();

        if (todo == null || !todo.getUserId().equals(user.getId())) {
            throw new TodoException("Delete other user's TODO is forbidden");
        }

        int affectedRows = todoMapper.deleteTodoById(id);

        if (affectedRows != 1) {
            throw new TodoException("TODO creation Error");
        }
    }

    public List<Todo> getTodoListByUserId(Long userId) {

        try {
            return todoMapper.getTodoListByUserId(userId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Todo> getTodoListForCurrentUser() {
        User user = UserContext.get();

        return getTodoListByUserId(user.getId());
    }
}
