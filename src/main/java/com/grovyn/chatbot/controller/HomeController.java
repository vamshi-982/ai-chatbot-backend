package com.grovyn.chatbot.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

	@GetMapping("/")
	public String home() {
		return "Backend Chatbot running successfully";
	}
}
