package hair.service;

import java.util.List;

import hair.domain.Message;

public interface ChatService {
    Message sendMessage(Message message);
    
    List<Message> getRecentMessages();
}
