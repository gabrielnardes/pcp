package com.gabrielnardes.pcpapi.dataInitialization;

import com.gabrielnardes.pcpapi.forecasting.entity.SimpleExponentialSmoothing;
import com.gabrielnardes.pcpapi.forecasting.repository.SimpleExponentialSmoothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleExponentialSmoothingData implements ApplicationRunner {

    @Autowired
    SimpleExponentialSmoothingRepository simpleExponentialSmoothingRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Initializing SimpleExponentialSmoothing datasource creation");

        SimpleExponentialSmoothing ses1 = new SimpleExponentialSmoothing(new double[]{1, 432, 6, 12313, 534, 54}, 1, 0.012);
        ses1.calc();
        simpleExponentialSmoothingRepository.save(ses1);

        SimpleExponentialSmoothing ses2 = new SimpleExponentialSmoothing(new double[]{24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23}, 2, 0.8);
        ses2.calc();
        simpleExponentialSmoothingRepository.save(ses2);

        System.out.println("Finishing SimpleExponentialSmoothing datasource creation");
    }
}