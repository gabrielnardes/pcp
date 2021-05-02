public class Main {
    public static void main(String[] args) {
//        SMA - Simple moving averages
/*
        Data d = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        SMA sma = new SMA(d, 2);
        sma.calc();
        sma.print();
*/

//        WMA - Weighted moving averages
/*
        Data d = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        WMA wma = new WMA(d, 3,0.5, 0.3, 0.2);
        wma.calc();
        wma.print();
*/

//        SES - Simple exponential smoothing
/*
        Data d = new Data(1, 432, 6, 12313, 534, 54);
        SES ses = new SES(d, 1, 0.012);
        ses.calc();
        ses.print();

        Data d1 = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        SES ses1 = new SES(d1, 2, 0.8);
        ses1.calc();
        ses1.print();

        Data d2 = new Data(24, 26, 22, 25, 19, 31, 26, 18, 29, 24, 30, 23);
        SES ses2 = new SES(d2, 4, 0.4);
        ses2.calc();
        ses2.print();
*/

//        LLS - Regression - Linear Least Squares
/*
        Data x = new Data(1, 2, 3, 4, 5, 6, 7);
        Data y = new Data(1.5, 3.8, 6.7, 9,11.2, 13.6, 16);
        LLS lls = new LLS(x, y);
        lls.print();

        Data x1 = new Data(1, 2, 3, 4, 5, 6, 7, 8);
        Data y1 = new Data(256, 312, 426, 278, 298, 387, 517, 349);
        LLS lls1 = new LLS(x1, y1);
        lls1.print();

        Data x2 = new Data(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Data y2 = lls1.calc(x2);
        Data y3 = lls1.season(y1, y2, 4);
        y2.print();
        y3.print();

        Data x4 = new Data(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                              11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                              21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        Data y4 = new Data(49, 22, 44, 87, 98, 30, 87, 89, 27, 80,
                21, 72, 93, 74, 30, 77, 94, 82, 3, 63, 29, 4, 13, 9,
                30, 64, 42, 24, 1, 89);
        LLS lls4 = new LLS(x4, y4);
        Data r4 = lls4.calc(x4);
        Data s4 = lls4.season(y4, r4, 12);
        r4.print();
        s4.print();
*/
    }
}
