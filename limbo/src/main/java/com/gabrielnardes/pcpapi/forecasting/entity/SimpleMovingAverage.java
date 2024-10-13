package com.gabrielnardes.pcpapi.forecasting.entity;

import com.vladmihalcea.hibernate.type.array.DoubleArrayType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Data
@NoArgsConstructor
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

    @Type( type = "double-array" )
    @Column(nullable = false,
            columnDefinition = "double precision[]"
    )
    private double[] data;

    @Column(nullable = false)
    private int period;

    @Type( type = "double-array" )
    @Column(
            columnDefinition = "double precision[]"
    )
    private double[] sma;

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

    public SimpleMovingAverage(double[] data, int period) {
        this.data = data;
        this.period = period;
        this.sma = new double[this.data.length - period + 1];
    }

    public SimpleMovingAverage(long id, int period, double[] data) {
        this.id = id;
        this.period = period;
        this.data = data;
        this.sma = new double[this.data.length - period + 1];
    }

    @Override
    public String toString() {
        return "SimpleMovingAverage{" +
                "id=" + id +
                "\nperiod=" + period +
                "\ndata=" + Arrays.toString(data) +
                "\nsma=" + Arrays.toString(sma) +
                '}';
    }
}
