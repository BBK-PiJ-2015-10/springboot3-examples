package tr.springcloudrabbitcsv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @PostMapping(path = "/publish/message")
    public void publishMessage(@RequestBody EligibilityRequest eligibilityRequest) {
        logger.info("Controller received eligibility request: {}", eligibilityRequest);
        brokerPublisher.publishEligibilityRequest(eligibilityRequest);
        logger.info("Controller published eligibility request: {}", eligibilityRequest);
    }


}
