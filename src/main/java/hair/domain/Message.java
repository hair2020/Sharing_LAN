package hair.domain;

import java.time.LocalDateTime;

public class Message {
    private String sender;
    private String content;
    private LocalDateTime timestamp;
    public String getSender() {
        return sender;
    }
    public String getContent() {
        return content;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    
}
