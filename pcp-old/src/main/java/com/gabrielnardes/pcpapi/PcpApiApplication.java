package com.gabrielnardes.pcpapi;

import com.gabrielnardes.pcpapi.forecasting.entity.SimpleMovingAverage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PcpApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcpApiApplication.class, args);
		System.out.println("Server on");

//      Simple moving average
//        double[] data = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
//        System.out.println(data);
//        SimpleMovingAverage sma = new SimpleMovingAverage(data, 2);
//        sma.calc();
//		System.out.println(sma);

//      Weighted moving average
/*
		double[] data = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
        WeightedMovingAverage wma = new WeightedMovingAverage(data, 3,0.5, 0.3, 0.2);
        wma.calc();
        System.out.println(wma);
*/

//      Simple exponential smoothing
/*
		double[] data = {1, 432, 6, 12313, 534, 54};
		SimpleExponentialSmoothing ses = new SimpleExponentialSmoothing(data, 1, 0.012);
        ses.calc();
        System.out.println(ses);

        double[] data1 = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
        SimpleExponentialSmoothing ses1 = new SimpleExponentialSmoothing(data1, 2, 0.8);
        ses1.calc();
        System.out.println(ses1);

        double[] data2 = {24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23};
        SimpleExponentialSmoothing ses2 = new SimpleExponentialSmoothing(data2, 4, 0.4);
        ses2.calc();
        System.out.println(ses2);
*/

//      Regression - Linear Least Squares
/*
        double[] x = {1, 2, 3, 4, 5, 6, 7};
        double[] y = {1.5, 3.8, 6.7, 9,11.2, 13.6, 16};
        LinearLeastSquares lls = new LinearLeastSquares(x, y);
        System.out.println(lls);

        double[] x1 = {1, 2, 3, 4, 5, 6, 7, 8};
        double[] y1 = {256, 312, 426, 278, 298, 387, 517, 349};
        LinearLeastSquares lls1 = new LinearLeastSquares(x1, y1);
        System.out.println(lls1);

        double[] x2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        double[] y2 = lls1.calc(x2);
        double[] y3 = lls1.seasoned(y1, y2, 4);
        System.out.println(Arrays.toString(y2));
        System.out.println(Arrays.toString(y3));

        double[] x4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                              11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                              21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        double[] y4 = {49, 22, 44, 87, 98, 30, 87, 89, 27, 80,
                21, 72, 93, 74, 30, 77, 94, 82, 3, 63, 29, 4, 13, 9,
                30, 64, 42, 24, 1, 89};
        LinearLeastSquares lls4 = new LinearLeastSquares(x4, y4);
        double[] r4 = lls4.calc(x4);
        double[] s4 = lls4.seasoned(y4, r4, 12);
        System.out.println(Arrays.toString(r4));
        System.out.println(Arrays.toString(s4));
*/

//      Errors
/*
        double[] data1 = {12, 15, 13, 16, 14, 12};
        double[] forecast1 = {14, 13, 12, 13, 15, 14};

        Error e1 = new Error(data1, forecast1);
        System.out.println(e1);

        double[] data2 = {8, 11, 12, 14};
        double[] forecast2 = {10, 10, 10, 10};

        Error e2 = new Error(data2, forecast2);
        System.out.println(e2);
*/

//      MPS - Master Production Schedule
//        Item b = new Item("B", 50, 2, 20, 80);
//        Item d = new Item("D", 100, 2 , 100, 400);
//
//        Item c = new Item("C", 100, 1, 50, 600, new Child(b, 5));
//
//        Item a = new Item("A", 50,  1, 0, 200, new Child(c, 4), new Child(d, 3));
//        Item y = new Item("Y", 120, 1, 0, 140, new Child(a, 2), new Child(b, 1));
//
//        Mps mps = new Mps(y, 0, 0, 0, 120, 0, 50, 80, 90, 0, 180, 80, 90);
//        mps.print();

//      BOM - Bill of Materials
/*
        Item b = new Item("B", 50, 2, 20, 80);
        Item d = new Item("D", 100, 2 , 100, 400);

        Item c = new Item("C", 100, 1, 50, 600, new Child(b, 5));

        Item a = new Item("A", 50,  1, 0, 200, new Child(c, 4), new Child(d, 3));
        Item y = new Item("Y", 120, 1, 0, 140, new Child(a, 2), new Child(b, 1));

        BillOfMaterial bom = y.bom(2);
        bom.print();
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
