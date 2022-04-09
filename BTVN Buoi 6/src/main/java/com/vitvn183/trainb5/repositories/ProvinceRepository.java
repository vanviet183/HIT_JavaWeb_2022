package com.vitvn183.trainb5.repositories;

import com.vitvn183.trainb5.entity.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Provinces, Long> {

    Optional<Provinces> findByCode(Long code);
    void deleteByCode(Long code);
}
