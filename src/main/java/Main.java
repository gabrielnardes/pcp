public class Main {
    public static void main(String[] args) {
        Data d = new Data(2, 3, 4, 5, 6, 7, 54, 234345, 67, 44, 23);
        SMA sma = new SMA(d, 2);
        WMA wma = new WMA(d, 6,0.3, 0.3, 0.2, 0.1, 0.05, 0.05);

        sma.calc();
        sma.print();

        wma.calc();
        wma.print();
    }
}
