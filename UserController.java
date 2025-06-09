package com.MainBus.Controller;

import com.MainBus.Model.User;
import com.MainBus.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginData) {
        boolean isValid = userService.validateUser(loginData.getPhoneNumber(), loginData.getPassword());
        return isValid
                ? ResponseEntity.ok("Login successful")
                : ResponseEntity.status(401).body("Invalid credentials");
    }
}

