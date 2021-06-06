package com.gabrielnardes.pcpapi.forecasting.entity;

import com.vladmihalcea.hibernate.type.array.DoubleArrayType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
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
}
