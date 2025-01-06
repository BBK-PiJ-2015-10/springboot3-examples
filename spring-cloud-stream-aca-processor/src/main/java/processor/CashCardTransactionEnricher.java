package processor;

import domain.EnrichedTransaction;
import domain.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.EnrichmentService;

import java.util.function.Function;

@Configuration
public class CashCardTransactionEnricher {

    @Bean
    EnrichmentService enrichmentService(){
        return new EnrichmentService();
    }

    // Spring Cloud Stream utilizes the java.util.function.Function interface for processors.
    // enrichTransaction-in-0 and enrichTransaction-out-0 for the Processor bean method name enrichTransaction.
    @Bean
    public Function<Transaction, EnrichedTransaction> enrichTransaction(EnrichmentService enrichmentService) {
        return transaction -> {
            return enrichmentService.enrichedTransaction(transaction);
        };
    }


}
