package com.grovyn.chatbot.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grovyn.chatbot.dto.MessageResponseDTO;
import com.grovyn.chatbot.entity.Message;
import com.grovyn.chatbot.service.MessageService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final MessageService messageService;

    @PostMapping("/send/{sessionId}")
    public MessageResponseDTO sendMessage(
            @PathVariable Long sessionId,
            @RequestBody String userMessage) {
    	Message message=messageService.saveMessage(sessionId, userMessage);
    	return new MessageResponseDTO(
    			message.getId(),
    			message.getUserMessage(),
    			message.getAiResponse(),
    			message.getTime());
        
    }

    @GetMapping("/history/{sessionId}")
    public List<MessageResponseDTO> getChatHistory(@PathVariable Long sessionId) {
        return messageService.getMessageBySession(sessionId)
                .stream()
                .map(msg -> new MessageResponseDTO(
                        msg.getId(),
                        msg.getUserMessage(),
                        msg.getAiResponse(),
                        msg.getTime()))
                .toList();
    }
}