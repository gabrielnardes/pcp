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
    public void mfe() {
        double errorSum = 0;

        for (int i = 0; i < n; i++) {
            errorSum += x.get(i) - y.get(i);
        }

        System.out.printf("\nMean Forecast Error: %.2f", errorSum / n);
    }

    // Mean Absolute Deviation
    public void mad() {
        double errorSum = 0;

        for (int i = 0; i < n; i++) {
            errorSum += Math.abs(x.get(i) - y.get(i));
        }

        System.out.printf("\nMean Absolute Deviation: %.2f", errorSum / n);
    }
}
