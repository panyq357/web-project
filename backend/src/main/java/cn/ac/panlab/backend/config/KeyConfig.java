package cn.ac.panlab.backend.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyConfig {

    @Bean
    public SecretKey jwtKey() throws NoSuchAlgorithmException, IOException {

        Path jwtKeyPath = Paths.get(System.getProperty("user.home") + "/.jwtKey");

        SecretKey jwtKey;

        if (Files.exists(jwtKeyPath)) {
            String encodedKey = Files.readString(jwtKeyPath).trim();

            byte[] decodedKey = Base64.getDecoder().decode(encodedKey);

            jwtKey = new SecretKeySpec(decodedKey, "HmacSHA256");
        } else {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256);
            jwtKey = keyGen.generateKey();

            String encodedKey = Base64.getEncoder().encodeToString(jwtKey.getEncoded());
            Files.writeString(jwtKeyPath, encodedKey);
        }

        return jwtKey;
    }
}
