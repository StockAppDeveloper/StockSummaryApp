package com.example.stockSummaryApp.module.stockapi.dto;

import java.time.LocalDate;

public sealed interface ChartDto permits ChartDto.ListResponse, ChartDto.DetailResponse {

    record ListResponse(
            String symbol,
            String name,
            String exchange,
            String assetType,
            LocalDate ipoDate,
            LocalDate delistingDate,
            String status
    ) implements ChartDto {
    }

    record DetailResponse(
            String symbol,
            String open,
            String high,
            String low,
            String price,
            String volume,
            String latestTradingDay,
            String previousClose,
            String change,
            String changePercent
    ) implements ChartDto {
    }
}
