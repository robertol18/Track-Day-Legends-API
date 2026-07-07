package com.example.trackdaylegends.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarModel {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private String segment;
    private String bodyStyle;
    private String country;
    private String description;
    private Boolean active;
    private List<EngineSpec> engineSpecs;

    // Default constructor
    public CarModel() {
        this.engineSpecs = new ArrayList<>();
    }

    // Full constructor for reconstruction
    public CarModel(Long id, String brand, String model, Integer year, String segment, 
                    String bodyStyle, String country, String description, Boolean active, 
                    List<EngineSpec> engineSpecs) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.segment = segment;
        this.bodyStyle = bodyStyle;
        this.country = country;
        this.description = description;
        this.active = active != null ? active : true;
        this.engineSpecs = engineSpecs != null ? new ArrayList<>(engineSpecs) : new ArrayList<>();
    }

    // Factory method for creating a new CarModel
    public static CarModel create(String brand, String model, Integer year, String segment, 
                                  String bodyStyle, String country, String description) {
        return new CarModel(null, brand, model, year, segment, bodyStyle, country, description, true, new ArrayList<>());
    }

    // Factory method for reconstruction from persistence
    public static CarModel reconstruct(Long id, String brand, String model, Integer year, String segment, 
                                        String bodyStyle, String country, String description, Boolean active, 
                                        List<EngineSpec> engineSpecs) {
        return new CarModel(id, brand, model, year, segment, bodyStyle, country, description, active, engineSpecs);
    }

    // Business Logic
    public void deactivate() {
        this.active = false;
        // In a real aggregate, we might also deactivate all engine specs
        for (EngineSpec spec : this.engineSpecs) {
            spec.deactivate();
        }
    }

    public void addEngineSpec(EngineSpec engineSpec) {
        if (engineSpec != null) {
            this.engineSpecs.add(engineSpec);
        }
    }

    public void removeEngineSpec(Long engineSpecId) {
        this.engineSpecs.removeIf(spec -> spec.getId() != null && spec.getId().equals(engineSpecId));
    }

    public void updateDetails(String brand, String model, Integer year, String segment, 
                              String bodyStyle, String country, String description, Boolean active) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.segment = segment;
        this.bodyStyle = bodyStyle;
        this.country = country;
        this.description = description;
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

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public String getSegment() {
        return segment;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getActive() {
        return active;
    }

    public List<EngineSpec> getEngineSpecs() {
        return Collections.unmodifiableList(engineSpecs);
    }
}
