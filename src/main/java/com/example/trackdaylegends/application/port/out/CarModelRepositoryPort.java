package com.example.trackdaylegends.application.port.out;

import com.example.trackdaylegends.domain.model.CarModel;
import java.util.List;
import java.util.Optional;

public interface CarModelRepositoryPort {
    Optional<CarModel> findById(Long id);
    List<CarModel> findAll(String brand, Integer year, String segment, Boolean active);
    CarModel save(CarModel carModel);
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
    long countDistinctBrands();
}
