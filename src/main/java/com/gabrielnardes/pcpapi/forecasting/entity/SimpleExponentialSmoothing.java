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
public class SimpleExponentialSmoothing {

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

    @Column(nullable = false)
    private double alpha;

    @Type( type = "double-array" )
    @Column(columnDefinition = "double precision[]")
    private double[] ses;

    public SimpleExponentialSmoothing(long id, double[] data, int period, double alpha) {
        if (alpha > 1.0 | alpha < 0.0) {
            System.out.println("Alpha must be between 0.0 and 1.0");
        }

        if (period < 1) {
            System.out.println("Period must be equal or greater than 2");
        }

        this.id = id;
        this.data = data;
        this.period = period;
        this.alpha = alpha;

        if (period == 1) {
            ses = new double[data.length];
        } else {
            ses = new double[data.length - period];
        }
    }

    public SimpleExponentialSmoothing(double[] data, int period, double alpha) {
        if (alpha > 1.0 | alpha < 0.0) {
            System.out.println("Alpha must be between 0.0 and 1.0");
        }

        if (period < 1) {
            System.out.println("Period must be equal or greater than 2");
        }

        this.data = data;
        this.period = period;
        this.alpha = alpha;

        if (period == 1) {
            ses = new double[data.length];
        } else {
            ses = new double[data.length - period];
        }
    }

    public void calc() {
        if (period == 1) {
            ses = new double[data.length];
            ses[0] = data[0];

            for (int i = 1; i < ses.length; i++) {
                ses[i] = ses[i-1] + alpha * (data[i] - ses[i-1]);
            }
        } else {
            ses = new double[data.length - period];
            double[] d1 = new double[period];

            for (int i = 0; i < period; i++) {
                d1[i] = data[i];
            }

            double[] data = d1;
            SimpleMovingAverage sma = new SimpleMovingAverage(data, period);

            sma.calc();

            ses[0] = sma.get(0) + alpha * (this.data[period] - sma.get(0));

            for (int i = 1; i < ses.length; i++) {
                ses[i] = ses[i-1] + alpha * (this.data[i+period] - ses[i-1]);
            }
        }
    }

    @Override
    public String toString() {
        return "SimpleExponentialSmoothing{" +
                "id=" + id +
                "\nperiod=" + period +
                "\nalpha=" + alpha +
                "\ndata=" + Arrays.toString(data) +
                "\nses=" + Arrays.toString(ses) +
                '}';
    }
}
