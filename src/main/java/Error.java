public class Error {
    Data x;
    Data y;
    int n;

    public Error(Data x, Data y) {
        if (x.length() != y.length()) {
            System.out.println("X and Y data set must be equal length");
        }

        this.x = x;
        this.y = y;

        this.n = x.length();
    }

    // Mean Forecast Error
    public double mfe() {
        double errorSum = 0;

        for (int i = 0; i < n; i++) {
            errorSum += x.get(i) - y.get(i);
        }

//        System.out.printf("\nMean Forecast Error: %.2f", errorSum / n);

        return errorSum / n;
    }

    // Mean Absolute Deviation
    public double mad() {
        double errorSum = 0;

        for (int i = 0; i < n; i++) {
            errorSum += Math.abs(x.get(i) - y.get(i));
        }

        System.out.printf("\nMean Absolute Deviation: %.2f", errorSum / n);

        return errorSum / n;
    }

    // Running Sum of the Forecast Errors
    public double rsfe() {
        double errorSum = 0;

        for (int i = 0; i < n; i++) {
            errorSum += x.get(i) - y.get(i);
        }

        System.out.printf("\nRunning Sum of the Forecast Errors: %.2f", errorSum);

        return errorSum;
    }

    public double trackingSignal() {
        double trackingSignal = rsfe() / mad();

        System.out.printf("\nTracking Signal: %.2f", trackingSignal);

        return trackingSignal;
    }

    @Override
    public String toString() {
        return "Error{" +
                "x=" + x +
                ", y=" + y +
                ", n=" + n +
                '}';
    }
}
