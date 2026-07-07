package com.example.trackdaylegends.adapter.inbound.web.dto;

import java.util.List;

public class CarModelResponse {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private String segment;
    private String bodyStyle;
    private String country;
    private String description;
    private Boolean active;
    private List<EngineSpecResponse> engineSpecs;

    public CarModelResponse() {}

    public CarModelResponse(Long id, String brand, String model, Integer year, String segment, 
                            String bodyStyle, String country, String description, Boolean active, 
                            List<EngineSpecResponse> engineSpecs) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.segment = segment;
        this.bodyStyle = bodyStyle;
        this.country = country;
        this.description = description;
        this.active = active;
        this.engineSpecs = engineSpecs;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<EngineSpecResponse> getEngineSpecs() {
        return engineSpecs;
    }

    public void setEngineSpecs(List<EngineSpecResponse> engineSpecs) {
        this.engineSpecs = engineSpecs;
    }
}
