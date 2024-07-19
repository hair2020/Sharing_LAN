package hair.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hair.domain.Message;
import hair.service.ChatService;

@RestController
public class ChatController {
    private final ChatService chatService;
    private final AtomicInteger userCounter = new AtomicInteger(0);

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(@RequestBody Message message) {
        // System.out.println("Controller received message: " + message);
        return chatService.sendMessage(message);
    }

    @GetMapping("/messages")
    public List<Message> getRecentMessages() {
        return chatService.getRecentMessages();
    }

    @GetMapping("username")
    public String getUserName() {
        return "User-" + userCounter.incrementAndGet();
    }
}
