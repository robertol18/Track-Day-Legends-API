package com.example.trackdaylegends;

import com.example.trackdaylegends.application.port.in.CarModelUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackDayLegendsApplicationTests {

    @Autowired
    private CarModelUseCase carModelUseCase;

    @Test
    void contextLoadsAndInitialDataIsPresent() {
        assertNotNull(carModelUseCase);
        // Verify that our DemoDataLoader initialized the database with exactly 8 track day models
        int count = carModelUseCase.getAllCarModels(null, null, null, null).size();
        assertEquals(8, count, "Exactly 8 initial track-focused sports car models should have been loaded");
    }
}
