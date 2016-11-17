package kohl.hadrien.console.spring;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BindingsController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String send(String message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return time + " -> " + message;
    }
}
