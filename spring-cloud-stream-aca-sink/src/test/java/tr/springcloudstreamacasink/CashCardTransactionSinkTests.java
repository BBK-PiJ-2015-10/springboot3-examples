package tr.springcloudstreamacasink;


import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import tr.springcloudstreamacasink.domain.*;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

@SpringBootTest
@ImportAutoConfiguration(TestChannelBinderConfiguration.class)
@ExtendWith(OutputCaptureExtension.class)
public class CashCardTransactionSinkTests {

    private static final int AWAIT_DURATION = 10;

    @Test
    void cashCardSinkToConsole(@Autowired InputDestination inputDestination, CapturedOutput output) throws IOException {

        // Set up the expected data
        Transaction transaction = new Transaction(1L, new CashCard(123L, "Kumar Patel", 1.00));
        EnrichedTransaction enrichedTransaction = new EnrichedTransaction(
                transaction.id(),
                transaction.cashCard(),
                ApprovalStatus.APPROVED,
                new CardHolderData(UUID.randomUUID(), transaction.cashCard().owner(), "123 Main Street"));

        // Send the message to the console sink's input topic
        Message<EnrichedTransaction> message = MessageBuilder.withPayload(enrichedTransaction).build();
        inputDestination.send(message, "sinkToConsole-in-0");

        // Wait for, then test the console output
        Awaitility.await().atMost(Duration.ofSeconds(AWAIT_DURATION))
                .until(() -> output.toString().contains("Transaction Received: " + enrichedTransaction.toString()));

    }

    @SpringBootApplication
    public static class App {

    }


}
