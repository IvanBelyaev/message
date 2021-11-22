package com.example.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class MessageDTO {
    @NotBlank(message = "name must not be empty")
    private String name;

    @NotBlank(message = "message must not be empty")
    private String message;
}
