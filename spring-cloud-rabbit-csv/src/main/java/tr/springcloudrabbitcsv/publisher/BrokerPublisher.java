package tr.springcloudrabbitcsv.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;
import tr.springcloudrabbitcsv.entity.EligibilityRequest;

@Configuration
public class BrokerPublisher {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private StreamBridge streamBridge;

    public BrokerPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publishEligibilityRequest(EligibilityRequest eligibilityRequest) {
        logger.info("Publishing on demand: {}", eligibilityRequest);
        streamBridge.send("approvalRequest-out-0",eligibilityRequest);
    }


}
