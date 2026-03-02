package com.grovyn.chatbot.service;

import org.springframework.stereotype.Service;

import com.grovyn.chatbot.entity.ChatSession;
import com.grovyn.chatbot.entity.User;
import com.grovyn.chatbot.repository.ChatSessionRepository;
import com.grovyn.chatbot.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ChatSessionServiceImpl implements ChatSessionService {
	private final ChatSessionRepository chatSessionRepository;
	private final UserRepository userRepository;

	@Override
	public ChatSession createSession(Long userId) {
		User user=userRepository.findById(userId)
				.orElseThrow(()->new RuntimeException("user not found"));
		ChatSession session=new ChatSession();
		session.setSessionName("New Chat");
		session.setUser(user);
		
		return chatSessionRepository.save(session);
	}

}
