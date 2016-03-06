package com.daggerok.spring.streaming.msg;

import com.daggerok.spring.streaming.msg.dto.Request;
import com.daggerok.spring.streaming.msg.dto.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessagesCtrl {
    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public Response sendMessage(Request request) {
        return Response.of(request.transform());
    }
}
