package com.example.trackdaylegends.application.port.out;

import com.example.trackdaylegends.domain.model.EngineSpec;
import java.util.List;
import java.util.Optional;

public interface EngineSpecRepositoryPort {
    Optional<EngineSpec> findById(Long id);
    List<EngineSpec> findAll(String engineType, Integer minHorsepower, Double maxZeroToHundred, String drivetrain, Boolean active);
    EngineSpec save(EngineSpec engineSpec);
    void deleteById(Long id);
    boolean existsById(Long id);
    List<EngineSpec> findFastest();
    List<EngineSpec> findMostPowerful();
    long count();
    Double getAverageHorsepower();
    Double getFastestZeroToHundred();
    String getMostCommonEngineType();
}
