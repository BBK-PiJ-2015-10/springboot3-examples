package tr.springcloudstreamacademy.stream;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tr.springcloudstreamacademy.domain.Transaction;
import tr.springcloudstreamacademy.service.DataSourceService;

import java.util.function.Supplier;

@Configuration
public class CashCardTransactionStream {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());






    // Spring Cloud Stream generates default topics for configured applications
    // This publishes to a topic named approvalRequest-out-0
    // based on a fixed schedule defined by spring.integration.poller.fixed-dela
    @Bean
    public Supplier<Transaction> approvalRequest(DataSourceService dataSourceService) {
        return () -> dataSourceService.getData();
    }

    @Bean
    public DataSourceService dataSourceService() {
        return new DataSourceService();
    }

}
