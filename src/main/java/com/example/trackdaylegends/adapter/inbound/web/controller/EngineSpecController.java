package com.example.trackdaylegends.adapter.inbound.web.controller;

import com.example.trackdaylegends.adapter.inbound.web.dto.EngineSpecRequest;
import com.example.trackdaylegends.adapter.inbound.web.dto.EngineSpecResponse;
import com.example.trackdaylegends.adapter.inbound.web.mapper.WebMapper;
import com.example.trackdaylegends.application.port.in.EngineSpecUseCase;
import com.example.trackdaylegends.domain.model.EngineSpec;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EngineSpecController {

    private final EngineSpecUseCase engineSpecUseCase;

    public EngineSpecController(EngineSpecUseCase engineSpecUseCase) {
        this.engineSpecUseCase = engineSpecUseCase;
    }

    @GetMapping("/engine-specs")
    public ResponseEntity<List<EngineSpecResponse>> getAllEngineSpecs(
            @RequestParam(required = false) String engineType,
            @RequestParam(required = false) Integer minHorsepower,
            @RequestParam(required = false) Double maxZeroToHundred,
            @RequestParam(required = false) String drivetrain,
            @RequestParam(required = false) Boolean active) {

        List<EngineSpec> specs = engineSpecUseCase.getAllEngineSpecs(engineType, minHorsepower, maxZeroToHundred, drivetrain, active);
        List<EngineSpecResponse> response = specs.stream()
                .map(WebMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/engine-specs/{id}")
    public ResponseEntity<EngineSpecResponse> getEngineSpecById(@PathVariable Long id) {
        EngineSpec spec = engineSpecUseCase.getEngineSpecById(id);
        return ResponseEntity.ok(WebMapper.toResponse(spec));
    }

    @PostMapping("/car-models/{carModelId}/engine-specs")
    public ResponseEntity<EngineSpecResponse> createEngineSpec(
            @PathVariable Long carModelId,
            @Valid @RequestBody EngineSpecRequest request) {

        EngineSpec spec = WebMapper.toDomain(request);
        EngineSpec savedSpec = engineSpecUseCase.createEngineSpec(carModelId, spec);
        return ResponseEntity.status(HttpStatus.CREATED).body(WebMapper.toResponse(savedSpec));
    }

    @PutMapping("/engine-specs/{id}")
    public ResponseEntity<EngineSpecResponse> updateEngineSpec(
            @PathVariable Long id,
            @Valid @RequestBody EngineSpecRequest request) {

        EngineSpec specDetails = WebMapper.toDomain(request);
        EngineSpec updatedSpec = engineSpecUseCase.updateEngineSpec(id, specDetails);
        return ResponseEntity.ok(WebMapper.toResponse(updatedSpec));
    }

    @PatchMapping("/engine-specs/{id}/deactivate")
    public ResponseEntity<Void> deactivateEngineSpec(@PathVariable Long id) {
        engineSpecUseCase.deactivateEngineSpec(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/engine-specs/{id}")
    public ResponseEntity<Void> deleteEngineSpec(@PathVariable Long id) {
        engineSpecUseCase.deleteEngineSpec(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/engine-specs/search/fastest")
    public ResponseEntity<List<EngineSpecResponse>> getFastestEngineSpecs() {
        List<EngineSpec> specs = engineSpecUseCase.getFastestEngineSpecs();
        List<EngineSpecResponse> response = specs.stream()
                .map(WebMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/engine-specs/search/most-powerful")
    public ResponseEntity<List<EngineSpecResponse>> getMostPowerfulEngineSpecs() {
        List<EngineSpec> specs = engineSpecUseCase.getMostPowerfulEngineSpecs();
        List<EngineSpecResponse> response = specs.stream()
                .map(WebMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
