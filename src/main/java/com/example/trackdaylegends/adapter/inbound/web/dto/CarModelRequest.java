package com.example.trackdaylegends.adapter.inbound.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CarModelRequest {

    @NotBlank(message = "Brand cannot be empty")
    private String brand;

    @NotBlank(message = "Model cannot be empty")
    private String model;

    @NotNull(message = "Year is mandatory")
    @Min(value = 1950, message = "Year must be at least 1950")
    @Max(value = 2030, message = "Year must be at most 2030")
    private Integer year;

    @NotBlank(message = "Segment cannot be empty")
    private String segment;

    private String bodyStyle;
    private String country;
    private String description;
    private Boolean active = true;

    public CarModelRequest() {}

    public CarModelRequest(String brand, String model, Integer year, String segment, 
                           String bodyStyle, String country, String description, Boolean active) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.segment = segment;
        this.bodyStyle = bodyStyle;
        this.country = country;
        this.description = description;
        this.active = active != null ? active : true;
    }

    // Getters and Setters
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
}
