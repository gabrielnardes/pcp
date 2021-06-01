package com.gabrielnardes.pcpapi.forecasting.service;

import com.gabrielnardes.pcpapi.forecasting.entity.SimpleMovingAverage;
import com.gabrielnardes.pcpapi.forecasting.repository.SimpleMovingAverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleMovingAverageService {
    @Autowired
    private SimpleMovingAverageRepository simpleMovingAverageRepository;

    public SimpleMovingAverage create(SimpleMovingAverage sma) {
        System.out.println("period " + sma.getPeriod());
        System.out.println("data " + sma.getData());
        sma.calc();
        return simpleMovingAverageRepository.save(sma);
    }
}
