package com.example.trackdaylegends.adapter.outbound.persistence.repository;

import com.example.trackdaylegends.adapter.outbound.persistence.entity.CarModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringCarModelRepository extends JpaRepository<CarModelEntity, Long> {

    @Query("SELECT DISTINCT c FROM CarModelEntity c LEFT JOIN FETCH c.engineSpecs WHERE " +
           "(:brand IS NULL OR LOWER(c.brand) LIKE LOWER(CONCAT('%', :brand, '%'))) AND " +
           "(:year IS NULL OR c.year = :year) AND " +
           "(:segment IS NULL OR LOWER(c.segment) = LOWER(:segment)) AND " +
           "(:active IS NULL OR c.active = :active)")
    List<CarModelEntity> findByFilters(
            @Param("brand") String brand,
            @Param("year") Integer year,
            @Param("segment") String segment,
            @Param("active") Boolean active
    );

    @Query("SELECT COUNT(DISTINCT c.brand) FROM CarModelEntity c")
    long countDistinctBrands();
}
