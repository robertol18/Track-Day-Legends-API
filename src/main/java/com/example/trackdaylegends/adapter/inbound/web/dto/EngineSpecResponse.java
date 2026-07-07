package com.example.trackdaylegends.adapter.inbound.web.dto;

public class EngineSpecResponse {
    private Long id;
    private String versionName;
    private String engineType;
    private String engineConfiguration;
    private Integer displacementCc;
    private Integer horsepowerHp;
    private Integer torqueNm;
    private String transmission;
    private String drivetrain;
    private Double zeroToHundredSeconds;
    private Integer topSpeedKph;
    private Double fuelConsumptionL100;
    private Boolean active;
    private Long carModelId;

    public EngineSpecResponse() {}

    public EngineSpecResponse(Long id, String versionName, String engineType, String engineConfiguration, 
                              Integer displacementCc, Integer horsepowerHp, Integer torqueNm, 
                              String transmission, String drivetrain, Double zeroToHundredSeconds, 
                              Integer topSpeedKph, Double fuelConsumptionL100, Boolean active, 
                              Long carModelId) {
        this.id = id;
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
        this.active = active;
        this.carModelId = carModelId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Long carModelId) {
        this.carModelId = carModelId;
    }
}
