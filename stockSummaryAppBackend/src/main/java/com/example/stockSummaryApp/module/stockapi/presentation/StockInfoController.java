package com.example.stockSummaryApp.module.stockapi.presentation;

import com.example.stockSummaryApp.global.StockResponse;
import com.example.stockSummaryApp.module.stockapi.application.ChartService;
import com.example.stockSummaryApp.module.stockapi.dto.ChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/charts")
public class StockInfoController {
    public final ChartService chartService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<StockResponse<List<ChartDto.ListResponse>>> getInfos() {
        return chartService.getInfos()
                .collectList()
                .map(StockResponse::ok);
    }

}
