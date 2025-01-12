package tr.springcloudrabbitcsv.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tr.springcloudrabbitcsv.entity.EligibilityRequest;

import static tr.springcloudrabbitcsv.config.RabbitMQConfig.EXCHANGE_NAME;
import static tr.springcloudrabbitcsv.config.RabbitMQConfig.QUEUE_NAME;


@Service
public class QueuePublisher {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper;

    public QueuePublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String queueName, EligibilityRequest eligibilityRequest) {
        var validQueue = queueName.equals(QUEUE_NAME);
        if (!validQueue) {
            logger.error("Invalid queue name: {}", queueName);
            return;
        }
        try {
            var jsonRequest = objectMapper.writeValueAsString(eligibilityRequest);
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, QUEUE_NAME, jsonRequest);
            logger.info("Message {} sent to queue: {}", jsonRequest, queueName);
        } catch (JsonProcessingException e) {
            logger.error("Error converting object to json: {}", e.getMessage());
        }
    }

}
