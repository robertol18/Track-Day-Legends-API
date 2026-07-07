package com.example.trackdaylegends.application.port.in;

import com.example.trackdaylegends.domain.model.CarModel;
import java.util.List;

public interface CarModelUseCase {
    CarModel getCarModelById(Long id);
    List<CarModel> getAllCarModels(String brand, Integer year, String segment, Boolean active);
    CarModel createCarModel(CarModel carModel);
    CarModel updateCarModel(Long id, CarModel carModel);
    void deactivateCarModel(Long id);
    void deleteCarModel(Long id);
}
