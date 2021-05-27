import data.Data;
import forecasting.Error;

public class Main {
    public static void main(String[] args) {
//      Simple moving averages
/*
        Data d = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        System.out.println(d.toString());
        SimpleMovingAverage sma = new SimpleMovingAverage(d, 2);
        sma.calc();
        sma.print();
*/

//      Weighted moving averages
/*
        Data d = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        WeightedMovingAverage wma = new WeightedMovingAverage(d, 3,0.5, 0.3, 0.2);
        wma.calc();
        wma.print();
*/

//      Simple exponential smoothing
/*
        Data d = new Data(1, 432, 6, 12313, 534, 54);
        SimpleExponentialSmoothing ses = new SimpleExponentialSmoothing(d, 1, 0.012);
        ses.calc();
        ses.print();

        Data d1 = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        SimpleExponentialSmoothing ses1 = new SimpleExponentialSmoothing(d1, 2, 0.8);
        ses1.calc();
        ses1.print();

        Data d2 = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        SimpleExponentialSmoothing ses2 = new SimpleExponentialSmoothing(d2, 4, 0.4);
        ses2.calc();
        ses2.print();
*/

//      Regression - Linear Least Squares
/*
        Data x = new Data(1, 2, 3, 4, 5, 6, 7);
        Data y = new Data(1.5, 3.8, 6.7, 9,11.2, 13.6, 16);
        LinearLeastSquares lls = new LinearLeastSquares(x, y);
        lls.print();

        Data x1 = new Data(1, 2, 3, 4, 5, 6, 7, 8);
        Data y1 = new Data(256, 312, 426, 278, 298, 387, 517, 349);
        LinearLeastSquares lls1 = new LinearLeastSquares(x1, y1);
        lls1.print();

        Data x2 = new Data(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Data y2 = lls1.calc(x2);
        Data y3 = lls1.seasoned(y1, y2, 4);
        y2.print();
        y3.print();

        Data x4 = new Data(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                              11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                              21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        Data y4 = new Data(49, 22, 44, 87, 98, 30, 87, 89, 27, 80,
                21, 72, 93, 74, 30, 77, 94, 82, 3, 63, 29, 4, 13, 9,
                30, 64, 42, 24, 1, 89);
        LinearLeastSquares lls4 = new LinearLeastSquares(x4, y4);
        Data r4 = lls4.calc(x4);
        Data s4 = lls4.seasoned(y4, r4, 12);
        r4.print();
        s4.print();
*/

//      Errors
/*
        Data d1 = new Data(12, 15, 13, 16, 14, 12);
        Data f1 = new Data(14, 13, 12, 13, 15, 14);

        Error e1 = new Error(d1, f1);
        e1.meanForecastError();
        e1.meanAbsoluteDeviation();

        Data d2 = new Data(8, 11, 12, 14);
        Data f2 = new Data(10, 10, 10, 10);

        Error e2 = new Error(d2, f2);
        e2.trackingSignal();
*/

//      MPS - Master Production Schedule
/*
        Item b = new Item("B", 50, 2, 20, 80);
        Item d = new Item("D", 100, 2 , 100, 400);

        Item c = new Item("C", 100, 1, 50, 600, new Child(b, 5));

        Item a = new Item("A", 50,  1, 0, 200, new Child(c, 4), new Child(d, 3));
        Item y = new Item("Y", 120, 1, 0, 140, new Child(a, 2), new Child(b, 1));

        Mps mps = new Mps(y, 0, 0, 0, 120, 0, 50, 80, 90, 0, 180, 80, 90);
        mps.print();
*/

//      BOM - Bill of Materials
/*
        Item b = new Item("B", 50, 2, 20, 80);
        Item d = new Item("D", 100, 2 , 100, 400);

        Item c = new Item("C", 100, 1, 50, 600, new Child(b, 5));

        Item a = new Item("A", 50,  1, 0, 200, new Child(c, 4), new Child(d, 3));
        Item y = new Item("Y", 120, 1, 0, 140, new Child(a, 2), new Child(b, 1));

        BillOfMaterial billOfMaterial = y.bom(2);
        billOfMaterial.print();
*/
//      Item manipulation methods
/*
        Item k = new Item("K", 10, 3, 50, 120);
        Item b = new Item("B", 50, 2, 20, 80);
        Item d = new Item("D", 100, 2 , 100, 400);

        Item c = new Item("C", 100, 1, 50, 600, new Child(b, 5));

        Item a = new Item("A", 50,  1, 0, 200, new Child(c, 4), new Child(d, 3));
        Item y = new Item("Y", 120, 1, 0, 140, new Child(a, 2), new Child(b, 1));

        System.out.print("Transverse and print item\n");
        y.transverse();

        System.out.print("\nAdd children after the creation of the item");
        System.out.print("\nBefore\n");
        y.printChildren();
        y.addChild(new Child(k, 8));
        System.out.print("\nAfter\n");
        y.printChildren();

        System.out.print("\nGet children\n");
        y.getChild(a).print();
        y.getChild(k).print();

        System.out.print("\nSet/update children");
        System.out.print("\nBefore\n");
        y.getChild(k).print();
        y.setChild(k, 9999);
        System.out.print("\nAfter\n");
        y.getChild(k).print();

        // Delete children
        System.out.print("\nDelete children");
        System.out.print("\nBefore\n");
        y.printChildren();
        y.deleteChild(k);
        System.out.print("\nAfter\n");
        y.printChildren();
*/
    }
}
