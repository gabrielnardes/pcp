public class SMA {
    private final Data d;
    private final double[] sma;
    private final int period;

    public SMA(Data _d, int period) {
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
        for (int i = 0; i <= d.length() - this.period; i++) {
            double sum = 0;

            for (int j = i; j < i + this.period; j++) {
                sum += d.get(j);
            }

           this.sma[i] = sum / this.period;
        }
    }
}
