package com.example.trackdaylegends.adapter.inbound.web.controller;

import com.example.trackdaylegends.adapter.inbound.web.dto.CarModelRequest;
import com.example.trackdaylegends.adapter.inbound.web.dto.CarModelResponse;
import com.example.trackdaylegends.adapter.inbound.web.mapper.WebMapper;
import com.example.trackdaylegends.application.port.in.CarModelUseCase;
import com.example.trackdaylegends.domain.model.CarModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car-models")
public class CarModelController {

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
        
        List<CarModel> models = carModelUseCase.getAllCarModels(brand, year, segment, active);
        List<CarModelResponse> response = models.stream()
                .map(WebMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarModelResponse> getCarModelById(@PathVariable Long id) {
        CarModel model = carModelUseCase.getCarModelById(id);
        return ResponseEntity.ok(WebMapper.toResponse(model));
    }

    @PostMapping
    public ResponseEntity<CarModelResponse> createCarModel(@Valid @RequestBody CarModelRequest request) {
        CarModel model = WebMapper.toDomain(request);
        CarModel savedModel = carModelUseCase.createCarModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(WebMapper.toResponse(savedModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModelResponse> updateCarModel(
            @PathVariable Long id,
            @Valid @RequestBody CarModelRequest request) {
        
        CarModel modelDetails = WebMapper.toDomain(request);
        CarModel updatedModel = carModelUseCase.updateCarModel(id, modelDetails);
        return ResponseEntity.ok(WebMapper.toResponse(updatedModel));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCarModel(@PathVariable Long id) {
        carModelUseCase.deactivateCarModel(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable Long id) {
        carModelUseCase.deleteCarModel(id);
        return ResponseEntity.noContent().build();
    }
}
