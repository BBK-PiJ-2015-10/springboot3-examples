package tr.springcloudrabbitcsv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tr.springcloudrabbitcsv.entity.EligibilityRequest;
import tr.springcloudrabbitcsv.file.CsvFileReader;
import tr.springcloudrabbitcsv.publisher.QueuePublisher;

@RestController
public class MessageBrokerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final QueuePublisher queuePublisher;

    private final CsvFileReader csvFileReader;


    public MessageBrokerController(QueuePublisher queuePublisher, CsvFileReader csvFileReader) {
        this.queuePublisher = queuePublisher;
        this.csvFileReader = csvFileReader;
    }

    @PostMapping(path = "/publish/queue")
    public void publishMessage(@RequestBody EligibilityRequest eligibilityRequest) {
        logger.info("Controller received eligibility request to published message {} to queue: {}", eligibilityRequest);
        var requests = csvFileReader.readCsvFile();
        requests.forEach(queuePublisher::sendMessage);
        logger.info("Controller published eligibility request to queue");
    }


}
