package tr.springcloudstreamacademy.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.springcloudstreamacademy.domain.Transaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CashCardController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @PostMapping(path = "/publish/txn")
    //public void publishTxn(@RequestBody Culon transaction) {
        public void publishTxn(@RequestBody Transaction transaction) {
        System.out.println("POST for Transaction: " + transaction);
    }

//    @PostMapping(path = "/publish/txn")
//    public void publishTxn(@RequestBody Transaction transaction){
//        logger.info("Controller received transaction: {}", transaction);
//    }

}
