package com.example.stockSummaryApp.infrastructure.chart.alphavantage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "alphavantage")
public class Properties {
    private final String baseUrl;
    private final String apiKey;
}
