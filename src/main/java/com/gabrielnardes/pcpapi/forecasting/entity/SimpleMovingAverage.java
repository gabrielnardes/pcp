package com.gabrielnardes.pcpapi.forecasting.entity;

import com.vladmihalcea.hibernate.type.array.DoubleArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Entity
@TypeDefs({
        @TypeDef(
                name = "double-array",
                typeClass = DoubleArrayType.class
        )
})
public class SimpleMovingAverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Column(nullable = false)
    @Type( type = "double-array" )
    @Column(
            columnDefinition = "double precision[]"
    )
    private double[] data;

    @Type( type = "double-array" )
    @Column(
            columnDefinition = "double precision[]"
    )
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
        sma = new double[this.data.length - period + 1];

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

    public SimpleMovingAverage(long id, int period, double[] data) {
        this.id = id;
        this.period = period;
        this.data = data;
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

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public void setSma(double[] sma) {
        this.sma = sma;
    }
}
