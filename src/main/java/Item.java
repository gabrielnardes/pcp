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

    public Child getChild(Item item) {
        int i = 0;
        boolean found = false;
        Child foundChild = null;

        while (!found) {
            if (children.get(i).getItem().getName() == item.getName()) {
                foundChild = children.get(i);
                found = true;
            }
        }
        return foundChild;
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
        return new MRP(demand, initialStock, leadTime, lotSize, children, name, quantity, safetyStock);
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

    public String getName() {
        return name;
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

    public BOM bom(int quantity) {
        return new BOM(this, quantity);
    }

    public ArrayList<Child> getChildren() {
        return children;
    }
}