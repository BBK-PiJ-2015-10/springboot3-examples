package tr.springcloudrabbitcsv.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tr.springcloudrabbitcsv.entity.EligibilityRequest;

import static tr.springcloudrabbitcsv.config.RabbitMQConfig.EXCHANGE_NAME;
import static tr.springcloudrabbitcsv.config.RabbitMQConfig.QUEUE_NAME;

@Service
public class QueueAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QueueAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String queueName, EligibilityRequest eligibilityRequest) {
        var validQueue = queueName.equals(QUEUE_NAME);
        if (!validQueue) {
            logger.error("Invalid queue name: {}", queueName);
            return;
        }
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, QUEUE_NAME, eligibilityRequest);
        logger.info("Message {} sent to queue: {}", eligibilityRequest, queueName);
    }

    public void fetchMessages(String queueName) {
        var validQueue = queueName.equals(QUEUE_NAME);
        if (!validQueue) {
            logger.error("Invalid queue name: {}", queueName);
            return;
        }
        var response = rabbitTemplate.receiveAndConvert(queueName);
        var eligibilityRequest = objectMapper.convertValue(response, EligibilityRequest.class);
        logger.info("Consumed {}", eligibilityRequest);
    }

}
