package cn.ac.panlab.backend.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cn.ac.panlab.backend.dto.LoginDTO;
import cn.ac.panlab.backend.dto.RegisterDTO;
import cn.ac.panlab.backend.exception.AuthException;
import cn.ac.panlab.backend.mapper.UserMapper;
import cn.ac.panlab.backend.model.User;
import cn.ac.panlab.backend.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String login(LoginDTO dto) throws AuthException {

        String username = dto.getUsername();
        String password = dto.getPassword();

        User user = userMapper.getUserByUsername(dto.getUsername());

        if (user == null) {
            throw new AuthException("User not found.");
        }

        String passwordInDb = user.getPassword();

        if (passwordInDb == null) {
            throw new AuthException("User password in database not found.");
        } else if (!passwordEncoder.matches(password, passwordInDb)) {
            throw new AuthException("Wrong password.");
        } else {
            return jwtUtil.generateToken(Map.of("username", username));
        }
    }

    public void register(RegisterDTO dto) throws AuthException {

        String username = dto.getUsername();

        if (userMapper.existsByUsername(username)) {
            throw new AuthException("Username already exists.");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        try {
            userMapper.insertUser(user);
        } catch(DuplicateKeyException e) {
            throw new AuthException("Username already exists.");
        }

    }
}
