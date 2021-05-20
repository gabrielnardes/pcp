package forecasting;

import data.Data;

public class SimpleMovingAverage {
    private final Data d;
    private final double[] sma;
    private final int period;

    public SimpleMovingAverage(Data _d, int period) {
        this.d = _d;
        this.period = period;

        sma = new double[(int) d.length() - period + 1];
    }

    public void print() {
        System.out.println("SMA with period " + period);
        for (int i = 0; i < sma.length; i++) {
            System.out.printf("P[%d]: %.2f\n", i + period, sma[i]);
        }
    }

    public void calc() {
        for (int i = 0; i <= d.length() - period; i++) {
            double sum = 0;

            for (int j = i; j < i + period; j++) {
                sum += d.get(j);
            }

            sma[i] = sum / period;
        }
    }

    public double get(int i) {
        return sma[i];
    }

    public double[] getSma() {
        return sma;
    }
}
