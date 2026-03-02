package com.grovyn.chatbot.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatSessionDTO {
	private Long id;
	private String sessionName;
	private LocalDateTime createdAt;

}
