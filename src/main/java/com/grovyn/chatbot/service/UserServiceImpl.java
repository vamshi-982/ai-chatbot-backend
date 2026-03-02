package com.grovyn.chatbot.service;

import org.springframework.stereotype.Service;

import com.grovyn.chatbot.entity.User;
import com.grovyn.chatbot.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository ;

	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserByEmail(String email) {
	return userRepository.findByEmail(email)
				.orElseThrow(()->new RuntimeException("User not found"));
	}

}
