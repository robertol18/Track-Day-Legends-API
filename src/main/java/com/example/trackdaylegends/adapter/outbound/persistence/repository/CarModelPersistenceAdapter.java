package com.example.trackdaylegends.adapter.outbound.persistence.repository;

import com.example.trackdaylegends.adapter.outbound.persistence.entity.CarModelEntity;
import com.example.trackdaylegends.adapter.outbound.persistence.mapper.PersistenceMapper;
import com.example.trackdaylegends.application.port.out.CarModelRepositoryPort;
import com.example.trackdaylegends.domain.model.CarModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CarModelPersistenceAdapter implements CarModelRepositoryPort {

    private final SpringCarModelRepository springRepository;

    public CarModelPersistenceAdapter(SpringCarModelRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Optional<CarModel> findById(Long id) {
        return springRepository.findById(id).map(PersistenceMapper::toDomain);
    }

    @Override
    public List<CarModel> findAll(String brand, Integer year, String segment, Boolean active) {
        return springRepository.findByFilters(brand, year, segment, active).stream()
                .map(PersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public CarModel save(CarModel carModel) {
        CarModelEntity entity = PersistenceMapper.toEntity(carModel);
        CarModelEntity savedEntity = springRepository.save(entity);
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
    public long count() {
        return springRepository.count();
    }

    @Override
    public long countDistinctBrands() {
        return springRepository.countDistinctBrands();
    }
}
