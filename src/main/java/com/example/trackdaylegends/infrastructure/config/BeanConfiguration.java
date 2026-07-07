package com.example.trackdaylegends.infrastructure.config;

import com.example.trackdaylegends.application.port.in.CarModelUseCase;
import com.example.trackdaylegends.application.port.in.EngineSpecUseCase;
import com.example.trackdaylegends.application.port.in.StatsUseCase;
import com.example.trackdaylegends.application.port.out.CarModelRepositoryPort;
import com.example.trackdaylegends.application.port.out.EngineSpecRepositoryPort;
import com.example.trackdaylegends.application.usecase.CarModelService;
import com.example.trackdaylegends.application.usecase.EngineSpecService;
import com.example.trackdaylegends.application.usecase.StatsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CarModelUseCase carModelUseCase(CarModelRepositoryPort carModelRepositoryPort) {
        return new CarModelService(carModelRepositoryPort);
    }

    @Bean
    public EngineSpecUseCase engineSpecUseCase(EngineSpecRepositoryPort engineSpecRepositoryPort, 
                                               CarModelRepositoryPort carModelRepositoryPort) {
        return new EngineSpecService(engineSpecRepositoryPort, carModelRepositoryPort);
    }

    @Bean
    public StatsUseCase statsUseCase(CarModelRepositoryPort carModelRepositoryPort, 
                                     EngineSpecRepositoryPort engineSpecRepositoryPort) {
        return new StatsService(carModelRepositoryPort, engineSpecRepositoryPort);
    }
}
