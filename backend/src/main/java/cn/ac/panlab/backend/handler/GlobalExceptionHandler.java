package cn.ac.panlab.backend.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.ac.panlab.backend.dto.ResultDTO;
import cn.ac.panlab.backend.exception.AuthException;
import cn.ac.panlab.backend.exception.UserException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResultDTO handleAuthException(Exception ex) {
        ex.printStackTrace();
        return ResultDTO.failure(ex.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public ResultDTO handleUserException(Exception ex) {
        ex.printStackTrace();
        return ResultDTO.failure(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultDTO handleException(Exception ex) {
        ex.printStackTrace();
        return ResultDTO.failure("Internal server error: " + ex.getMessage());
    }
}
