package masterProductionSchedule;

import java.util.HashMap;
import java.util.Map;

public class Mps {
    private final Map<String, MpsProduct> mps = new HashMap<>();

    public Mps(Item item, double ... grossRequirement) {
        createMap(item);
        calculateMpsProduct(item, grossRequirement);
    }

    public void calculateMpsProduct(Item item, double[] grossRequirement) {
        MpsProduct mpsProduct;

        if (mps.get(item.getName()) == null) {
            mpsProduct = new MpsProduct(item, grossRequirement);
            mps.put(item.getName(), mpsProduct);
        } else {
            MpsProduct OldMps = mps.get(item.getName());
            mpsProduct = new MpsProduct(item, grossRequirement);
            mps.put(item.getName(), sum(OldMps, mpsProduct));
        }

        for (Child c : item.getChildren()) {
            calculateMpsProduct(c.getItem(), scaleArray(mpsProduct.getPlannedOrderRelease(), c.getQuantity()));
        }
    }

    public MpsProduct sum(MpsProduct OldMps, MpsProduct newMps) {
        MpsProduct sumMps = new MpsProduct(OldMps.getItem(), sumTwoArrays(OldMps.getGrossRequirement(), newMps.getGrossRequirement()));

        sumMps.setScheduledReceipts(sumTwoArrays(OldMps.getScheduledReceipts(), newMps.getScheduledReceipts()));
        sumMps.setProjectedAvailable(sumTwoArrays(OldMps.getProjectedAvailable(), newMps.getProjectedAvailable()));
        sumMps.setPlannedOrderRelease(sumTwoArrays(OldMps.getPlannedOrderRelease(), newMps.getPlannedOrderRelease()));

        return sumMps;
    }

    public double[] scaleArray(double[] array, int scaleFactor) {
        double[] scaledArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            scaledArray[i] = array[i] * scaleFactor;
        }

        return scaledArray;
    }

    public double[] sumTwoArrays(double[] arrayA, double[] arrayB) {
        double[] sumArray = new double[arrayA.length];

        for (int i = 0; i < sumArray.length; i++) {
            sumArray[i] = arrayA[i] + arrayB[i];
        }

        return sumArray;
    }

    public void print() {
        System.out.print("Printing map\n");

        for( Map.Entry<String, MpsProduct> entry : mps.entrySet() ){
            entry.getValue().print();
        }
    }

    public void createMap(Item item) {
        mps.put(item.getName(), null);

        for (Child c : item.getChildren()) {
            createMap(c.getItem());
        }
    }
}
