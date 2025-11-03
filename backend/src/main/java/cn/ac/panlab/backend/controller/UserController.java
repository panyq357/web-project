package cn.ac.panlab.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.ac.panlab.backend.mapper.UserMapper;
import cn.ac.panlab.backend.model.Result;
import cn.ac.panlab.backend.model.User;

@RestController
@CrossOrigin("http://localhost:8000/")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users")
    public Result getAllUsernames() {
        List<String> allUsernames = userMapper.getAllUsernames();
        Result res = new Result();
        res.setCode(200);
        res.setData(allUsernames);
        return res;
    }

    @PostMapping("/users")
    public Result createUser(@RequestBody User user) {
        String username = user.getUsername();
        if (userMapper.getCountByUsername(username) != 0) {
            return Result.failure("Username: " + username + " already exists.");
        } else {
            userMapper.insertUser(user);
            return Result.success("Username " + username + " Registered.");
        }
    }

    @DeleteMapping("/users")
    public Result deleteUser(@RequestBody User user) {
        String username = user.getUsername();
        if (userMapper.getCountByUsername(username) == 1) {
            String password = user.getPassword();
            String passwordInDatabase = userMapper.getPasswordByUsername(username);
            if (passwordInDatabase.equals(password)) {
                userMapper.deleteUser(username);
                return Result.success("Username: " + username + " deleted.");
            } else {
                return Result.failure("Username " + username + " password incorrect.");
            }
        } else {
            return Result.failure("Username " + username + " does not exists");
        }
    }
}
