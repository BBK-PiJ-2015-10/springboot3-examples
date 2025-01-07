package tr.springcloudstreamacasink.sink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tr.springcloudstreamacasink.domain.EnrichedTransaction;

import java.util.function.Consumer;

@Configuration
public class CashCardTransactionSink {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Bean
    public Consumer<EnrichedTransaction> sinkToConsole() {
        return enrichedTransaction -> {
           // logger.info("Sinking Enriched Transaction: {}", enrichedTransaction);
            System.out.println("Transaction Received: " + enrichedTransaction);
        };
    }

}
