package com.example.stockSummaryApp.module.stockapi.application;

import com.example.stockSummaryApp.module.stockapi.dto.StockInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChartService {
    private final ChartApiAdapter chartApiAdapter;

    public Flux<StockInfoDto.ListResponse> getInfos() {
        return chartApiAdapter.fetchInfos()
                .doOnError(error -> System.err.println("Error while processing charts: " + error.getMessage()))
                .onErrorResume(error -> Flux.empty())
                .timeout(java.time.Duration.ofSeconds(5));
    }
}
