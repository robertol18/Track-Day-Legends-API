package com.example.trackdaylegends.application.port.in;

public interface StatsUseCase {
    record StatsSummary(
        long totalCarModels,
        long totalEngineSpecs,
        double averageHorsepower,
        double fastestZeroToHundred,
        String mostCommonEngineType,
        long brandsCount
    ) {}

    StatsSummary getSummary();
}
