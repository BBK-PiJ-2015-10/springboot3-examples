package tr.springcloudrabbitcsv.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import tr.springcloudrabbitcsv.entity.EligibilityRequest;

import static tr.springcloudrabbitcsv.config.RabbitMQConfig.EXCHANGE_NAME;
import static tr.springcloudrabbitcsv.config.RabbitMQConfig.QUEUE_NAME;


@Service
public class QueuePublisher {

    private RabbitTemplate rabbitTemplate;

    public QueuePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(EligibilityRequest request){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,QUEUE_NAME,request.toString());
    }

}
