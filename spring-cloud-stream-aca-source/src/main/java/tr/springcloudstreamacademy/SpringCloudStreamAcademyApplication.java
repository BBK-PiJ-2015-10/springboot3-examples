package tr.springcloudstreamacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudStreamAcademyApplication {

    /* For rabbit:
       1 - Uncomment rabbit dependencies
       2 - Start rabbitmq like below
       To run docker for rabbitmq:
        docker run -d --hostname rabbit --name rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management

        login into http://localhost:15672/
        username: guest
        password: guest

        create queue for binding

        docker exec -it {rabbit-mq-container} sh -c "rabbitmqadmin declare queue name=test-queue && rabbitmqadmin declare binding source=approvalRequest-out-0 destination=test-queue routing_key=#"

        3 - Run the application

        For Kafka:
        1- Uncomment Kafka dependencies
        2- run docker-compose up -d
        3 - To see messages being published:
         docker exec -it {kafka-container-id} /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic approvalRequest-out-0

        optional - To publish a message:
        docker exec -it {kafka-container-id} /opt/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic approvalRequest-out-0

        3 - Run the application

        Sunday:  On Demand processing with StreamBridge
        Monday: Enrich with data enrich

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
