package com.vitvn183.trainb5.repositories;

import com.vitvn183.trainb5.entity.Districts;
import com.vitvn183.trainb5.entity.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<Districts, Long> {

    Optional<Districts> findByCode(Long code);
    void deleteByCode(Long code);
}
