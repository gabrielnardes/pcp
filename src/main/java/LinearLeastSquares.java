public class LinearLeastSquares {
    private final double m;
    private final double b;

    public LinearLeastSquares(Data x, Data y) {

        if (x.length() != y.length()) {
            System.out.println("X and Y data set must be equal length");
        }
        double n = x.length();

        double xSum = 0;
        for (int i = 0; i < n; i++) {
            xSum += x.get(i);
        }

        double ySum = 0;
        for (int i = 0; i < n; i++) {
            ySum += y.get(i);
        }

        double xySum = 0;
        for (int i = 0; i < n; i++) {
            xySum += x.get(i) * y.get(i);
        }

        double xxSum = 0;
        for (int i = 0; i < n; i++) {
            xxSum += x.get(i) * x.get(i);
        }

        m = (n *xySum - xSum*ySum) / (n *xxSum - xSum*xSum);
        b = (ySum - m*xSum) / n;
    }

    public void print() {
        System.out.println("LLS");

        if (b > 0) {
            System.out.printf("y = %.2fx + %.2f\n", m, b);
        } else {
            System.out.printf("y = %.2fx - %.2f\n", m, Math.abs(b));
        }
    }

    public double calc(double x) {
        return m * x + b;
    }

    public Data calc(Data x) {
        double[] y = new double[x.length()];

        for (int i = 0; i < x.length(); i++) {
            y[i] = m * x.get(i) + b;
        }

        return new Data(y);
    }

    public Data season(Data demand, Data regression, int period) {
        double[] ratio = new double[demand.length()];
        for (int i = 0; i < ratio.length; i++) {
            ratio[i] = demand.get(i) / regression.get(i);
        }

        double[] m = new double[period];
        for (int i = 0; i < m.length; i++) {
            m[i] = (ratio[i] + ratio[i+period]) / 2;
        }

        double[] multiplier = new double[regression.length()];
        for (int i = 0; i < multiplier.length; i++) {
            multiplier[i] = m[i % period];
        }

        double[] y = new double[multiplier.length];
        for (int i = 0; i < y.length; i++) {
            y[i] = regression.get(i) * multiplier[i];
        }

        return new Data(y);
    }

    @Override
    public String toString() {
        return "LLS{" +
                "m=" + m +
                ", b=" + b +
                '}';
    }
}
