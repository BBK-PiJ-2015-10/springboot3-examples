package tr.springcloudstreamacademy.service;

import tr.springcloudstreamacademy.domain.CashCard;
import tr.springcloudstreamacademy.domain.Transaction;

import java.util.Random;

public class DataSourceService {

    public Transaction getData() {
        CashCard cashCard = new CashCard(new Random().nextLong(),"" +
                "sarah1", new Random().nextDouble());
        return new Transaction(new Random().nextLong(), cashCard);
    }

}
