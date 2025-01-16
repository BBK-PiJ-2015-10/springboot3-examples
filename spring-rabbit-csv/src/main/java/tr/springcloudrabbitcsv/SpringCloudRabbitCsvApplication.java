package tr.springcloudrabbitcsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// TODO:
// https://thepracticaldeveloper.com/produce-and-consume-json-messages-with-spring-boot-amqp/
// 1 - Consume all messages from queue
// 2 - Save to local directory

@SpringBootApplication
public class SpringCloudRabbitCsvApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRabbitCsvApplication.class, args);
    }

}
