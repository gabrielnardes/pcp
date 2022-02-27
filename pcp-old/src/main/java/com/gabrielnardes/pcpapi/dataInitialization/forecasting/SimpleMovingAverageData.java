package com.gabrielnardes.pcpapi.dataInitialization.forecasting;

import com.gabrielnardes.pcpapi.forecasting.entity.SimpleMovingAverage;
import com.gabrielnardes.pcpapi.forecasting.repository.SimpleMovingAverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleMovingAverageData implements ApplicationRunner {

    @Autowired
    SimpleMovingAverageRepository simpleMovingAverageRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Initializing SimpleMovingAverage datasource creation");

        SimpleMovingAverage sma1 = new SimpleMovingAverage(new double[]{24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23}, 2);
        sma1.calc();
        simpleMovingAverageRepository.save(sma1);

        SimpleMovingAverage sma2 = new SimpleMovingAverage(new double[]{2, 4, 6, 8, 12, 14, 16, 18, 20}, 5);
        sma2.calc();
        simpleMovingAverageRepository.save(sma2);

        SimpleMovingAverage sma3 = new SimpleMovingAverage(new double[]{2, 4, 43, 12, 5, 32, 65, 34, 1, 76, 23, 21, 65, 23, 12, 45, 65, 3}, 5);
        sma3.calc();
        simpleMovingAverageRepository.save(sma3);

        SimpleMovingAverage sma4 = new SimpleMovingAverage(new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 6);
        sma4.calc();
        simpleMovingAverageRepository.save(sma4);

        SimpleMovingAverage sma5 = new SimpleMovingAverage(new double[]{1, 3, 5, 7, 9, 11, 13, 15, 17}, 2);
        sma5.calc();
        simpleMovingAverageRepository.save(sma5);

        System.out.println("Finishing SimpleMovingAverage datasource creation");
    }
}