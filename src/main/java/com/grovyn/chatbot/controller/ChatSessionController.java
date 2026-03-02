package com.grovyn.chatbot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grovyn.chatbot.entity.ChatSession;
import com.grovyn.chatbot.service.ChatSessionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class ChatSessionController {
	private final ChatSessionService chatSessionService;
	
	@PostMapping("/create/{userId}")
	public ChatSession createSession(@PathVariable Long userId) {
		return chatSessionService.createSession(userId);
				
	}

}
