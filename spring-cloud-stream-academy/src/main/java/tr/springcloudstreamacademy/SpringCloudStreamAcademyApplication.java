package tr.springcloudstreamacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudStreamAcademyApplication {

    /*  To run docker for rabbitmq:
        docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management

     */

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamAcademyApplication.class, args);
    }

}
