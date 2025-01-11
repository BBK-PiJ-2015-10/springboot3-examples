package tr.springcloudrabbitcsv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tr.springcloudrabbitcsv.entity.EligibilityRequest;
import tr.springcloudrabbitcsv.publisher.BrokerPublisher;

@RestController
public class MessageBrokerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final BrokerPublisher brokerPublisher;

    public MessageBrokerController(BrokerPublisher brokerPublisher) {
        this.brokerPublisher = brokerPublisher;
    }

    @PostMapping(path = "/publish/queue/{queueName}")
    public void publishMessage(@PathVariable("queueName") String queueName, @RequestBody EligibilityRequest eligibilityRequest) {
        logger.info("Controller received eligibility request to published message {} to queue: {}", eligibilityRequest, queueName);
        brokerPublisher.publishEligibilityRequest(eligibilityRequest);
        logger.info("Controller published eligibility request to queue: {}", queueName);
        brokerPublisher.publishEligibilityRequestFucker(eligibilityRequest);
        logger.info("Controller published eligibility request to queue fucker: {}", queueName);
    }


}
