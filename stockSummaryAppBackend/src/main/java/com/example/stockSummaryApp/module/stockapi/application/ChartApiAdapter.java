package com.example.stockSummaryApp.module.stockapi.application;

import com.example.stockSummaryApp.module.stockapi.dto.ChartDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChartApiAdapter {
    Flux<ChartDto.ListResponse> fetchInfo();

    Mono<ChartDto.DetailResponse> getDetails(String symbol);
}
