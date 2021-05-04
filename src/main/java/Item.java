import java.util.ArrayList;

public class Item {
    private String name;
    private double initialStock;
    private double leadTime;
    private double safetyStock;
    private double lotSize;
    private ArrayList<Child> children;
    private double[] demand;

    public Item(String name, double initialStock, double leadTime, double safetyStock, double lotSize, Child... children) {
        this.name = name;
        this.initialStock = initialStock;
        this.leadTime = leadTime;
        this.safetyStock = safetyStock;
        this.lotSize = lotSize;

        this.children = new ArrayList<Child>();
        for (Child c : children) {
//            c.print();
            this.children.add(c);
        }
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

    public MRP plan(double quantity) {
        return new MRP(demand, initialStock, leadTime, lotSize, children, name, quantity);
    }

    public void print() {
        System.out.printf("Item{" +
                "name='" + name + '\'' +
                ", currentStock=" + initialStock +
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

    public void transverse() {
        for (Child c : children) {
            c.getItem().transverse();
            c.print();
        }
    }
}
