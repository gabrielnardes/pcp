package forecasting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LinearLeastSquaresTest {

    @Test
    @DisplayName("Testing print() method")
    void print() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Data x = new Data(1, 2, 3, 4, 5, 6, 7);
        Data y = new Data(1.5, 3.8, 6.7, 9,11.2, 13.6, 16);
        LinearLeastSquares lls = new LinearLeastSquares(x, y);
        lls.print();

        String expectedOutput  = "LLS\n" +
                "y = 2,41x - 0,83\n";

        assertEquals(expectedOutput, outContent
                        .toString()
                        .replaceAll("\r", ""),
                "Console output must be equal");
    }

    @Test
    @DisplayName("Testing calc() method")
    void calc() {
        Data x1 = new Data(1, 2, 3, 4, 5, 6, 7, 8);
        Data y1 = new Data(256, 312, 426, 278, 298, 387, 517, 349);
        LinearLeastSquares lls1 = new LinearLeastSquares(x1, y1);

        Data x2 = new Data(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Data y2 = lls1.calc(x2);

        double[] expectedCalc = {287.083, 305.881, 324.679, 343.476, 362.274, 381.071, 399.869, 418.667, 437.464};
        assertArrayEquals(y2.getData(), expectedCalc, 0.001, "calc() must retrieve the correct LLS array");
    }

    @Test
    @DisplayName("Testing seasoned() method")
    void season() {
        Data x1 = new Data(1, 2, 3, 4, 5, 6, 7, 8);
        Data y1 = new Data(256, 312, 426, 278, 298, 387, 517, 349);
        LinearLeastSquares lls1 = new LinearLeastSquares(x1, y1);

        Data x2 = new Data(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Data y2 = lls1.calc(x2);
        Data y3 = lls1.seasoned(y1, y2, 4);

        double[] expectedSeasoned = {246.075, 311.320, 422.892, 282.161, 310.525, 387.847, 520.827, 343.929, 374.975};
        assertArrayEquals(y3.getData(), expectedSeasoned, 0.001, "seasoned() must retrieve the correct seasoned LLS array");
    }
}