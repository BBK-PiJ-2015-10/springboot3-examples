package tr.springcloudstreamacaprocessor.processor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tr.springcloudstreamacaprocessor.domain.EnrichedTransaction;
import tr.springcloudstreamacaprocessor.domain.Transaction;
import tr.springcloudstreamacaprocessor.service.EnrichmentService;

import java.util.function.Function;


@Configuration
public class CashCardTransactionProcessor {
    
    // Consumes from enrichTransaction-in-0
    // Publishes to enrichTransaction-out-0
    @Bean
    public Function<Transaction, EnrichedTransaction> enrichTransaction(EnrichmentService enrichmentService) {
        return transaction -> {
            return enrichmentService.enrichTransaction(transaction);
        };
    }

    @Bean
    public EnrichmentService enrichmentService() {
        return new EnrichmentService();
    }

}
