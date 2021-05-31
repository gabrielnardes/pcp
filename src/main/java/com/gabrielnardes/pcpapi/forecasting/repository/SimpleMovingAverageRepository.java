package com.gabrielnardes.pcpapi.forecasting.repository;

import com.gabrielnardes.pcpapi.forecasting.entity.SimpleMovingAverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleMovingAverageRepository extends JpaRepository<SimpleMovingAverage, Long> {
}
