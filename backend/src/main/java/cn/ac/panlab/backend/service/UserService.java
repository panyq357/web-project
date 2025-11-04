package cn.ac.panlab.backend.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ac.panlab.backend.mapper.UserMapper;
import cn.ac.panlab.backend.model.Result;
import cn.ac.panlab.backend.model.User;
import cn.ac.panlab.backend.util.JwtUtil;

@Component
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtil jwtUtil;

    public Result login(User user) {

        String username = user.getUsername();
        String password = user.getPassword();

        String passwordInDb = userMapper.getPasswordByUsername(user.getUsername());

        if (password.equals(passwordInDb)) {
            return Result.success("Login success.", jwtUtil.generateToken(Map.of("username", username)));
        } else {
            return Result.failure("User not found or password mismatch.");
        }
    }

    public Result getAllUserNames() {
        return Result.success("Get all username success.", userMapper.getAllUsernames());
    }

    public Result createUser(User user) {

        String username = user.getUsername();
        if (userMapper.getCountByUsername(username) != 0) {
            return Result.failure("Username: " + username + " already exists.");
        } else {
            userMapper.insertUser(user);
            return Result.success("Username " + username + " Registered.");
        }
    }

    public Result deleteUser(User user) {

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
