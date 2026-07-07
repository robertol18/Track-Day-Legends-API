package com.example.trackdaylegends.application.usecase;

import com.example.trackdaylegends.application.port.in.StatsUseCase;
import com.example.trackdaylegends.application.port.out.CarModelRepositoryPort;
import com.example.trackdaylegends.application.port.out.EngineSpecRepositoryPort;

public class StatsService implements StatsUseCase {

    private final CarModelRepositoryPort carModelRepository;
    private final EngineSpecRepositoryPort engineSpecRepository;

    public StatsService(CarModelRepositoryPort carModelRepository, EngineSpecRepositoryPort engineSpecRepository) {
        this.carModelRepository = carModelRepository;
        this.engineSpecRepository = engineSpecRepository;
    }

    @Override
    public StatsSummary getSummary() {
        long totalCarModels = carModelRepository.count();
        long totalEngineSpecs = engineSpecRepository.count();
        
        Double avgHp = engineSpecRepository.getAverageHorsepower();
        double averageHorsepower = avgHp != null ? avgHp : 0.0;

        Double fastest0100 = engineSpecRepository.getFastestZeroToHundred();
        double fastestZeroToHundred = fastest0100 != null ? fastest0100 : 0.0;

        String mostCommonEngineType = engineSpecRepository.getMostCommonEngineType();
        if (mostCommonEngineType == null) {
            mostCommonEngineType = "N/A";
        }

        long brandsCount = carModelRepository.countDistinctBrands();

        return new StatsSummary(
                totalCarModels,
                totalEngineSpecs,
                averageHorsepower,
                fastestZeroToHundred,
                mostCommonEngineType,
                brandsCount
        );
    }
}
