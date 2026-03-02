package com.grovyn.chatbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grovyn.chatbot.entity.ChatSession;
import com.grovyn.chatbot.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message>
	findTop10ByChatSessionOrderByTimeDesc(ChatSession chatSession);
    List<Message> findByChatSessionOrderByTimeAsc(ChatSession chatSession);

}

