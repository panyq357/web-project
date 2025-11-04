package cn.ac.panlab.backend.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {

    @Autowired
    JwtUtil jwtUtil;

    @Test
    void testGenerateAndParseToken() {

        System.out.println("--- TEST ---");

        Map<String, Object> map = new HashMap<>();

        map.put("name", "Tom");
        map.put("email", "tom@tom.com");

        String jwt = jwtUtil.generateToken(map);

        System.out.println(jwt);

        Map<String, Object> payload = jwtUtil.parseToken(jwt);

        payload.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("--- TEST ---");
    }
}

