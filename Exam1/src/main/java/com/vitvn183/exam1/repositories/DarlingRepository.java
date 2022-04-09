package com.vitvn183.exam1.repositories;

import com.vitvn183.exam1.dao.Address;
import com.vitvn183.exam1.dao.Darling;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DarlingRepository extends JpaRepository<Darling, Long> {

    List<Darling> findByStatusIsNot(Integer status);
    List<Darling> findByStatus(Integer status);

}
