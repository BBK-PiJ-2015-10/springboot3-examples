package tr.springcloudrabbitcsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// TODO:
// 1 - Change msg to json
// https://thepracticaldeveloper.com/produce-and-consume-json-messages-with-spring-boot-amqp/
// 2 - Read msg from queue and write to file

@SpringBootApplication
public class SpringCloudRabbitCsvApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRabbitCsvApplication.class, args);
    }

}
