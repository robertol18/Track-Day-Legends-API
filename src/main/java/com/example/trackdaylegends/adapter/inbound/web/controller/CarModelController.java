package com.example.trackdaylegends.adapter.inbound.web.controller;

import com.example.trackdaylegends.adapter.inbound.web.dto.CarModelRequest;
import com.example.trackdaylegends.adapter.inbound.web.dto.CarModelResponse;
import com.example.trackdaylegends.adapter.inbound.web.mapper.WebMapper;
import com.example.trackdaylegends.application.port.in.CarModelUseCase;
import com.example.trackdaylegends.domain.model.CarModel;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car-models")
public class CarModelController {

    private static final Logger logger = LoggerFactory.getLogger(CarModelController.class);
    
    private final CarModelUseCase carModelUseCase;

    public CarModelController(CarModelUseCase carModelUseCase) {
        this.carModelUseCase = carModelUseCase;
    }

    @GetMapping
    public ResponseEntity<List<CarModelResponse>> getAllCarModels(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String segment,
            @RequestParam(required = false) Boolean active) {
        
        logger.debug("Fetching all car models with filters: brand={}, year={}, segment={}, active={}", 
                    brand, year, segment, active);
        
        List<CarModel> models = carModelUseCase.getAllCarModels(brand, year, segment, active);
        List<CarModelResponse> response = models.stream()
                .map(WebMapper::toResponse)
                .collect(Collectors.toList());
        
        logger.info("Successfully retrieved {} car models", response.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarModelResponse> getCarModelById(@PathVariable Long id) {
        logger.debug("Fetching car model with id: {}", id);
        
        CarModel model = carModelUseCase.getCarModelById(id);
        
        logger.info("Successfully retrieved car model with id: {} ({})", id, model.getBrand());
        return ResponseEntity.ok(WebMapper.toResponse(model));
    }

    @PostMapping
    public ResponseEntity<CarModelResponse> createCarModel(@Valid @RequestBody CarModelRequest request) {
        logger.debug("Creating new car model: brand={}, model={}, year={}", 
                    request.getBrand(), request.getModel(), request.getYear());
        
        CarModel model = WebMapper.toDomain(request);
        CarModel savedModel = carModelUseCase.createCarModel(model);
        
        logger.info("Car model created successfully with id: {} ({})", savedModel.getId(), savedModel.getBrand());
        return ResponseEntity.status(HttpStatus.CREATED).body(WebMapper.toResponse(savedModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModelResponse> updateCarModel(
            @PathVariable Long id,
            @Valid @RequestBody CarModelRequest request) {
        
        logger.debug("Updating car model with id: {}, new data: brand={}, model={}", 
                    id, request.getBrand(), request.getModel());
        
        CarModel modelDetails = WebMapper.toDomain(request);
        CarModel updatedModel = carModelUseCase.updateCarModel(id, modelDetails);
        
        logger.info("Car model with id: {} updated successfully", id);
        return ResponseEntity.ok(WebMapper.toResponse(updatedModel));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCarModel(@PathVariable Long id) {
        logger.debug("Deactivating car model with id: {}", id);
        
        carModelUseCase.deactivateCarModel(id);
        
        logger.info("Car model with id: {} deactivated successfully", id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable Long id) {
        logger.debug("Deleting car model with id: {}", id);
        
        carModelUseCase.deleteCarModel(id);
        
        logger.info("Car model with id: {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }
}
