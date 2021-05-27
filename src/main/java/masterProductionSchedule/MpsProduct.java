package masterProductionSchedule;

import java.util.Arrays;

public class MpsProduct {
    private final double[] grossRequirement;
    private double[] scheduledReceipts;
    private double[] projectedAvailable;
    private double[] plannedOrderRelease;
    private final Item item;

    public MpsProduct(Item item, double[] grossRequirement) {
        this.item = item;
        this.grossRequirement = grossRequirement;
        int period = grossRequirement.length;
        this.projectedAvailable = new double[period];
        this.scheduledReceipts = new double[period];
        this.plannedOrderRelease = new double[period];

        projectedAvailable[0] = item.getInitialStock();

        for (int i = 1; i < period; i++) {
            if (grossRequirement[i] == 0) {
                projectedAvailable[i] = projectedAvailable[i-1];
            } else if (projectedAvailable[i - 1] - grossRequirement[i] < item.getSafetyStock()){
                double lot = Math.ceil((item.getSafetyStock() + grossRequirement[i] - projectedAvailable[i - 1]) / item.getLotSize());

                scheduledReceipts[i] = lot * item.getLotSize();
                plannedOrderRelease[i - (int)item.getLeadTime()] = scheduledReceipts[i];
                projectedAvailable[i] = projectedAvailable[i - 1] + scheduledReceipts[i] - grossRequirement[i];
            } else {
                projectedAvailable[i] = projectedAvailable[i - 1] - grossRequirement[i];
            }
        }
    }

    @Override
    public String toString() {
        return "masterProductionSchedule.MpsProduct{" +
                "grossRequirement=" + Arrays.toString(grossRequirement) +
                "\nscheduledReceipts=" + Arrays.toString(scheduledReceipts) +
                "\nprojectedAvailable=" + Arrays.toString(projectedAvailable) +
                "\nplannedOrderRelease=" + Arrays.toString(plannedOrderRelease) +
                '}';
    }

    public void print() {
        System.out.printf("masterProductionSchedule.Item: %s\n", item.getName());

        System.out.print("Gross Requirement     ");
        for (double el : grossRequirement) {
            System.out.printf("%5.0f ", el);
        }
        System.out.print("\n");

        System.out.print("Scheduled Receipts    ");
        for (double el : scheduledReceipts) {
            System.out.printf("%5.0f ", el);
        }
        System.out.print("\n");

        System.out.print("Projected Available   ");
        for (double el : projectedAvailable) {
            System.out.printf("%5.0f ", el);
        }
        System.out.print("\n");

        System.out.print("Planned Order Release ");
        for (double el : plannedOrderRelease) {
            System.out.printf("%5.0f ", el);
        }
        System.out.print("\n\n");
    }

    public double[] getPlannedOrderRelease() {
        return plannedOrderRelease;
    }

    public double[] getGrossRequirement() {
        return grossRequirement;
    }

    public double[] getScheduledReceipts() {
        return scheduledReceipts;
    }

    public void setScheduledReceipts(double[] scheduledReceipts) {
        this.scheduledReceipts = scheduledReceipts;
    }

    public double[] getProjectedAvailable() {
        return projectedAvailable;
    }

    public void setProjectedAvailable(double[] projectedAvailable) {
        this.projectedAvailable = projectedAvailable;
    }

    public void setPlannedOrderRelease(double[] plannedOrderRelease) {
        this.plannedOrderRelease = plannedOrderRelease;
    }

    public Item getItem() {
        return item;
    }
}
