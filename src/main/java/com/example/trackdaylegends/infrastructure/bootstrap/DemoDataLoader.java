package com.example.trackdaylegends.infrastructure.bootstrap;

import com.example.trackdaylegends.application.port.in.CarModelUseCase;
import com.example.trackdaylegends.application.port.in.EngineSpecUseCase;
import com.example.trackdaylegends.domain.model.CarModel;
import com.example.trackdaylegends.domain.model.EngineSpec;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoDataLoader implements CommandLineRunner {

    private final CarModelUseCase carModelUseCase;
    private final EngineSpecUseCase engineSpecUseCase;

    public DemoDataLoader(CarModelUseCase carModelUseCase, EngineSpecUseCase engineSpecUseCase) {
        this.carModelUseCase = carModelUseCase;
        this.engineSpecUseCase = engineSpecUseCase;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Toyota GR Yaris
        CarModel grYaris = CarModel.create(
                "Toyota",
                "GR Yaris",
                2021,
                "B",
                "Hatchback",
                "Japón",
                "Especial de homologación para el WRC. Desarrollado directamente por Gazoo Racing, cuenta con tracción total GR-Four avanzada y un chasis ultraligero que lo convierte en un monstruo de tramos y track days ratoneros."
        );
        grYaris = carModelUseCase.createCarModel(grYaris);

        engineSpecUseCase.createEngineSpec(grYaris.getId(), EngineSpec.create(
                "RZ / Circuit Pack",
                "Gasolina Turbo",
                "1.6L L3 (G16E-GTS)",
                1618,
                261,
                360,
                "Manual 6 velocidades",
                "AWD",
                5.5,
                230,
                8.2,
                grYaris.getId()
        ));

        // 2. Honda Civic Type R
        CarModel civicTypeR = CarModel.create(
                "Honda",
                "Civic Type R",
                2023,
                "C",
                "Hatchback",
                "Japón",
                "La generación FL5 representa la cumbre de la tracción delantera. Con una puesta a punto de chasis quirúrgica y un refinamiento aerodinámico excepcional, tiene récords de FWD en circuitos legendarios como Suzuka y Nürburgring."
        );
        civicTypeR = carModelUseCase.createCarModel(civicTypeR);

        engineSpecUseCase.createEngineSpec(civicTypeR.getId(), EngineSpec.create(
                "Type R (FL5)",
                "Gasolina Turbo",
                "2.0L L4 (K20C1)",
                1996,
                329,
                420,
                "Manual 6 velocidades",
                "FWD",
                5.4,
                275,
                8.4,
                civicTypeR.getId()
        ));

        // 3. BMW M3 Competition
        CarModel m3 = CarModel.create(
                "BMW",
                "M3",
                2022,
                "D",
                "Sedan",
                "Alemania",
                "La berlina deportiva de referencia mundial en su generación G80. Ofrece una rigidez estructural extrema, un motor S58 derivado de competición y una agilidad impropia de su tamaño."
        );
        m3 = carModelUseCase.createCarModel(m3);

        engineSpecUseCase.createEngineSpec(m3.getId(), EngineSpec.create(
                "Competition RWD",
                "Gasolina Twin-Turbo",
                "3.0L L6 (S58)",
                2993,
                510,
                650,
                "Automática 8 velocidades (M Steptronic)",
                "RWD",
                3.9,
                290,
                9.8,
                m3.getId()
        ));

        engineSpecUseCase.createEngineSpec(m3.getId(), EngineSpec.create(
                "Competition M xDrive",
                "Gasolina Twin-Turbo",
                "3.0L L6 (S58)",
                2993,
                510,
                650,
                "Automática 8 velocidades (M Steptronic)",
                "AWD",
                3.5,
                290,
                10.1,
                m3.getId()
        ));

        // 4. Porsche 911 Carrera
        CarModel porsche911 = CarModel.create(
                "Porsche",
                "911",
                2024,
                "S",
                "Coupe",
                "Alemania",
                "La silueta más icónica del automovilismo deportivo (generación 992). El 'Nueveonce' destaca por su motor bóxer trasero, reparto de pesos optimizado y un tacto de conducción legendario apto tanto para el día a día como para batir cronos."
        );
        porsche911 = carModelUseCase.createCarModel(porsche911);

        engineSpecUseCase.createEngineSpec(porsche911.getId(), EngineSpec.create(
                "Carrera standard",
                "Gasolina Twin-Turbo",
                "3.0L Boxer 6",
                2981,
                385,
                450,
                "Automática doble embrague (PDK) 8 vel.",
                "RWD",
                4.2,
                293,
                10.3,
                porsche911.getId()
        ));

        engineSpecUseCase.createEngineSpec(porsche911.getId(), EngineSpec.create(
                "Carrera GTS",
                "Gasolina Twin-Turbo",
                "3.0L Boxer 6",
                2981,
                480,
                570,
                "Automática doble embrague (PDK) 8 vel.",
                "RWD",
                3.4,
                311,
                10.7,
                porsche911.getId()
        ));

        // 5. Audi RS3 Sportback
        CarModel rs3 = CarModel.create(
                "Audi",
                "RS3 Sportback",
                2023,
                "C",
                "Hatchback",
                "Alemania",
                "Compacto superdeportivo que enamora por el rugido único de su motor de 5 cilindros y el novedoso sistema RS Torque Splitter, que permite derivar toda la potencia del eje trasero a una sola rueda para una dinámica de locura."
        );
        rs3 = carModelUseCase.createCarModel(rs3);

        engineSpecUseCase.createEngineSpec(rs3.getId(), EngineSpec.create(
                "Standard 2.5 TFSI",
                "Gasolina Turbo",
                "2.5L L5 (TFSI)",
                2480,
                400,
                500,
                "Automática doble embrague (S tronic) 7 vel.",
                "AWD",
                3.8,
                250,
                9.0,
                rs3.getId()
        ));

        engineSpecUseCase.createEngineSpec(rs3.getId(), EngineSpec.create(
                "Performance Edition",
                "Gasolina Turbo",
                "2.5L L5 (TFSI)",
                2480,
                407,
                500,
                "Automática doble embrague (S tronic) 7 vel.",
                "AWD",
                3.8,
                300,
                9.1,
                rs3.getId()
        ));

        // 6. Mercedes-AMG A45 S
        CarModel a45s = CarModel.create(
                "Mercedes-Benz",
                "AMG A45 S",
                2022,
                "C",
                "Hatchback",
                "Alemania",
                "El motor de 4 cilindros de producción en serie más potente del mundo (M139) ensamblado a mano bajo la filosofía 'un hombre, un motor'. Ofrece unas prestaciones asombrosas y tracción total inteligente con modo Drift."
        );
        a45s = carModelUseCase.createCarModel(a45s);

        engineSpecUseCase.createEngineSpec(a45s.getId(), EngineSpec.create(
                "AMG 45 S 4MATIC+",
                "Gasolina Turbo",
                "2.0L L4 (M139)",
                1991,
                421,
                500,
                "Automática doble embrague (AMG SPEEDSHIFT) 8 vel.",
                "AWD",
                3.9,
                270,
                8.8,
                a45s.getId()
        ));

        // 7. Alpine A110
        CarModel alpine = CarModel.create(
                "Alpine",
                "A110",
                2023,
                "S",
                "Coupe",
                "Francia",
                "Bajo el lema de ligereza heredado del clásico original de rallyes, este deportivo de motor central pesa solo 1100 kg. Es la agilidad hecha coche, ofreciendo un comportamiento divertido e intuitivo en curvas de montaña."
        );
        alpine = carModelUseCase.createCarModel(alpine);

        engineSpecUseCase.createEngineSpec(alpine.getId(), EngineSpec.create(
                "Standard 1.8 TCe",
                "Gasolina Turbo",
                "1.8L L4 (TCe)",
                1798,
                252,
                320,
                "Automática doble embrague (EDC) 7 vel.",
                "RWD",
                4.5,
                250,
                6.7,
                alpine.getId()
        ));

        engineSpecUseCase.createEngineSpec(alpine.getId(), EngineSpec.create(
                "A110 S",
                "Gasolina Turbo",
                "1.8L L4 (TCe)",
                1798,
                300,
                340,
                "Automática doble embrague (EDC) 7 vel.",
                "RWD",
                4.2,
                275,
                6.9,
                alpine.getId()
        ));

        // 8. Renault Megane R.S. Trophy
        CarModel meganeRS = CarModel.create(
                "Renault",
                "Megane R.S.",
                2021,
                "C",
                "Hatchback",
                "Francia",
                "El último de una dinastía gloriosa de Renault Sport. La versión Trophy incorpora el chasis Cup con amortiguadores y muelles rígidos, diferencial autoblocante Torsen y dirección a las 4 ruedas (4Control)."
        );
        meganeRS = carModelUseCase.createCarModel(meganeRS);

        engineSpecUseCase.createEngineSpec(meganeRS.getId(), EngineSpec.create(
                "Trophy 300",
                "Gasolina Turbo",
                "1.8L L4 (TCe)",
                1798,
                300,
                420,
                "Automática doble embrague (EDC) 6 vel.",
                "FWD",
                5.7,
                260,
                8.0,
                meganeRS.getId()
        ));
    }
}
