package com.example.trackdaylegends.adapter.outbound.persistence.repository;

import com.example.trackdaylegends.adapter.outbound.persistence.entity.CarModelEntity;
import com.example.trackdaylegends.adapter.outbound.persistence.entity.EngineSpecEntity;
import com.example.trackdaylegends.adapter.outbound.persistence.mapper.PersistenceMapper;
import com.example.trackdaylegends.application.port.out.EngineSpecRepositoryPort;
import com.example.trackdaylegends.domain.exception.EntityNotFoundException;
import com.example.trackdaylegends.domain.model.EngineSpec;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EngineSpecPersistenceAdapter implements EngineSpecRepositoryPort {

    private final SpringEngineSpecRepository springRepository;
    private final SpringCarModelRepository carModelRepository;

    public EngineSpecPersistenceAdapter(SpringEngineSpecRepository springRepository, 
                                        SpringCarModelRepository carModelRepository) {
        this.springRepository = springRepository;
        this.carModelRepository = carModelRepository;
    }

    @Override
    public Optional<EngineSpec> findById(Long id) {
        return springRepository.findById(id).map(PersistenceMapper::toDomain);
    }

    @Override
    public List<EngineSpec> findAll(String engineType, Integer minHorsepower, Double maxZeroToHundred, String drivetrain, Boolean active) {
        return springRepository.findByFilters(engineType, minHorsepower, maxZeroToHundred, drivetrain, active).stream()
                .map(PersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public EngineSpec save(EngineSpec engineSpec) {
        CarModelEntity carModelEntity = carModelRepository.findById(engineSpec.getCarModelId())
                .orElseThrow(() -> new EntityNotFoundException("Modelo de coche no encontrado con ID: " + engineSpec.getCarModelId()));
        
        EngineSpecEntity entity = PersistenceMapper.toEntity(engineSpec, carModelEntity);
        EngineSpecEntity savedEntity = springRepository.save(entity);
        return PersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        springRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springRepository.existsById(id);
    }

    @Override
    public List<EngineSpec> findFastest() {
        return springRepository.findTop5ByActiveTrueOrderByZeroToHundredSecondsAsc().stream()
                .map(PersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<EngineSpec> findMostPowerful() {
        return springRepository.findTop5ByActiveTrueOrderByHorsepowerHpDesc().stream()
                .map(PersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return springRepository.count();
    }

    @Override
    public Double getAverageHorsepower() {
        return springRepository.getAverageHorsepower();
    }

    @Override
    public Double getFastestZeroToHundred() {
        return springRepository.getFastestZeroToHundred();
    }

    @Override
    public String getMostCommonEngineType() {
        List<String> results = springRepository.getMostCommonEngineTypes(PageRequest.of(0, 1));
        return results.isEmpty() ? null : results.get(0);
    }
}
