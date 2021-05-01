public class Data {
    private double[] data;

    public Data(double ... d) {
        data = d;
    }

    public double length() {
        return data.length;
    }

    public void print() {
        System.out.println("\nDATA");

        for (int i = 0; i < data.length; i++) {
            System.out.printf("d[%d]: %.1f\n", i+1, data[i]);
        }
    }

    public double get (int i) {
        return data[i];
    }
}
