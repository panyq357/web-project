package cn.ac.panlab.backend.filter;

import java.io.IOException;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;

import cn.ac.panlab.backend.context.UserContext;
import cn.ac.panlab.backend.mapper.UserMapper;
import cn.ac.panlab.backend.model.User;
import cn.ac.panlab.backend.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*")
public class JwtAuthFilter implements Filter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserMapper userMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            if (token != null && !token.isBlank()) {
                Claims claims = jwtUtil.parseToken(token);
                String username = (String) claims.get("username");
                User user = userMapper.getUserByUsername(username);
                UserContext.set(user);
            }
            chain.doFilter(request, response);
        } catch(JwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
        } finally {
            UserContext.clear();
        }
    }
}
