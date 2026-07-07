package com.example.trackdaylegends.application.usecase;

import com.example.trackdaylegends.application.port.in.CarModelUseCase;
import com.example.trackdaylegends.application.port.out.CarModelRepositoryPort;
import com.example.trackdaylegends.domain.exception.EntityNotFoundException;
import com.example.trackdaylegends.domain.model.CarModel;
import java.util.List;

public class CarModelService implements CarModelUseCase {

    private final CarModelRepositoryPort carModelRepository;

    public CarModelService(CarModelRepositoryPort carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    @Override
    public CarModel getCarModelById(Long id) {
        return carModelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Modelo de coche no encontrado con ID: " + id));
    }

    @Override
    public List<CarModel> getAllCarModels(String brand, Integer year, String segment, Boolean active) {
        return carModelRepository.findAll(brand, year, segment, active);
    }

    @Override
    public CarModel createCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    @Override
    public CarModel updateCarModel(Long id, CarModel carModel) {
        CarModel existingModel = getCarModelById(id);
        existingModel.updateDetails(
                carModel.getBrand(),
                carModel.getModel(),
                carModel.getYear(),
                carModel.getSegment(),
                carModel.getBodyStyle(),
                carModel.getCountry(),
                carModel.getDescription(),
                carModel.getActive()
        );
        return carModelRepository.save(existingModel);
    }

    @Override
    public void deactivateCarModel(Long id) {
        CarModel existingModel = getCarModelById(id);
        existingModel.deactivate();
        carModelRepository.save(existingModel);
    }

    @Override
    public void deleteCarModel(Long id) {
        if (!carModelRepository.existsById(id)) {
            throw new EntityNotFoundException("Modelo de coche no encontrado con ID: " + id);
        }
        carModelRepository.deleteById(id);
    }
}
