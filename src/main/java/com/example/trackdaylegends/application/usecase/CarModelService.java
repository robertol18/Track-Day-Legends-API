package com.example.trackdaylegends.application.usecase;

import com.example.trackdaylegends.application.port.in.CarModelUseCase;
import com.example.trackdaylegends.application.port.out.CarModelRepositoryPort;
import com.example.trackdaylegends.domain.exception.EntityNotFoundException;
import com.example.trackdaylegends.domain.model.CarModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class CarModelService implements CarModelUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CarModelService.class);
    
    private final CarModelRepositoryPort carModelRepository;

    public CarModelService(CarModelRepositoryPort carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    @Override
    public CarModel getCarModelById(Long id) {
        logger.trace("Retrieving car model with id: {}", id);
        return carModelRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Car model not found with ID: {}", id);
                    return new EntityNotFoundException("Car model not found with ID: " + id);
                });
    }

    @Override
    public List<CarModel> getAllCarModels(String brand, Integer year, String segment, Boolean active) {
        logger.trace("Querying car models with filters: brand={}, year={}, segment={}, active={}", 
                    brand, year, segment, active);
        List<CarModel> result = carModelRepository.findAll(brand, year, segment, active);
        logger.debug("Found {} car models matching the filters", result.size());
        return result;
    }

    @Override
    public CarModel createCarModel(CarModel carModel) {
        logger.info("Creating new car model: brand={}, model={}, year={}", 
                   carModel.getBrand(), carModel.getModel(), carModel.getYear());
        CarModel saved = carModelRepository.save(carModel);
        logger.info("Car model created successfully with id: {}", saved.getId());
        return saved;
    }

    @Override
    public CarModel updateCarModel(Long id, CarModel carModel) {
        logger.info("Updating car model with id: {}", id);
        CarModel existingModel = getCarModelById(id);
        
        logger.debug("Applying updates to car model {} - brand={}, model={}, year={}", 
                    id, carModel.getBrand(), carModel.getModel(), carModel.getYear());
        
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
        
        CarModel updated = carModelRepository.save(existingModel);
        logger.info("Car model with id: {} updated successfully", id);
        return updated;
    }

    @Override
    public void deactivateCarModel(Long id) {
        logger.info("Deactivating car model with id: {}", id);
        CarModel existingModel = getCarModelById(id);
        existingModel.deactivate();
        carModelRepository.save(existingModel);
        logger.info("Car model with id: {} deactivated successfully", id);
    }

    @Override
    public void deleteCarModel(Long id) {
        logger.info("Deleting car model with id: {}", id);
        if (!carModelRepository.existsById(id)) {
            logger.warn("Cannot delete car model with id: {} - not found", id);
            throw new EntityNotFoundException("Car model not found with ID: " + id);
        }
        carModelRepository.deleteById(id);
        logger.info("Car model with id: {} deleted successfully", id);
    }
}
