public class MRP {
    private double[] grossRequirement;
    private double[] scheduledReceipts;
    private double[] projectedAvailable;
    private double[] plannedOrderRelease;

    public MRP(double[] grossRequirement, double currentStock, double leadTime, double lotSize) {
        int length = grossRequirement.length;
        this.grossRequirement = grossRequirement;
        projectedAvailable = new double[length];
        scheduledReceipts = new double[length];
        plannedOrderRelease = new double[length];

        projectedAvailable[0] = currentStock;

        for (int i = 1; i < grossRequirement.length; i++) {
            if (grossRequirement[i] == 0) {
                projectedAvailable[i] = projectedAvailable[i-1];
            } else if (projectedAvailable[i - 1] - grossRequirement[i] < 0){
                double lot = Math.ceil((grossRequirement[i] -projectedAvailable[i - 1]) / lotSize);
                scheduledReceipts[i] = lot * lotSize;
                plannedOrderRelease[i - (int)leadTime] = scheduledReceipts[i];
                projectedAvailable[i] = projectedAvailable[i - 1] + scheduledReceipts[i] - grossRequirement[i];
            } else {
                projectedAvailable[i] = projectedAvailable[i - 1] - grossRequirement[i];
            }
        }
    }

    public void print() {
        for (double el : scheduledReceipts) {
//            System.out.printf("%.0f\n", el);
        }

        for (double el : projectedAvailable) {
//            System.out.printf("%.0f\n", el);
        }

        for (double el : plannedOrderRelease) {
//            System.out.printf("%.0f\n", el);
        }
    }
}
