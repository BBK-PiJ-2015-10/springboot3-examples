package tr.springcloudstreamacademy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.springcloudstreamacademy.domain.CashCard;
import tr.springcloudstreamacademy.domain.Transaction;

import java.util.Random;

public class DataSourceService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public Transaction getData() {
        CashCard cashCard = new CashCard(new Random().nextLong(),"" +
                "sarah1", new Random().nextDouble());
        var transaction = new Transaction(new Random().nextLong(), cashCard);
        logger.info("Generated transaction: {}", transaction);
        return transaction;
    }

}
