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
                "Japan",
                "WRC homologation special. Developed directly by Gazoo Racing, it features the advanced GR-Four all-wheel-drive system and an ultra-lightweight chassis, making it a monster on tight B-roads and track days."
        );
        grYaris = carModelUseCase.createCarModel(grYaris);

        engineSpecUseCase.createEngineSpec(grYaris.getId(), EngineSpec.create(
                "RZ / Circuit Pack",
                "Turbo Petrol",
                "1.6L L3 (G16E-GTS)",
                1618,
                261,
                360,
                "6-speed manual",
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
                "Japan",
                "The FL5 generation represents the pinnacle of front-wheel-drive dynamics. With surgical chassis tuning and exceptional aerodynamic refinement, it holds FWD lap records on legendary tracks like Suzuka and Nürburgring."
        );
        civicTypeR = carModelUseCase.createCarModel(civicTypeR);

        engineSpecUseCase.createEngineSpec(civicTypeR.getId(), EngineSpec.create(
                "Type R (FL5)",
                "Turbo Petrol",
                "2.0L L4 (K20C1)",
                1996,
                329,
                420,
                "6-speed manual",
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
                "Germany",
                "The reference world standard for sports sedans in its G80 generation. It offers extreme structural rigidity, a motorsport-derived S58 engine, and an agility that defies its footprint."
        );
        m3 = carModelUseCase.createCarModel(m3);

        engineSpecUseCase.createEngineSpec(m3.getId(), EngineSpec.create(
                "Competition RWD",
                "Twin-Turbo Petrol",
                "3.0L L6 (S58)",
                2993,
                510,
                650,
                "8-speed automatic (M Steptronic)",
                "RWD",
                3.9,
                290,
                9.8,
                m3.getId()
        ));

        engineSpecUseCase.createEngineSpec(m3.getId(), EngineSpec.create(
                "Competition M xDrive",
                "Twin-Turbo Petrol",
                "3.0L L6 (S58)",
                2993,
                510,
                650,
                "8-speed automatic (M Steptronic)",
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
                "Germany",
                "The most iconic silhouette in motorsport history (992 generation). The 'Nine-Eleven' stands out for its rear-mounted boxer engine, optimized weight distribution, and a legendary driving feel suited for both daily use and record lap times."
        );
        porsche911 = carModelUseCase.createCarModel(porsche911);

        engineSpecUseCase.createEngineSpec(porsche911.getId(), EngineSpec.create(
                "Carrera standard",
                "Twin-Turbo Petrol",
                "3.0L Boxer 6",
                2981,
                385,
                450,
                "8-speed dual-clutch (PDK)",
                "RWD",
                4.2,
                293,
                10.3,
                porsche911.getId()
        ));

        engineSpecUseCase.createEngineSpec(porsche911.getId(), EngineSpec.create(
                "Carrera GTS",
                "Twin-Turbo Petrol",
                "3.0L Boxer 6",
                2981,
                480,
                570,
                "8-speed dual-clutch (PDK)",
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
                "Germany",
                "A high-performance compact sports car loved for the unique exhaust note of its 5-cylinder engine and the innovative RS Torque Splitter system, which allows torque vectoring to the outer rear wheel for drift-ready dynamics."
        );
        rs3 = carModelUseCase.createCarModel(rs3);

        engineSpecUseCase.createEngineSpec(rs3.getId(), EngineSpec.create(
                "Standard 2.5 TFSI",
                "Turbo Petrol",
                "2.5L L5 (TFSI)",
                2480,
                400,
                500,
                "7-speed dual-clutch (S tronic)",
                "AWD",
                3.8,
                250,
                9.0,
                rs3.getId()
        ));

        engineSpecUseCase.createEngineSpec(rs3.getId(), EngineSpec.create(
                "Performance Edition",
                "Turbo Petrol",
                "2.5L L5 (TFSI)",
                2480,
                407,
                500,
                "7-speed dual-clutch (S tronic)",
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
                "Germany",
                "The world's most powerful series-production 4-cylinder engine (M139), hand-assembled under the 'One Man, One Engine' philosophy. It delivers jaw-dropping performance paired with intelligent AWD and a standard Drift Mode."
        );
        a45s = carModelUseCase.createCarModel(a45s);

        engineSpecUseCase.createEngineSpec(a45s.getId(), EngineSpec.create(
                "AMG 45 S 4MATIC+",
                "Turbo Petrol",
                "2.0L L4 (M139)",
                1991,
                421,
                500,
                "8-speed dual-clutch (AMG SPEEDSHIFT)",
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
                "France",
                "Embodying the philosophy of lightness inherited from the classic original rally car, this mid-engined French sports car weighs only 1100 kg. It is agility in car form, offering playful and intuitive handling on twisty mountain passes."
        );
        alpine = carModelUseCase.createCarModel(alpine);

        engineSpecUseCase.createEngineSpec(alpine.getId(), EngineSpec.create(
                "Standard 1.8 TCe",
                "Turbo Petrol",
                "1.8L L4 (TCe)",
                1798,
                252,
                320,
                "7-speed dual-clutch (EDC)",
                "RWD",
                4.5,
                250,
                6.7,
                alpine.getId()
        ));

        engineSpecUseCase.createEngineSpec(alpine.getId(), EngineSpec.create(
                "A110 S",
                "Turbo Petrol",
                "1.8L L4 (TCe)",
                1798,
                300,
                340,
                "7-speed dual-clutch (EDC)",
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
                "France",
                "The last of a glorious dynasty from Renault Sport. The Trophy version features the Cup chassis with stiffer springs and dampers, a Torsen limited-slip differential, and the 4Control four-wheel-steering system."
        );
        meganeRS = carModelUseCase.createCarModel(meganeRS);

        engineSpecUseCase.createEngineSpec(meganeRS.getId(), EngineSpec.create(
                "Trophy 300",
                "Turbo Petrol",
                "1.8L L4 (TCe)",
                1798,
                300,
                420,
                "6-speed dual-clutch (EDC)",
                "FWD",
                5.7,
                260,
                8.0,
                meganeRS.getId()
                ));
    }
}
