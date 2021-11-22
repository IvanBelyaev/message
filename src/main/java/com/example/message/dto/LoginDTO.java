package com.example.message.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {
    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "password must not be empty")
    private String password;
}
