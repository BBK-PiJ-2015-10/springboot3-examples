package tr.springcloudrabbitcsv.config;


import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

//    private CachingConnectionFactory connectionFactory;
//
//    public RabbitMQConfig(CachingConnectionFactory connectionFactory) {
//        this.connectionFactory = connectionFactory;
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter converter(){
//        return new Jackson2JsonMessageConverter();
//    }

//    @Bean
//    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter jsonMessageConverter){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        //rabbitTemplate.setMessageConverter(jsonMessageConverter);
//        return rabbitTemplate;
//    }

    public static final String QUEUE_NAME = "culon";
    public static final String EXCHANGE_NAME = "amq.direct";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("culon");
    }

}
