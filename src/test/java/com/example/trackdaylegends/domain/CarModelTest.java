package com.example.trackdaylegends.domain;

import com.example.trackdaylegends.domain.model.CarModel;
import com.example.trackdaylegends.domain.model.EngineSpec;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarModelTest {

    @Test
    void shouldCreateCarModelWithDefaults() {
        CarModel model = CarModel.create(
                "Toyota", "GR Yaris", 2021, "B", "Hatchback", "Japón", "Homologation special."
        );

        assertNull(model.getId());
        assertEquals("Toyota", model.getBrand());
        assertEquals("GR Yaris", model.getModel());
        assertEquals(2021, model.getYear());
        assertEquals("B", model.getSegment());
        assertTrue(model.getActive());
        assertTrue(model.getEngineSpecs().isEmpty());
    }

    @Test
    void shouldDeactivateCarModelAndItsEngineSpecs() {
        CarModel model = CarModel.create(
                "Porsche", "911", 2024, "S", "Coupe", "Alemania", "Icon."
        );
        EngineSpec spec = EngineSpec.create(
                "Carrera", "Gasolina", "3.0L Boxer 6", 2981, 385, 450, "PDK", "RWD", 4.2, 293, 10.3, 1L
        );
        model.addEngineSpec(spec);

        assertTrue(model.getActive());
        assertTrue(spec.getActive());

        model.deactivate();

        assertFalse(model.getActive());
        assertFalse(spec.getActive());
    }

    @Test
    void shouldAddAndRemoveEngineSpec() {
        CarModel model = CarModel.create(
                "Honda", "Civic Type R", 2023, "C", "Hatchback", "Japón", "Type R."
        );
        EngineSpec spec = new EngineSpec(
                10L, "Type R", "Gasolina", "2.0L", 1996, 329, 420, "Manual", "FWD", 5.4, 275, 8.4, true, null
        );

        model.addEngineSpec(spec);
        assertEquals(1, model.getEngineSpecs().size());

        model.removeEngineSpec(10L);
        assertTrue(model.getEngineSpecs().isEmpty());
    }
}
