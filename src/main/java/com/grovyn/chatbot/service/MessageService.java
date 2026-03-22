package com.grovyn.chatbot.service;

import java.util.List;

import com.grovyn.chatbot.entity.Message;

public interface MessageService {
	Message saveMessage(Long sessionId,String userMessage);
	List<Message> getMessageBySession(Long sessionId);
}
