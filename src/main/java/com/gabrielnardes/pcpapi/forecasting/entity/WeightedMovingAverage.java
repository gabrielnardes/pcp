package com.gabrielnardes.pcpapi.forecasting.entity;

import com.vladmihalcea.hibernate.type.array.DoubleArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Arrays;

@Entity
//@Data
//@NoArgsConstructor
@TypeDefs({
        @TypeDef(
                name = "double-array",
                typeClass = DoubleArrayType.class
        )
})
public class WeightedMovingAverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Type( type = "double-array" )
    @Column(nullable = false,
            columnDefinition = "double precision[]"
    )
    private double[] data;

    @Type( type = "double-array" )
    @Column(nullable = false,
            columnDefinition = "double precision[]"
    )
    private double[] wma;

    @Column(nullable = false)
    private int period;

    @Type( type = "double-array" )
    @Column(columnDefinition = "double precision[]")
    private double[] weight;

    public WeightedMovingAverage(long id, double[] data, int period, double ... weight) {
        this.id = id;
        this.data = data;
        this.period = period;
        this.weight = weight;

        double weightsSum = 0;
        for (double w : weight) {
            weightsSum += w;
        }
        if (weightsSum != 1.0) {
            System.out.println("Weight sum not equal to 1");
        }

        wma = new double[data.length - period + 1];
    }

    public WeightedMovingAverage(double[] data, int period, double ... weight) {
        this.data = data;
        this.period = period;
        this.weight = weight;

        double weightsSum = 0;
        for (double w : weight) {
            weightsSum += w;
        }
        if (weightsSum != 1.0) {
            System.out.println("Weight sum not equal to 1");
        }

        wma = new double[data.length - period + 1];
    }

    public void calc() {
        wma = new double[data.length - period + 1];

        for (int i = 0; i <= data.length - period; i++) {
            double sum = 0;
            int wId = 0;

            for (int j = i; j < i + period; j++) {
                sum += data[j] * weight[wId];
                wId++;
            }

            this.wma[i] = sum;
        }
    }

    @Override
    public String toString() {
        return "WeightedMovingAverage{" +
                "id=" + id +
                "\nperiod=" + period +
                "\ndata=" + Arrays.toString(data) +
                "\nwma=" + Arrays.toString(wma) +
                "\nweight=" + Arrays.toString(weight) +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public double[] getWma() {
        return wma;
    }

    public void setWma(double[] wma) {
        this.wma = wma;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double[] getWeight() {
        return weight;
    }

    public void setWeight(double[] weight) {
        this.weight = weight;
    }
}
