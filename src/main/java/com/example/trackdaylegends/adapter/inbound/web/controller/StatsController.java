package com.example.trackdaylegends.adapter.inbound.web.controller;

import com.example.trackdaylegends.adapter.inbound.web.dto.StatsResponse;
import com.example.trackdaylegends.application.port.in.StatsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsUseCase statsUseCase;

    public StatsController(StatsUseCase statsUseCase) {
        this.statsUseCase = statsUseCase;
    }

    @GetMapping("/summary")
    public ResponseEntity<StatsResponse> getStatsSummary() {
        StatsUseCase.StatsSummary summary = statsUseCase.getSummary();
        StatsResponse response = new StatsResponse(
                summary.totalCarModels(),
                summary.totalEngineSpecs(),
                summary.averageHorsepower(),
                summary.fastestZeroToHundred(),
                summary.mostCommonEngineType(),
                summary.brandsCount()
        );
        return ResponseEntity.ok(response);
    }
}
