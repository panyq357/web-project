package cn.ac.panlab.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ac.panlab.backend.context.UserContext;
import cn.ac.panlab.backend.exception.UserException;
import cn.ac.panlab.backend.mapper.UserMapper;
import cn.ac.panlab.backend.model.User;

@Component
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void deleteUserByUsername(String username) throws UserException {

        User currentUser = UserContext.get();

        if (currentUser == null || !username.equals(currentUser.getUsername())) {
            throw new UserException("User can only delete itself.");
        }

        if (!userMapper.existsByUsername(username)) {
            throw new UserException("User not found when deleting user");
        }

        userMapper.deleteUserByUsername(username);
    }
}
