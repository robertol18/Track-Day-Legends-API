package com.example.trackdaylegends.application.port.in;

import com.example.trackdaylegends.domain.model.EngineSpec;
import java.util.List;

public interface EngineSpecUseCase {
    EngineSpec getEngineSpecById(Long id);
    List<EngineSpec> getAllEngineSpecs(String engineType, Integer minHorsepower, Double maxZeroToHundred, String drivetrain, Boolean active);
    EngineSpec createEngineSpec(Long carModelId, EngineSpec engineSpec);
    EngineSpec updateEngineSpec(Long id, EngineSpec engineSpec);
    void deactivateEngineSpec(Long id);
    void deleteEngineSpec(Long id);
    List<EngineSpec> getFastestEngineSpecs();
    List<EngineSpec> getMostPowerfulEngineSpecs();
}
