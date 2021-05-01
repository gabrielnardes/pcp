//import static java.lang.Math.abs;

public class LLS {
    private final Data x;
    private final Data y;
    private final double m;
    private final double b;
    private final double n;

    public LLS(Data x, Data y) {
        this.x = x;
        this.y = y;

        if (x.length() != y.length()) {
            System.out.println("X and Y data set must be equal length");
        }
        n = x.length();

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

        m = (n*xySum - xSum*ySum) / (n*xxSum - xSum*xSum);
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
}
