package cn.ac.panlab.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ac.panlab.backend.dto.ResultDTO;
import cn.ac.panlab.backend.exception.UserException;
import cn.ac.panlab.backend.service.UserService;

@RestController
@CrossOrigin("${app.frontend.url}")
public class UserController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/users/{username}")
    public ResultDTO deleteUser(@RequestParam String username) throws UserException {
        userService.deleteUserByUsername(username);
        return ResultDTO.success("User deletion complete.");
    }
}
