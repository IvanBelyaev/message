package com.example.message.controller;

import com.example.message.dto.LoginDTO;
import com.example.message.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegistrationController {
    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Void> registration(
            @Valid @RequestBody LoginDTO loginDTO) {
        userService.registerUser(loginDTO);
        return ResponseEntity.ok().build();
    }
}
