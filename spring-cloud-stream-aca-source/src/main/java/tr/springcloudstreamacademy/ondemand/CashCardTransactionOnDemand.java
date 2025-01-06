package tr.springcloudstreamacademy.ondemand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;
import tr.springcloudstreamacademy.domain.Transaction;

@Configuration
public class CashCardTransactionOnDemand {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private StreamBridge streamBridge;

    // StreamBridge is automatically configured as a bean by the Spring Cloud Stream framework
    public CashCardTransactionOnDemand(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publishOnDemand(Transaction transaction) {
        logger.info("Publishing on demand: {}", transaction);
        streamBridge.send("approvalRequest-out-0",transaction);
    }


}
