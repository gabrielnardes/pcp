package forecasting;

import data.Data;

public class WeightedMovingAverage {
    private final Data d;
    private final double[] wma;
    private final double[] w;
    private final int period;

    public WeightedMovingAverage(Data _d, int period, double ... w) {
        this.d = _d;
        this.period = period;
        this.w = w;

        double wSum = 0;
        for (double el : w) {
            wSum += el;
        }
        if (wSum != 1.0) {
            System.out.println("Weight sum not equal to 1");
        }

        wma = new double[(int) d.length() - period + 1];
    }

    public void print() {
        System.out.println("WMA with period " + period);
        for (int i = 0; i < wma.length; i++) {
            System.out.printf("P[%d]: %.2f\n", i + period, wma[i]);
        }
    }

    public void calc() {
        for (int i = 0; i <= d.length() - this.period; i++) {
            double sum = 0;
            int wId = 0;

            for (int j = i; j < i + this.period; j++) {
                sum += d.get(j) * w[wId];
                wId++;
            }

            this.wma[i] = sum;
        }
    }

    public double[] getWma() {
        return wma;
    }

    public double get(int i) {
        return wma[i];
    }
}
