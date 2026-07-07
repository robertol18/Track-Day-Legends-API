package com.example.trackdaylegends.adapter.inbound.web.dto;

public class StatsResponse {
    private long totalCarModels;
    private long totalEngineSpecs;
    private double averageHorsepower;
    private double fastestZeroToHundred;
    private String mostCommonEngineType;
    private long brandsCount;

    public StatsResponse() {}

    public StatsResponse(long totalCarModels, long totalEngineSpecs, double averageHorsepower, 
                         double fastestZeroToHundred, String mostCommonEngineType, long brandsCount) {
        this.totalCarModels = totalCarModels;
        this.totalEngineSpecs = totalEngineSpecs;
        this.averageHorsepower = averageHorsepower;
        this.fastestZeroToHundred = fastestZeroToHundred;
        this.mostCommonEngineType = mostCommonEngineType;
        this.brandsCount = brandsCount;
    }

    // Getters and Setters
    public long getTotalCarModels() {
        return totalCarModels;
    }

    public void setTotalCarModels(long totalCarModels) {
        this.totalCarModels = totalCarModels;
    }

    public long getTotalEngineSpecs() {
        return totalEngineSpecs;
    }

    public void setTotalEngineSpecs(long totalEngineSpecs) {
        this.totalEngineSpecs = totalEngineSpecs;
    }

    public double getAverageHorsepower() {
        return averageHorsepower;
    }

    public void setAverageHorsepower(double averageHorsepower) {
        this.averageHorsepower = averageHorsepower;
    }

    public double getFastestZeroToHundred() {
        return fastestZeroToHundred;
    }

    public void setFastestZeroToHundred(double fastestZeroToHundred) {
        this.fastestZeroToHundred = fastestZeroToHundred;
    }

    public String getMostCommonEngineType() {
        return mostCommonEngineType;
    }

    public void setMostCommonEngineType(String mostCommonEngineType) {
        this.mostCommonEngineType = mostCommonEngineType;
    }

    public long getBrandsCount() {
        return brandsCount;
    }

    public void setBrandsCount(long brandsCount) {
        this.brandsCount = brandsCount;
    }
}
