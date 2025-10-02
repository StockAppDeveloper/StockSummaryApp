package com.example.stockSummaryApp.module.stockapi.application;

import com.example.stockSummaryApp.module.stockapi.dto.StockInfoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChartApiAdapter {
    Flux<StockInfoDto.ListResponse> fetchInfos();

    Mono<StockInfoDto.DetailResponse> getDetails(String symbol);
}
