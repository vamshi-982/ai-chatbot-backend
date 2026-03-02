package com.grovyn.chatbot.service;

import com.grovyn.chatbot.entity.User;

public interface UserService {
	User registerUser(User user);
	User getUserByEmail(String email);

}
