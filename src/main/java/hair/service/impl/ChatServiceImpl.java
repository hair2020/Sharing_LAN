package hair.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import hair.domain.Message;
import hair.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {
    private final List<Message> messages = new ArrayList<>();
    private static int MAX_MESSAGE_LENGTH = 100; 
    @Override
    public Message sendMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        messages.add(message);

        if (messages.size() == MAX_MESSAGE_LENGTH) {
            messages.subList(0, 80).clear();
        }

        // System.out.println("Service received message: " + message);
        return message;
    }

    @Override
    public List<Message> getRecentMessages() {
        return messages;
    }

}
