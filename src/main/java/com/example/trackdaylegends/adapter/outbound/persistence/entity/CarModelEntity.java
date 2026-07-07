package com.example.trackdaylegends.adapter.outbound.persistence.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car_models")
public class CarModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(name = "model_year", nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String segment;

    private String bodyStyle;
    private String country;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EngineSpecEntity> engineSpecs = new ArrayList<>();

    public CarModelEntity() {}

    public CarModelEntity(Long id, String brand, String model, Integer year, String segment, 
                          String bodyStyle, String country, String description, Boolean active) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.segment = segment;
        this.bodyStyle = bodyStyle;
        this.country = country;
        this.description = description;
        this.active = active;
    }

    public void addEngineSpec(EngineSpecEntity specEntity) {
        engineSpecs.add(specEntity);
        specEntity.setCarModel(this);
    }

    public void removeEngineSpec(EngineSpecEntity specEntity) {
        engineSpecs.remove(specEntity);
        specEntity.setCarModel(null);
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

    public List<EngineSpecEntity> getEngineSpecs() {
        return engineSpecs;
    }

    public void setEngineSpecs(List<EngineSpecEntity> engineSpecs) {
        this.engineSpecs = engineSpecs;
    }
}
