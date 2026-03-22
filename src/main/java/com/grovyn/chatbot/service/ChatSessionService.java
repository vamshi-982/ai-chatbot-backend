package com.grovyn.chatbot.service;

import com.grovyn.chatbot.entity.ChatSession;

public interface ChatSessionService {
	ChatSession createSession(Long userId);

}
