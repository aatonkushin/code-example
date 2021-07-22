package org.tonkushin.example.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    @MessageMapping("/to-upper-case")
    @SendTo("/socket/to-upper-case")
    public SocketMessage toUpperCase(SocketMessage message) {
        String retVal = message.getContent() != null ? message.getContent().toUpperCase() : "";
        return new SocketMessage(retVal);
    }
}
