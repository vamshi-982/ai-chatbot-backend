package com.grovyn.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grovyn.chatbot.dto.ChatSessionDTO;
import com.grovyn.chatbot.entity.ChatSession;
import com.grovyn.chatbot.service.ChatSessionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ChatSessionController {

    private static final Logger log = LoggerFactory.getLogger(ChatSessionController.class);
    private final ChatSessionService chatSessionService;

    @PostMapping("/create/{userId}")
    @Operation(summary = "Create a new chat session for a user")
    public ResponseEntity<ChatSessionDTO> createSession(@PathVariable Long userId) {
        log.info("Creating new session for userId: {}", userId);
        ChatSession session = chatSessionService.createSession(userId);
        return ResponseEntity.ok(new ChatSessionDTO(
                session.getId(),
                session.getSessionName(),
                session.getCreatedAt()));
    }
}