package com.grovyn.chatbot.service;

import org.springframework.stereotype.Service;

import com.grovyn.chatbot.dto.RegisterRequest;
import com.grovyn.chatbot.entity.User;
import com.grovyn.chatbot.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository ;

	@Override
	public User registerUser(RegisterRequest request) {
	    User user = new User();
	    user.setUsername(request.getUsername());
	    user.setEmail(request.getEmail());
	    user.setPassword(request.getPassword()); // ✅ password now correctly set
	    user.setMobileNumber(request.getMobileNumber());
	    return userRepository.save(user);
	}

	@Override
	public User getUserByEmail(String email) {
	return userRepository.findByEmail(email)
				.orElseThrow(()->new RuntimeException("User not found"));
	}

}
