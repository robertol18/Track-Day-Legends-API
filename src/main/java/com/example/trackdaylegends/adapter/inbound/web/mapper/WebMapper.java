package com.example.trackdaylegends.adapter.inbound.web.mapper;

import com.example.trackdaylegends.adapter.inbound.web.dto.CarModelRequest;
import com.example.trackdaylegends.adapter.inbound.web.dto.CarModelResponse;
import com.example.trackdaylegends.adapter.inbound.web.dto.EngineSpecRequest;
import com.example.trackdaylegends.adapter.inbound.web.dto.EngineSpecResponse;
import com.example.trackdaylegends.domain.model.CarModel;
import com.example.trackdaylegends.domain.model.EngineSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebMapper {

    public static CarModel toDomain(CarModelRequest request) {
        if (request == null) {
            return null;
        }
        return CarModel.create(
                request.getBrand(),
                request.getModel(),
                request.getYear(),
                request.getSegment(),
                request.getBodyStyle(),
                request.getCountry(),
                request.getDescription()
        );
    }

    public static EngineSpec toDomain(EngineSpecRequest request) {
        if (request == null) {
            return null;
        }
        return EngineSpec.create(
                request.getVersionName(),
                request.getEngineType(),
                request.getEngineConfiguration(),
                request.getDisplacementCc(),
                request.getHorsepowerHp(),
                request.getTorqueNm(),
                request.getTransmission(),
                request.getDrivetrain(),
                request.getZeroToHundredSeconds(),
                request.getTopSpeedKph(),
                request.getFuelConsumptionL100(),
                null // carModelId to be associated in usecase
        );
    }

    public static CarModelResponse toResponse(CarModel domain) {
        if (domain == null) {
            return null;
        }

        List<EngineSpecResponse> specs = new ArrayList<>();
        if (domain.getEngineSpecs() != null) {
            specs = domain.getEngineSpecs().stream()
                    .map(WebMapper::toResponse)
                    .collect(Collectors.toList());
        }

        return new CarModelResponse(
                domain.getId(),
                domain.getBrand(),
                domain.getModel(),
                domain.getYear(),
                domain.getSegment(),
                domain.getBodyStyle(),
                domain.getCountry(),
                domain.getDescription(),
                domain.getActive(),
                specs
        );
    }

    public static EngineSpecResponse toResponse(EngineSpec domain) {
        if (domain == null) {
            return null;
        }

        return new EngineSpecResponse(
                domain.getId(),
                domain.getVersionName(),
                domain.getEngineType(),
                domain.getEngineConfiguration(),
                domain.getDisplacementCc(),
                domain.getHorsepowerHp(),
                domain.getTorqueNm(),
                domain.getTransmission(),
                domain.getDrivetrain(),
                domain.getZeroToHundredSeconds(),
                domain.getTopSpeedKph(),
                domain.getFuelConsumptionL100(),
                domain.getActive(),
                domain.getCarModelId()
        );
    }
}
