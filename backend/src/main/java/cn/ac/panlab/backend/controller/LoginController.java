package cn.ac.panlab.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.ac.panlab.backend.model.Result;
import cn.ac.panlab.backend.model.User;
import cn.ac.panlab.backend.service.UserService;

@RestController
@CrossOrigin("${app.frontend.url}")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }
}
