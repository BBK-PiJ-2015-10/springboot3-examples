package tr.springcloudstreamacademy.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.springcloudstreamacademy.domain.Transaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tr.springcloudstreamacademy.ondemand.CashCardTransactionOnDemand;

@RestController
public class CashCardController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private CashCardTransactionOnDemand cashCardTransactionOnDemand;

    public CashCardController(CashCardTransactionOnDemand cashCardTransactionOnDemand) {
        this.cashCardTransactionOnDemand = cashCardTransactionOnDemand;
    }

    @PostMapping(path = "/publish/txn")
    public void publishTxn(@RequestBody Transaction transaction) {
        logger.info("Controller received transaction: {}", transaction);
        cashCardTransactionOnDemand.publishOnDemand(transaction);
    }

}
