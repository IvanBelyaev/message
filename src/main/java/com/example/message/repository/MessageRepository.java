package com.example.message.repository;

import com.example.message.model.Message;
import com.example.message.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByAuthorOrderByCreated(User user, Pageable pageable);
}
