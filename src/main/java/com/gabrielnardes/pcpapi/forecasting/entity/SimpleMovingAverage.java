package com.gabrielnardes.pcpapi.forecasting.entity;

import javax.persistence.*;

@Entity
public class SimpleMovingAverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double[] data;

    private double[] sma;

    @Column(nullable = false)
    private int period;

    public SimpleMovingAverage(double[] data, int period) {
        this.data = data;
        this.period = period;

        sma = new double[(int) this.data.length - period + 1];
    }

    public void print() {
        System.out.println("SMA with period " + period);
        for (int i = 0; i < sma.length; i++) {
            System.out.printf("P[%d]: %.2f\n", i + period, sma[i]);
        }
    }

    public void calc() {
        for (int i = 0; i <= data.length - period; i++) {
            double sum = 0;

            for (int j = i; j < i + period; j++) {
                sum += data[j];
            }

            sma[i] = sum / period;
        }
    }

    public double get(int i) {
        return sma[i];
    }

    public double[] getSma() {
        return sma;
    }

    public SimpleMovingAverage() {
    }

    public SimpleMovingAverage(int period) {
        this.period = period;
    }

    public SimpleMovingAverage(long id, int period) {
        this.id = id;
        this.period = period;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
