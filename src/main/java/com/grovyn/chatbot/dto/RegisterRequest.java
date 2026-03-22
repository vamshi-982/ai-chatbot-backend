package com.grovyn.chatbot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String mobileNumber;
}