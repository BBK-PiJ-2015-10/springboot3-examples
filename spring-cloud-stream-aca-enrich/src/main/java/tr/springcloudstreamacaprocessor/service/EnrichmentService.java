package tr.springcloudstreamacaprocessor.service;

import tr.springcloudstreamacaprocessor.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class EnrichmentService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public EnrichedTransaction enrichTransaction(Transaction transaction) {
        var trans = new EnrichedTransaction(transaction.id(), transaction.cashCard(), ApprovalStatus.APPROVED,
                new CardHolderData(UUID.randomUUID(), transaction.cashCard().owner(), "123 Main street"));
        logger.info("Enriched transaction {}", trans);
        return trans;
    }

}
