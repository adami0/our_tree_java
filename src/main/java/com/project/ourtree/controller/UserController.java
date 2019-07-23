package com.project.ourtree.controller;

import com.project.ourtree.model.User;
import com.project.ourtree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.util.Base64;

@CrossOrigin(origins = "http://localhost:30000")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //authentification check
    private boolean checkUser(String email, String password) {
        User userToCheck = userRepository.findByEmail(email);
        if (userToCheck == null) {
            return false;
        } else if (!(password.equals(userToCheck.getPassword()))) {
            return false;
        } else {
            return true;
        }
    }

    //email must be unique to register
    private boolean checkEmailUnicity(String email) {
        if (userRepository.findByEmail(email) != null) {
            return false;
        } else {
            return true;
        }
    }

    //hashing password with some salt
    private String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = new byte[12];
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        String hashConvertedToString = Base64.getEncoder().encodeToString(hash);
        return hashConvertedToString;
    }

    @CrossOrigin(origins = "http://localhost:3000")
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

    /**@GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id) throws SQLException {
        return userRepository.findById(id);
    }**/

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getUser/{email}")
    public User getUserByEmail(@PathVariable String email) throws SQLException {
        return userRepository.findByEmail(email);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public boolean login(@RequestBody User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String emailToVerify=user.getEmail();
        String passwordToVerify=user.getPassword();
        String hashedPasswordToVerify=hashPassword(passwordToVerify);

        boolean authStatus = checkUser(emailToVerify, hashedPasswordToVerify);

        if (authStatus) {
            return true;
        } else {
            return false;
        }
    }

    @PutMapping("/putUser/{id}")
    public String putUser(@RequestBody User user) {
        userRepository.save(user);
        return "user updated";
    }
}
