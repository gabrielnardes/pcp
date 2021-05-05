import java.util.ArrayList;

public class MRP {
    private String name;
    private double quantity;
    private double[] grossRequirement;
    private double[] scheduledReceipts;
    private double[] projectedAvailable;
    private double[] plannedOrderRelease;
    private ArrayList<Child> children;

    public MRP(double[] grossRequirement,
               double currentStock,
               double leadTime,
               double lotSize,
               ArrayList<Child> children,
               String name,
               double quantity,
               double safetyStock) {
        this.name = name;
        this.quantity = quantity;
        int length = grossRequirement.length;
        this.grossRequirement = grossRequirement;
        projectedAvailable = new double[length];
        scheduledReceipts = new double[length];
        plannedOrderRelease = new double[length];

        projectedAvailable[0] = currentStock;

        for (int i = 1; i < length; i++) {
            if (grossRequirement[i] == 0) {
                projectedAvailable[i] = projectedAvailable[i-1];
            } else if (projectedAvailable[i - 1] - grossRequirement[i] < safetyStock){
                double lot = Math.ceil((safetyStock + grossRequirement[i] - projectedAvailable[i - 1]) / lotSize);

                scheduledReceipts[i] = lot * lotSize;
                plannedOrderRelease[i - (int)leadTime] = scheduledReceipts[i];
                projectedAvailable[i] = projectedAvailable[i - 1] + scheduledReceipts[i] - grossRequirement[i];
            } else {
                projectedAvailable[i] = projectedAvailable[i - 1] - grossRequirement[i];
            }
        }

        for (Child c: children) {
            double[] plan = new double[length];

            for (int i = 0; i < length; i++) {
                plan[i] = plannedOrderRelease[i] * (double)c.getQuantity();
            }

            c.getItem().demand(plan);
            MRP mrp = c.getItem().plan(c.getQuantity());
            mrp.print();
        }
    }

    public void print() {
        System.out.printf("MRP - Item: %s - Quantity: %.0f\n", name, quantity);

        System.out.printf("Gross Requirement     ");
        for (double el : grossRequirement) {
            System.out.printf("%5.0f ", el);
        }
        System.out.printf("\n");

        System.out.printf("Scheduled Receipts    ");
        for (double el : scheduledReceipts) {
            System.out.printf("%5.0f ", el);
        }
        System.out.printf("\n");

        System.out.printf("Projected Available   ");
        for (double el : projectedAvailable) {
            System.out.printf("%5.0f ", el);
        }
        System.out.printf("\n");

        System.out.printf("Planned Order Release ");
        for (double el : plannedOrderRelease) {
            System.out.printf("%5.0f ", el);
        }
        System.out.printf("\n\n");
    }
}
