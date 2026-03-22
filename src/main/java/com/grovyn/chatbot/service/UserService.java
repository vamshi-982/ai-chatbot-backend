package com.grovyn.chatbot.service;

import com.grovyn.chatbot.dto.RegisterRequest;
import com.grovyn.chatbot.entity.User;

public interface UserService {
	User registerUser(RegisterRequest request);
	User getUserByEmail(String email);

}
