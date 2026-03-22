package com.grovyn.chatbot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grovyn.chatbot.dto.MessageResponseDTO;
import com.grovyn.chatbot.entity.Message;
import com.grovyn.chatbot.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    private final MessageService messageService;

    @PostMapping("/send/{sessionId}")
    @Operation(summary = "Send a message and get AI response")
    public ResponseEntity<MessageResponseDTO> sendMessage(
            @PathVariable Long sessionId,
            @RequestBody String userMessage) {

        log.info("Message received for sessionId: {}", sessionId);
        Message message = messageService.saveMessage(sessionId, userMessage);
        return ResponseEntity.ok(new MessageResponseDTO(
                message.getId(),
                message.getUserMessage(),
                message.getAiResponse(),
                message.getTime()));
    }

    @GetMapping("/history/{sessionId}")
    @Operation(summary = "Get full chat history for a session")
    public ResponseEntity<List<MessageResponseDTO>> getChatHistory(
            @PathVariable Long sessionId) {
        log.info("Fetching chat history for sessionId: {}", sessionId);
        List<MessageResponseDTO> history = messageService.getMessageBySession(sessionId)
                .stream()
                .map(msg -> new MessageResponseDTO(
                        msg.getId(),
                        msg.getUserMessage(),
                        msg.getAiResponse(),
                        msg.getTime()))
                .toList();
        return ResponseEntity.ok(history);
    }
}