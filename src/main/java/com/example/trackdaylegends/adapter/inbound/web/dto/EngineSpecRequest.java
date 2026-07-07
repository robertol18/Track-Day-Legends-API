package com.example.trackdaylegends.adapter.inbound.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EngineSpecRequest {

    @NotBlank(message = "Version name cannot be empty")
    private String versionName;

    @NotBlank(message = "Engine type cannot be empty")
    private String engineType;

    private String engineConfiguration;

    @NotNull(message = "Displacement is mandatory")
    @Min(value = 1, message = "Displacement must be greater than 0")
    private Integer displacementCc;

    @NotNull(message = "Horsepower is mandatory")
    @Min(value = 1, message = "Horsepower must be greater than 0")
    private Integer horsepowerHp;

    @NotNull(message = "Torque is mandatory")
    @Min(value = 1, message = "Torque must be greater than 0")
    private Integer torqueNm;

    private String transmission;
    private String drivetrain;

    @NotNull(message = "0-100 time is mandatory")
    @Min(value = 0, message = "0-100 time must be greater than 0")
    private Double zeroToHundredSeconds;

    @NotNull(message = "Top speed is mandatory")
    @Min(value = 1, message = "Top speed must be greater than 0")
    private Integer topSpeedKph;

    private Double fuelConsumptionL100;
    private Boolean active = true;

    public EngineSpecRequest() {}

    public EngineSpecRequest(String versionName, String engineType, String engineConfiguration, 
                             Integer displacementCc, Integer horsepowerHp, Integer torqueNm, 
                             String transmission, String drivetrain, Double zeroToHundredSeconds, 
                             Integer topSpeedKph, Double fuelConsumptionL100, Boolean active) {
        this.versionName = versionName;
        this.engineType = engineType;
        this.engineConfiguration = engineConfiguration;
        this.displacementCc = displacementCc;
        this.horsepowerHp = horsepowerHp;
        this.torqueNm = torqueNm;
        this.transmission = transmission;
        this.drivetrain = drivetrain;
        this.zeroToHundredSeconds = zeroToHundredSeconds;
        this.topSpeedKph = topSpeedKph;
        this.fuelConsumptionL100 = fuelConsumptionL100;
        this.active = active != null ? active : true;
    }

    // Getters and Setters
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getEngineConfiguration() {
        return engineConfiguration;
    }

    public void setEngineConfiguration(String engineConfiguration) {
        this.engineConfiguration = engineConfiguration;
    }

    public Integer getDisplacementCc() {
        return displacementCc;
    }

    public void setDisplacementCc(Integer displacementCc) {
        this.displacementCc = displacementCc;
    }

    public Integer getHorsepowerHp() {
        return horsepowerHp;
    }

    public void setHorsepowerHp(Integer horsepowerHp) {
        this.horsepowerHp = horsepowerHp;
    }

    public Integer getTorqueNm() {
        return torqueNm;
    }

    public void setTorqueNm(Integer torqueNm) {
        this.torqueNm = torqueNm;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(String drivetrain) {
        this.drivetrain = drivetrain;
    }

    public Double getZeroToHundredSeconds() {
        return zeroToHundredSeconds;
    }

    public void setZeroToHundredSeconds(Double zeroToHundredSeconds) {
        this.zeroToHundredSeconds = zeroToHundredSeconds;
    }

    public Integer getTopSpeedKph() {
        return topSpeedKph;
    }

    public void setTopSpeedKph(Integer topSpeedKph) {
        this.topSpeedKph = topSpeedKph;
    }

    public Double getFuelConsumptionL100() {
        return fuelConsumptionL100;
    }

    public void setFuelConsumptionL100(Double fuelConsumptionL100) {
        this.fuelConsumptionL100 = fuelConsumptionL100;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
