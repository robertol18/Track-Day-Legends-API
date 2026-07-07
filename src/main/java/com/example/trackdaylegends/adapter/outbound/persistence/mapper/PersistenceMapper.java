package com.example.trackdaylegends.adapter.outbound.persistence.mapper;

import com.example.trackdaylegends.adapter.outbound.persistence.entity.CarModelEntity;
import com.example.trackdaylegends.adapter.outbound.persistence.entity.EngineSpecEntity;
import com.example.trackdaylegends.domain.model.CarModel;
import com.example.trackdaylegends.domain.model.EngineSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersistenceMapper {

    public static CarModel toDomain(CarModelEntity entity) {
        if (entity == null) {
            return null;
        }

        List<EngineSpec> specs = new ArrayList<>();
        if (entity.getEngineSpecs() != null) {
            specs = entity.getEngineSpecs().stream()
                    .map(PersistenceMapper::toDomain)
                    .collect(Collectors.toList());
        }

        return CarModel.reconstruct(
                entity.getId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getYear(),
                entity.getSegment(),
                entity.getBodyStyle(),
                entity.getCountry(),
                entity.getDescription(),
                entity.getActive(),
                specs
        );
    }

    public static EngineSpec toDomain(EngineSpecEntity entity) {
        if (entity == null) {
            return null;
        }

        Long carModelId = entity.getCarModel() != null ? entity.getCarModel().getId() : null;

        return EngineSpec.reconstruct(
                entity.getId(),
                entity.getVersionName(),
                entity.getEngineType(),
                entity.getEngineConfiguration(),
                entity.getDisplacementCc(),
                entity.getHorsepowerHp(),
                entity.getTorqueNm(),
                entity.getTransmission(),
                entity.getDrivetrain(),
                entity.getZeroToHundredSeconds(),
                entity.getTopSpeedKph(),
                entity.getFuelConsumptionL100(),
                entity.getActive(),
                carModelId
        );
    }

    public static CarModelEntity toEntity(CarModel model) {
        if (model == null) {
            return null;
        }

        CarModelEntity entity = new CarModelEntity(
                model.getId(),
                model.getBrand(),
                model.getModel(),
                model.getYear(),
                model.getSegment(),
                model.getBodyStyle(),
                model.getCountry(),
                model.getDescription(),
                model.getActive()
        );

        if (model.getEngineSpecs() != null) {
            List<EngineSpecEntity> specs = model.getEngineSpecs().stream()
                    .map(spec -> toEntity(spec, entity))
                    .collect(Collectors.toList());
            entity.setEngineSpecs(specs);
        }

        return entity;
    }

    public static EngineSpecEntity toEntity(EngineSpec spec, CarModelEntity carModel) {
        if (spec == null) {
            return null;
        }

        return new EngineSpecEntity(
                spec.getId(),
                spec.getVersionName(),
                spec.getEngineType(),
                spec.getEngineConfiguration(),
                spec.getDisplacementCc(),
                spec.getHorsepowerHp(),
                spec.getTorqueNm(),
                spec.getTransmission(),
                spec.getDrivetrain(),
                spec.getZeroToHundredSeconds(),
                spec.getTopSpeedKph(),
                spec.getFuelConsumptionL100(),
                spec.getActive(),
                carModel
        );
    }
}
