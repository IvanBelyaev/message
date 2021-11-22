package com.example.message.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
public class Message implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    public Message(String text, User author) {
        this.text = text;
        this.author = author;
    }

    @Override
    public String getAuthority() {
        return "USER";
    }
}
