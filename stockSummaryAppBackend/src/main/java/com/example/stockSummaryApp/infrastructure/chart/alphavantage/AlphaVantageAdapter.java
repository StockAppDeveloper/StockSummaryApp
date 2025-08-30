package com.example.stockSummaryApp.infrastructure.chart.alphavantage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.stockSummaryApp.module.stockapi.application.ChartApiAdapter;
import com.example.stockSummaryApp.module.stockapi.dto.ChartDto;
import io.netty.channel.ChannelOption;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Component
public class AlphaVantageAdapter implements ChartApiAdapter {

    private static final int MAX_BUFFER_SIZE = 10*1024*1024;

    private final WebClient webClient;
    private final Properties properties;
    private final ResponseConverter responseConverter;

    public AlphaVantageAdapter(Properties properties, ResponseConverter responseConverter) {
        this.properties = properties;
        this.responseConverter = responseConverter;
        HttpClient httpClient = HttpClient.create()
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .followRedirect(true)
                .compress(true);

         this.webClient= WebClient.builder()
                .baseUrl(properties.getBaseUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(MAX_BUFFER_SIZE))
                .build();
    }

    public Flux<ChartDto.ListResponse> fetchInfos() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/query")
                        .queryParam("function", "LISTING_STATUS")
                        .queryParam("apikey", properties.getApiKey())
                        .queryParam("state", "active")
                        .queryParam("date", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(responseConverter::parse)
                .flatMapMany(Flux::fromIterable);

    }

    @Override
    public Mono<ChartDto.DetailResponse> getDetails(String symbol) {
        return null;
    }
}
