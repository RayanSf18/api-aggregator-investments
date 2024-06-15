package br.com.aggregator_investments.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "BrapiClient", url = "https://brapi.dev")
public interface BrapiClient {

    @GetMapping(value = "/api/quote/{stockId}")
    BrapiResponseDto getQuote(@RequestParam(name = "token") String token, @PathVariable(name = "stockId") String stockId);
}
