package com.masi.red;

import com.masi.red.entity.UsabilityData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsabilityDataRepository extends JpaRepository<UsabilityData, Integer> {

    Optional<UsabilityData> findTopByUsernameOrderByMeasurementNumberDesc(String username);
}
