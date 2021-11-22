package com.example.message.service;

import com.example.message.dto.MessageDTO;
import com.example.message.model.Message;
import com.example.message.model.User;
import com.example.message.repository.MessageRepository;
import com.example.message.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<MessageDTO> getMessages(MessageDTO messageDTO) {
        int amountOfMessages = Integer.parseInt(messageDTO.getMessage().split(" ")[1]);
        Pageable lastAmount = PageRequest.of(0, amountOfMessages);
        User user = userRepository.findByName(messageDTO.getName()).get();
        List<MessageDTO> messages = messageRepository.findByAuthorOrderByCreated(user, lastAmount).stream()
                .map(m -> new MessageDTO(m.getAuthor().getName(), m.getText()))
                .collect(Collectors.toList());
        return messages;
    }

    @Transactional
    public MessageDTO saveMessage(MessageDTO messageDTO) {
        User user = userRepository.findByName(messageDTO.getName()).get();
        Message message = new Message(messageDTO.getMessage(), user);
        Message savedMessage = messageRepository.save(message);
        return new MessageDTO(message.getAuthor().getName(), message.getText());
    }
}
