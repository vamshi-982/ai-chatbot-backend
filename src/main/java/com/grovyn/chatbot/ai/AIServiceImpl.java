package com.grovyn.chatbot.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AIServiceImpl implements AIService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${groq.api.key}")
    private String apiKey;

    public AIServiceImpl(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://api.groq.com")
                .build();
    }

    @Override
    public String getAIResponse(String message) {

        try {
            // Build request body safely using ObjectMapper (no JSON injection issues)
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("model", "llama-3.3-70b-versatile");

            ArrayNode messages = objectMapper.createArrayNode();
            ObjectNode userMessage = objectMapper.createObjectNode();
            userMessage.put("role", "user");
            userMessage.put("content", message);
            messages.add(userMessage);

            requestBody.set("messages", messages);

            String rawResponse = webClient.post()
                    .uri("/openai/v1/chat/completions")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .bodyValue(requestBody.toString())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // Extract only the answer text
            JsonNode root = objectMapper.readTree(rawResponse);
            return root.path("choices")
                       .get(0)
                       .path("message")
                       .path("content")
                       .asText();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}