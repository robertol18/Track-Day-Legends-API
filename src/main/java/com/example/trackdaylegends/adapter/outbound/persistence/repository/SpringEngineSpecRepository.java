package com.example.trackdaylegends.adapter.outbound.persistence.repository;

import com.example.trackdaylegends.adapter.outbound.persistence.entity.EngineSpecEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringEngineSpecRepository extends JpaRepository<EngineSpecEntity, Long> {

    @Query("SELECT e FROM EngineSpecEntity e WHERE " +
           "(:engineType IS NULL OR LOWER(e.engineType) = LOWER(:engineType)) AND " +
           "(:minHorsepower IS NULL OR e.horsepowerHp >= :minHorsepower) AND " +
           "(:maxZeroToHundred IS NULL OR e.zeroToHundredSeconds <= :maxZeroToHundred) AND " +
           "(:drivetrain IS NULL OR LOWER(e.drivetrain) = LOWER(:drivetrain)) AND " +
           "(:active IS NULL OR e.active = :active)")
    List<EngineSpecEntity> findByFilters(
            @Param("engineType") String engineType,
            @Param("minHorsepower") Integer minHorsepower,
            @Param("maxZeroToHundred") Double maxZeroToHundred,
            @Param("drivetrain") String drivetrain,
            @Param("active") Boolean active
    );

    List<EngineSpecEntity> findTop5ByActiveTrueOrderByZeroToHundredSecondsAsc();

    List<EngineSpecEntity> findTop5ByActiveTrueOrderByHorsepowerHpDesc();

    @Query("SELECT AVG(e.horsepowerHp) FROM EngineSpecEntity e")
    Double getAverageHorsepower();

    @Query("SELECT MIN(e.zeroToHundredSeconds) FROM EngineSpecEntity e")
    Double getFastestZeroToHundred();

    @Query("SELECT e.engineType FROM EngineSpecEntity e GROUP BY e.engineType ORDER BY COUNT(e) DESC")
    List<String> getMostCommonEngineTypes(Pageable pageable);
}
