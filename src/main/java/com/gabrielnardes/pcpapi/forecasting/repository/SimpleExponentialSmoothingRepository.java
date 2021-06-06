package com.gabrielnardes.pcpapi.forecasting.repository;

import com.gabrielnardes.pcpapi.forecasting.entity.SimpleExponentialSmoothing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleExponentialSmoothingRepository extends JpaRepository<SimpleExponentialSmoothing, Long> {
}
