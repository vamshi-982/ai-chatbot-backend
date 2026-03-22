package com.grovyn.chatbot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
@Column(columnDefinition="TEXT")
private String userMessage;

@Column(columnDefinition="TEXT")
private String aiResponse;

private LocalDateTime time;

@ManyToOne
@JoinColumn(name="session_id")
private ChatSession chatSession;
}
