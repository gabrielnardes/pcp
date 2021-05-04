import java.util.ArrayList;

public class Item {
    private String name;
    private double currentStock;
    private double leadTime;
    private double safetyStock;
    private double lotSize;
    private ArrayList<Child> children;
    private double[] demand;

    public Item(String name, double currentStock, double leadTime, double safetyStock, double lotSize, Child... children) {
//        System.out.println("Creating " + name);

        this.name = name;
        this.currentStock = currentStock;
        this.leadTime = leadTime;
        this.safetyStock = safetyStock;
        this.lotSize = lotSize;

        this.children = new ArrayList<Child>();
        for (Child c : children) {
//            c.print();
            this.children.add(c);
        }
//        System.out.println("Finish " + name);
    }

    public void addChild(Child child) {
        // TODO Add check if child already exists
        this.children.add(child);
    }

    public void setChild(Child child) {
        // TODO Add set child
    }

    public void deleteChild(Child child) {
        // TODO Add delete child
    }

    public void demand(double ... demand) {
        this.demand = demand;
        for (double d : this.demand) {
//            System.out.printf("%.0f ", d);
        }
    }

    public MRP plan() {
        return new MRP(demand, currentStock, leadTime, lotSize);
    }

    public void print() {
        System.out.printf("Item{" +
                "name='" + name + '\'' +
                ", currentStock=" + currentStock +
                ", leadTime=" + leadTime +
                ", safetyStock=" + safetyStock +
                ", lotSize=" + lotSize +
                '}');
    }

    public void printChildren() {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).print();
        }
    }
}
