package tr.springcloudstreamacaprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
   To run pass env variable:
   spring.cloud.stream.bindings.enrichTransaction-in-0.destination=approvalRequest-out-0
 */


@SpringBootApplication
public class SpringCloudStreamAcaProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamAcaProcessorApplication.class, args);
	}

}
