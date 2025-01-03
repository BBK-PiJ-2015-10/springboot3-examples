package com.learning.springboot3.actors.actor;

import akka.actor.UntypedActor;
import com.learning.springboot3.actors.dto.Message;
import com.learning.springboot3.actors.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingActor extends UntypedActor {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private int count = 0;

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Message msg) {
            count = count + 1;
            var ms = "the new count is : " + count;
            var response = new Response(ms);
            logger.info("Greeting actor received {} count is {}", msg, count);
            sender().tell(response, self());
        }
    }
}
