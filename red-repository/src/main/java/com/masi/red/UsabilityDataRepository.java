package com.masi.red;

import com.masi.red.entity.UsabilityData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsabilityDataRepository extends JpaRepository<UsabilityData, Integer> {

    UsabilityData findTopByUsernameOrderByMeasurementNumberDesc(String username);
}
