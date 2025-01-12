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

    public void sendMessage(EligibilityRequest request) {
        try {
            var jsonMessage = objectMapper.writeValueAsString(request);
            logger.info("Sending message: {}", jsonMessage);
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, QUEUE_NAME, jsonMessage);
        } catch (JsonProcessingException e) {
            logger.error("Error while converting object to json", e);
        }

    }

}
