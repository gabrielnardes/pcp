package com.gabrielnardes.pcpapi.forecasting.repository;

import com.gabrielnardes.pcpapi.forecasting.entity.WeightedMovingAverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightedMovingAverageRepository extends JpaRepository<WeightedMovingAverage, Long> {
}
