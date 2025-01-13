package tr.springcloudrabbitcsv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping(path = "/queue/{queueName}")
    public void publishMessage(@PathVariable("queueName") String queueName) {
        logger.info("Controller received eligibility request to published messages to queue: {}", queueName);
        var requests = csvFileReader.readCsvFile();
        requests.forEach(er -> queuePublisher.sendMessage(queueName, er));
        logger.info("Controller published eligibility request to queue");
    }

    @GetMapping(path = "/queue/{queueName}")
    public void fetchMessages(@PathVariable("queueName") String queueName) {
        logger.info("Controller received request to consume messages to queue: {}", queueName);
        queuePublisher.fetchMessages(queueName);
        logger.info("Controller processed eligibility request to consume from queue {}", queueName);
    }

}
