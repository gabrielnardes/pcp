package com.gabrielnardes.pcpapi.forecasting.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SimpleExponentialSmoothingTest {

    @Test
    @DisplayName("Testing 1 calc() method")
    void calc1() {
        double[] data = {1, 432, 6, 12313, 534, 54};
        SimpleExponentialSmoothing ses = new SimpleExponentialSmoothing(data, 1, 0.012);
        ses.calc();

        double[] expectedSes = {1.000, 6.172, 6.170, 153.852, 158.414, 157.162};
        double[] actualSes = ses.getSes();

        assertArrayEquals(expectedSes, actualSes, 0.01,"getSes() must retrieve the correct forecasting.SES array");
    }

    @Test
    @DisplayName("Testing 2 calc() method")
    void calc2() {
        double[] data = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
        SimpleExponentialSmoothing ses = new SimpleExponentialSmoothing(data, 2, 0.8);
        ses.calc();

        double[] expectedSes = {22.600, 24.520, 20.104, 28.821, 26.564, 19.713, 27.143, 24.629, 28.926, 24.185,};
        double[] actualSes = ses.getSes();

        assertArrayEquals(expectedSes, actualSes, 0.01,"getSes() must retrieve the correct forecasting.SES array");
    }

    @Test
    @DisplayName("Testing 3 calc() method")
    void calc3() {
        double[] data = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
        SimpleExponentialSmoothing ses = new SimpleExponentialSmoothing(data, 4, 0.4);
        ses.calc();

        double[] expectedSes = {22.150, 25.690, 25.814, 22.688, 25.213, 24.728, 26.837, 25.302};
        double[] actualSes = ses.getSes();

        assertArrayEquals(expectedSes, actualSes, 0.01,"getSes() must retrieve the correct forecasting.SES array");
    }
}