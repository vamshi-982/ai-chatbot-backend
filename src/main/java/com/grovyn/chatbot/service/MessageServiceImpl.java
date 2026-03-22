package com.grovyn.chatbot.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.grovyn.chatbot.ai.AIService;
import com.grovyn.chatbot.entity.ChatSession;
import com.grovyn.chatbot.entity.Message;
import com.grovyn.chatbot.repository.ChatSessionRepository;
import com.grovyn.chatbot.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatSessionRepository chatSessionRepository;
    private final AIService aiService;

    @Override
    public Message saveMessage(Long sessionId, String userMessage) {

        ChatSession session = chatSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        List<Message> history =
                messageRepository
                .findTop10ByChatSessionOrderByTimeDesc(session);

        Collections.reverse(history);

        // Build context
        String prompt = buildContext(history, userMessage);

        //  Call AI with context
        String aiReply = aiService.getAIResponse(prompt);

        // Save message
        Message message = new Message();
        message.setUserMessage(userMessage);
        message.setAiResponse(aiReply);
        message.setTime(LocalDateTime.now());
        message.setChatSession(session);
        return messageRepository.save(message);
    }
    private String buildContext(
            List<Message> history,
            String newMessage) {

        StringBuilder context = new StringBuilder();

        for (Message msg : history) {

            context.append("User: ")
                   .append(msg.getUserMessage())
                   .append("\n");

            context.append("AI: ")
                   .append(msg.getAiResponse())
                   .append("\n");
        }

        context.append("User: ")
               .append(newMessage);

        return context.toString();
    }


    @Override
    public List<Message> getMessageBySession(Long sessionId) {

        ChatSession session = chatSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        return messageRepository.findByChatSessionOrderByTimeAsc(session);
    }

	
}