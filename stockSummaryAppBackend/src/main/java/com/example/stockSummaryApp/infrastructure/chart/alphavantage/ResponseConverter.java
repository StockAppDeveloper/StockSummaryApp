package com.example.stockSummaryApp.infrastructure.chart.alphavantage;

import com.example.stockSummaryApp.module.stockapi.dto.ChartDto;
import com.example.stockSummaryApp.module.stockapi.dto.ChartMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResponseConverter {
    private final ChartMapper chartMapper;

    public List<ChartDto.ListResponse> parse(String response) {
        try (var parser = CSVParser.parse(response, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            return parser.getRecords().stream()
                    .map(csvRecord -> chartMapper.toListResponse(csvRecord.toMap()))
                    .toList();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse stock data", e);
        }
    }

}
