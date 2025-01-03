package tr.springcloudstreamacademy.stream;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tr.springcloudstreamacademy.domain.Transaction;
import tr.springcloudstreamacademy.service.DataSourceService;

import java.util.function.Supplier;

@Configuration
public class CashCardStream {

    @Bean
    public Supplier<Transaction> approvalRequest(DataSourceService dataSourceService) {
        return () -> dataSourceService.getData();
    }

    @Bean
    public DataSourceService dataSourceService() {
        return new DataSourceService();
    }

}
