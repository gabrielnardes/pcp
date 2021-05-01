public class Data {
    private double[] data;

    public Data(double ... d) {
        data = d;
    }

    public double length() {
        return data.length;
    }

    public void print() {
        System.out.println("DATA");

        for (double el : data) {
            System.out.println(el);
        }
    }

    public double get (int i) {
        return data[i];
    }
}
