package com.example.trackdaylegends.domain.model;

public class EngineSpec {
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

    // Default constructor
    public EngineSpec() {}

    // Full constructor for reconstruction
    public EngineSpec(Long id, String versionName, String engineType, String engineConfiguration, 
                      Integer displacementCc, Integer horsepowerHp, Integer torqueNm, 
                      String transmission, String drivetrain, Double zeroToHundredSeconds, 
                      Integer topSpeedKph, Double fuelConsumptionL100, Boolean active, Long carModelId) {
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
        this.active = active != null ? active : true;
        this.carModelId = carModelId;
    }

    // Factory method for creating a new EngineSpec
    public static EngineSpec create(String versionName, String engineType, String engineConfiguration, 
                                    Integer displacementCc, Integer horsepowerHp, Integer torqueNm, 
                                    String transmission, String drivetrain, Double zeroToHundredSeconds, 
                                    Integer topSpeedKph, Double fuelConsumptionL100, Long carModelId) {
        return new EngineSpec(null, versionName, engineType, engineConfiguration, displacementCc, 
                horsepowerHp, torqueNm, transmission, drivetrain, zeroToHundredSeconds, 
                topSpeedKph, fuelConsumptionL100, true, carModelId);
    }

    // Factory method for reconstruction
    public static EngineSpec reconstruct(Long id, String versionName, String engineType, String engineConfiguration, 
                                         Integer displacementCc, Integer horsepowerHp, Integer torqueNm, 
                                         String transmission, String drivetrain, Double zeroToHundredSeconds, 
                                         Integer topSpeedKph, Double fuelConsumptionL100, Boolean active, Long carModelId) {
        return new EngineSpec(id, versionName, engineType, engineConfiguration, displacementCc, 
                horsepowerHp, torqueNm, transmission, drivetrain, zeroToHundredSeconds, 
                topSpeedKph, fuelConsumptionL100, active, carModelId);
    }

    // Business Logic
    public void deactivate() {
        this.active = false;
    }

    public void updateDetails(String versionName, String engineType, String engineConfiguration, 
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
        if (active != null) {
            this.active = active;
        }
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

    public String getEngineType() {
        return engineType;
    }

    public String getEngineConfiguration() {
        return engineConfiguration;
    }

    public Integer getDisplacementCc() {
        return displacementCc;
    }

    public Integer getHorsepowerHp() {
        return horsepowerHp;
    }

    public Integer getTorqueNm() {
        return torqueNm;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getDrivetrain() {
        return drivetrain;
    }

    public Double getZeroToHundredSeconds() {
        return zeroToHundredSeconds;
    }

    public Integer getTopSpeedKph() {
        return topSpeedKph;
    }

    public Double getFuelConsumptionL100() {
        return fuelConsumptionL100;
    }

    public Boolean getActive() {
        return active;
    }

    public Long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Long carModelId) {
        this.carModelId = carModelId;
    }
}
