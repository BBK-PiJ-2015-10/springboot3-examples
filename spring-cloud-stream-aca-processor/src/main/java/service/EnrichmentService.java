package service;

import domain.ApprovalStatus;
import domain.CardHolderData;
import domain.EnrichedTransaction;
import domain.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class EnrichmentService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public EnrichedTransaction enrichedTransaction(Transaction transaction) {
        var et = new EnrichedTransaction(transaction.id(), transaction.cashCard(), ApprovalStatus.APPROVED, new CardHolderData(UUID.randomUUID(), transaction.cashCard().owner(), "Berlin"));
        logger.info("Enriched transaction: {}", et);
        return et;

    }
}
