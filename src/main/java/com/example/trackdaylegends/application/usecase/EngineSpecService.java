package com.example.trackdaylegends.application.usecase;

import com.example.trackdaylegends.application.port.in.EngineSpecUseCase;
import com.example.trackdaylegends.application.port.out.CarModelRepositoryPort;
import com.example.trackdaylegends.application.port.out.EngineSpecRepositoryPort;
import com.example.trackdaylegends.domain.exception.EntityNotFoundException;
import com.example.trackdaylegends.domain.model.EngineSpec;
import java.util.List;

public class EngineSpecService implements EngineSpecUseCase {

    private final EngineSpecRepositoryPort engineSpecRepository;
    private final CarModelRepositoryPort carModelRepository;

    public EngineSpecService(EngineSpecRepositoryPort engineSpecRepository, CarModelRepositoryPort carModelRepository) {
        this.engineSpecRepository = engineSpecRepository;
        this.carModelRepository = carModelRepository;
    }

    @Override
    public EngineSpec getEngineSpecById(Long id) {
        return engineSpecRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Motorización no encontrada con ID: " + id));
    }

    @Override
    public List<EngineSpec> getAllEngineSpecs(String engineType, Integer minHorsepower, Double maxZeroToHundred, String drivetrain, Boolean active) {
        return engineSpecRepository.findAll(engineType, minHorsepower, maxZeroToHundred, drivetrain, active);
    }

    @Override
    public EngineSpec createEngineSpec(Long carModelId, EngineSpec engineSpec) {
        if (!carModelRepository.existsById(carModelId)) {
            throw new EntityNotFoundException("No se puede crear la motorización. Modelo de coche no encontrado con ID: " + carModelId);
        }
        engineSpec.setCarModelId(carModelId);
        return engineSpecRepository.save(engineSpec);
    }

    @Override
    public EngineSpec updateEngineSpec(Long id, EngineSpec engineSpec) {
        EngineSpec existingSpec = getEngineSpecById(id);
        existingSpec.updateDetails(
                engineSpec.getVersionName(),
                engineSpec.getEngineType(),
                engineSpec.getEngineConfiguration(),
                engineSpec.getDisplacementCc(),
                engineSpec.getHorsepowerHp(),
                engineSpec.getTorqueNm(),
                engineSpec.getTransmission(),
                engineSpec.getDrivetrain(),
                engineSpec.getZeroToHundredSeconds(),
                engineSpec.getTopSpeedKph(),
                engineSpec.getFuelConsumptionL100(),
                engineSpec.getActive()
        );
        return engineSpecRepository.save(existingSpec);
    }

    @Override
    public void deactivateEngineSpec(Long id) {
        EngineSpec existingSpec = getEngineSpecById(id);
        existingSpec.deactivate();
        engineSpecRepository.save(existingSpec);
    }

    @Override
    public void deleteEngineSpec(Long id) {
        if (!engineSpecRepository.existsById(id)) {
            throw new EntityNotFoundException("Motorización no encontrada con ID: " + id);
        }
        engineSpecRepository.deleteById(id);
    }

    @Override
    public List<EngineSpec> getFastestEngineSpecs() {
        return engineSpecRepository.findFastest();
    }

    @Override
    public List<EngineSpec> getMostPowerfulEngineSpecs() {
        return engineSpecRepository.findMostPowerful();
    }
}
