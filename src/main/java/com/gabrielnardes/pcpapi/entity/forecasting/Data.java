package com.gabrielnardes.pcpapi.entity.forecasting;

import java.util.Arrays;

public class Data {
    private final double[] data;

    public Data(double ... d) {
        data = d;
    }

    public int length() {
        return data.length;
    }

    public void print() {
        System.out.println("\nDATA");

        for (int i = 0; i < data.length; i++) {
            System.out.printf("d[%d]: %.3f\n", i+1, data[i]);
        }
    }

    public double get (int i) {
        return data[i];
    }

    public double[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
