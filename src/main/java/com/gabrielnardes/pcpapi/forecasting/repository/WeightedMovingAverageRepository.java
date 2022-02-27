package com.gabrielnardes.pcpapi.forecasting.repository;

import com.gabrielnardes.pcpapi.forecasting.entity.WeightedMovingAverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightedMovingAverageRepository extends JpaRepository<WeightedMovingAverage, Long> {
}
