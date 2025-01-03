package com.learning.springboot3.actors.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.learning.springboot3.actors.actor.GreetingActor;
import com.learning.springboot3.actors.actor.configs.SpringExtension;
import com.learning.springboot3.actors.dto.Message;
import com.learning.springboot3.actors.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import scala.concurrent.impl.Promise;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

@RestController
public class MessengerApi {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ActorRef greetingActor;

    public MessengerApi(ActorSystem actorSystem) {
        this.greetingActor = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER
                .get(actorSystem).props("greetingActor"), "greeter");
    }

    @PostMapping("/messenger/v1")
    Mono<Response> sendMessage(@RequestBody Mono<Message> message) {
        return message.flatMap(d -> logAndResponse(d));
    }

    Mono<Response> logAndResponse(Message message) {
        logger.info("Received " + message);
        FiniteDuration duration = FiniteDuration.create(10, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);

        Future<Object> result = ask(greetingActor, message, timeout);
        CompletableFuture<Object> cu
                = CompletableFuture.supplyAsync(() -> result);
        CompletableFuture<Response> responseCF = cu.thenApply(c -> mapToResponse(c));

        //CompletableFuture<Response> responseCF = cu.thenApply(c -> mapToResponseP(c)
        //);


        return Mono.fromFuture(responseCF);
        //return object.map(o -> mapToResponseP(o));
    }

    private Response mapToResponse(Object response) {
        if (response instanceof Response msg) {
            logger.info("YES");
            return msg;
        } else {
            var notGood = response.getClass().getName();
            logger.error("fucker {}", notGood);
            return new Response("FUCKER");
        }
    }

    private Response mapToResponseP(Object resp) {
        Promise<Object> cat = (Promise<Object>) resp;
        var response = cat.value().get().get();
        if (response instanceof Response msg) {
            logger.info("YES");
            return msg;
        } else {
            var notGood = response.getClass().getName();
            logger.error("fucker {}", notGood);
            return new Response("FUCKER");
        }
    }

}
