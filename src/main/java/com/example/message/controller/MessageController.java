package com.example.message.controller;

import com.example.message.dto.MessageDTO;
import com.example.message.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages")
    public ResponseEntity<?> processMessage(@RequestBody MessageDTO messageDTO) {
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!principal.equals(messageDTO.getName())) {
            throw new IllegalArgumentException("log in as " + messageDTO.getName());
        }
        if (messageDTO.getMessage().matches("history \\d+")) {
            return ResponseEntity.ok(messageService.getMessages(messageDTO));
        }
        return ResponseEntity.ok(messageService.saveMessage(messageDTO));
    }

}
