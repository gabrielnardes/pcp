package com.gabrielnardes.pcpapi.forecasting.repository;

import com.gabrielnardes.pcpapi.forecasting.entity.SimpleExponentialSmoothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleExponentialSmoothingRepository extends JpaRepository<SimpleExponentialSmoothing, Long> {
}
