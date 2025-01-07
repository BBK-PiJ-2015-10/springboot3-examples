package controller;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tr.springcloudstreamacademy.domain.CashCard;
import tr.springcloudstreamacademy.domain.Transaction;
import tr.springcloudstreamacademy.ondemand.CashCardTransactionOnDemand;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import({TestChannelBinderConfiguration.class, CashCardTransactionOnDemand.class})
public class CashCardControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void postShouldSendTransactionAsAMessage() throws IOException {
        Transaction postedTransaction = new Transaction(123L, new CashCard(1L, "kumar2", 1.00));
        ResponseEntity<Transaction> response = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/publish/txn",
                postedTransaction, Transaction.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @SpringBootApplication
    public static class App {

    }





}
