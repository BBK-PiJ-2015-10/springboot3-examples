package tr.springcloudrabbitcsv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tr.springcloudrabbitcsv.entity.EligibilityRequest;
import tr.springcloudrabbitcsv.publisher.QueuePublisher;

@RestController
public class MessageBrokerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final QueuePublisher queuePublisher;


    public MessageBrokerController(QueuePublisher queuePublisher) {
        this.queuePublisher = queuePublisher;
    }

    @PostMapping(path = "/publish/queue")
    public void publishMessage(@RequestBody EligibilityRequest eligibilityRequest) {
        logger.info("Controller received eligibility request to published message {} to queue: {}", eligibilityRequest);
        queuePublisher.sendMessage(eligibilityRequest);
        //topicPublisher.publishEligibilityRequest(eligibilityRequest);
        logger.info("Controller published eligibility request to queue");
        //topicPublisher.publishEligibilityRequestFucker(eligibilityRequest);
        //logger.info("Controller published eligibility request to queue fucker: {}", queueName);
    }


}
