package com.gabrielnardes.pcpapi.dataInitialization.forecasting;

import com.gabrielnardes.pcpapi.forecasting.entity.WeightedMovingAverage;
import com.gabrielnardes.pcpapi.forecasting.repository.WeightedMovingAverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class WeightedMovingAverageData implements ApplicationRunner {

    @Autowired
    WeightedMovingAverageRepository weightedMovingAverageRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Initializing WeightedMovingAverage datasource creation");

        WeightedMovingAverage wma1 = new WeightedMovingAverage(new double[]{24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23}, 3, new double[]{0.5, 0.3, 0.2});
        wma1.calc();
        weightedMovingAverageRepository.save(wma1);

        WeightedMovingAverage wma2 = new WeightedMovingAverage(new double[]{1,3,5,7,9,11,13,15}, 4,new double[]{0.5, 0.3, 0.1, 0.1});
        wma2.calc();
        weightedMovingAverageRepository.save(wma2);
//
//        WeightedMovingAverage wma3 = new WeightedMovingAverage(new double[]{2, 4, 43, 12, 5, 32, 65, 34, 1, 76, 23, 21, 65, 23, 12, 45, 65, 3}, 5);
//        wma3.calc();
//        weightedMovingAverageRepository.save(wma3);
//
//        WeightedMovingAverage wma4 = new WeightedMovingAverage(new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 6);
//        wma4.calc();
//        weightedMovingAverageRepository.save(wma4);
//
//        WeightedMovingAverage wma5 = new WeightedMovingAverage(new double[]{1, 3, 5, 7, 9, 11, 13, 15, 17}, 2);
//        wma5.calc();
//        weightedMovingAverageRepository.save(wma5);

        System.out.println("Finishing WeightedMovingAverage datasource creation");
    }
}