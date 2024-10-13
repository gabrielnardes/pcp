package com.gabrielnardes.pcpapi.forecasting.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class LinearLeastSquaresTest {

    @Test
    @DisplayName("Testing calc() method")
    void calc() {
        double[] x1 = {1, 2, 3, 4, 5, 6, 7, 8};
        double[] y1 = {256, 312, 426, 278, 298, 387, 517, 349};
        LinearLeastSquares lls1 = new LinearLeastSquares(x1, y1);

        double[] x2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        double[] y2 = lls1.calc(x2);

        double[] expectedCalc = {287.083, 305.881, 324.679, 343.476, 362.274, 381.071, 399.869, 418.667, 437.464};
        assertArrayEquals(y2, expectedCalc, 0.001, "calc() must retrieve the correct LLS array");
    }

    @Test
    @DisplayName("Testing seasoned() method")
    void season() {
        double[] x1 = {1, 2, 3, 4, 5, 6, 7, 8};
        double[] y1 = {256, 312, 426, 278, 298, 387, 517, 349};
        LinearLeastSquares lls1 = new LinearLeastSquares(x1, y1);

        double[] x2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        double[] y2 = lls1.calc(x2);
        double[] y3 = lls1.seasoned(y1, y2, 4);

        double[] expectedSeasoned = {246.075, 311.320, 422.892, 282.161, 310.525, 387.847, 520.827, 343.929, 374.975};
        assertArrayEquals(y3, expectedSeasoned, 0.001, "seasoned() must retrieve the correct seasoned LLS array");
    }
}