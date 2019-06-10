package com.project.ourtree.controller;

import com.project.ourtree.model.User;
import com.project.ourtree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.util.Base64;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id) throws SQLException {
        return userRepository.findById(id);
    }

    private boolean checkEmailUnicity(String email) {
        if (userRepository.findByEmail(email) != null) {
            return false;
        } else {
            return true;
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        String hashConvertedToString = Base64.getEncoder().encodeToString(hash);
        return hashConvertedToString;
    }

    @PostMapping("/postUser")
    public String postUser(@RequestBody User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String emailToCheck = user.getEmail();

        if (checkEmailUnicity(emailToCheck)) {
            user.setPassword(hashPassword(user.getPassword()));
            userRepository.save(user);
            return "user inserted";
        } else {
            return "an account already exists with this email";
        }
    }

    @PutMapping("/putUser/{id}")
    public String putUser(@RequestBody User user) {
        userRepository.save(user);
        return "user updated";
    }
}
