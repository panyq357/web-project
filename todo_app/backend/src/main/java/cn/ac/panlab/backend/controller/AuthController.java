package cn.ac.panlab.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.ac.panlab.backend.dto.LoginDTO;
import cn.ac.panlab.backend.dto.RegisterDTO;
import cn.ac.panlab.backend.dto.ResultDTO;
import cn.ac.panlab.backend.exception.AuthException;
import cn.ac.panlab.backend.service.AuthService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("${app.frontend.url}")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResultDTO login(@Valid @RequestBody LoginDTO dto) throws AuthException {

        String token = authService.login(dto);
        return ResultDTO.success("Login complete.", token);
    }

    @PostMapping("/register")
    public ResultDTO register(@Valid @RequestBody RegisterDTO dto) throws AuthException {

        authService.register(dto);
        return ResultDTO.success("Registration complete.");
    }

    // Currently, there's no logout methodo.
    // Logout is implemented simply in frontend (just delete jwt-token in LocalStorage).
}
