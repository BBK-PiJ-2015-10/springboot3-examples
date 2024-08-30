package com.learning.springboot3.actors.controller;

import com.learning.springboot3.actors.dto.Message;
import com.learning.springboot3.actors.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MessengerApi {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/messenger/v1")
    Mono<Response> sendMessage(@RequestBody Mono<Message> message) {
        return message.map(d -> logAndResponde(d));
    }

    Response logAndResponde(Message message) {
        logger.info("Received " + message);
        return new Response("ACA");
    }

}
