package cn.ac.panlab.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.ac.panlab.backend.model.Result;
import cn.ac.panlab.backend.model.User;
import cn.ac.panlab.backend.service.UserService;

@RestController
@CrossOrigin("http://localhost:8000/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Result getAllUsernames() {
        return userService.getAllUserNames();
    }

    @PostMapping("/users")
    public Result createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/users")
    public Result deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }
}
