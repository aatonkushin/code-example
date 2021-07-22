package org.tonkushin.example.socket;

import lombok.Data;

@Data
public class SocketMessage {
    private String content;

    public SocketMessage() {
    }

    public SocketMessage(String content) {
        this.content = content;
    }
}
