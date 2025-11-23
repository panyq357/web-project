package cn.ac.panlab.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ac.panlab.backend.context.UserContext;
import cn.ac.panlab.backend.exception.UserException;
import cn.ac.panlab.backend.mapper.UserMapper;

@Component
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void deleteUserByUsername(String username) throws UserException {

        String currentUsername = UserContext.get();

        if (currentUsername == null || !username.equals(currentUsername)) {
            throw new UserException("User can only delete itself.");
        }

        if (userMapper.existsByUsername(username) == null) {
            throw new UserException("User not found when deleting user");
        }

        userMapper.deleteUserByUsername(username);
    }
}
