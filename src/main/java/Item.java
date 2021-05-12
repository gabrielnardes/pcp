import java.util.ArrayList;
import java.util.Arrays;

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

    public void setChild(Item item, int quantity) {
        getChild(item).setQuantity(quantity);
    }

    public Child getChild(Item item) {
        int i = 0;
        boolean found = false;
        Child foundChild = null;

        // TODO Change to a limited search range
        while (!found) {
            if (children.get(i).getItem().getName() == item.getName()) {
                foundChild = children.get(i);
                found = true;
            }
            i++;
        }
        return foundChild;
    }

    public void deleteChild(Item item) {
        if (getChild(item) != null) {
            int i = 0;
            boolean found = false;

            // TODO Change to a limited search range
            while (!found) {
                if (children.get(i).getItem().getName() == item.getName()) {
                    children.remove(i);
                    found = true;
                }
                i++;
            }
        }
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
                ", initialStock=" + initialStock +
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

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", initialStock=" + initialStock +
                ", leadTime=" + leadTime +
                ", safetyStock=" + safetyStock +
                ", lotSize=" + lotSize +
                ", demand=" + Arrays.toString(demand) +
                ", children=" + children +
                '}';
    }
}
