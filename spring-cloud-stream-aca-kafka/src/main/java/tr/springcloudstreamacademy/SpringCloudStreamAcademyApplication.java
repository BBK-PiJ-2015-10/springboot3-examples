package tr.springcloudstreamacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudStreamAcademyApplication {

    /*  To run docker for rabbitmq:
        docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management

        For Kafka:
        1- run docker-compose up -d
        2 - To see messages being published:
         docker exec -it {kafka-container-id} /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic approvalRequest-out-0

        3 - To publish a message:
        docker exec -it {kafka-container-id} /opt/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic approvalRequest-out-0


     */
    /*
       TODO:
        1 - Run Kafka locally, check example on scala
        2 - Go to STEP 8 - Run the application

     */
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamAcademyApplication.class, args);
    }

}
