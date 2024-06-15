package br.com.aggregator_investments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AggregatorInvestmentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregatorInvestmentsApplication.class, args);
    }

}
