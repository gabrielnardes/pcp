package com.gabrielnardes.pcpapi.masterProductionSchedule.entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Item {
    private String name;
    private double initialStock;
    private double leadTime;
    private double safetyStock;
    private double lotSize;
    private ArrayList<Child> children;

    public Item(String name, double initialStock, double leadTime, double safetyStock, double lotSize, Child... children) {
        this.name = name;
        this.initialStock = initialStock;
        this.leadTime = leadTime;
        this.safetyStock = safetyStock;
        this.lotSize = lotSize;

        this.children = new ArrayList<>();

        this.children.addAll(Arrays.asList(children));
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
            if (children.get(i).getItem().getName().equals(item.getName())) {
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
                if (children.get(i).getItem().getName().equals(item.getName())) {
                    children.remove(i);
                    found = true;
                }
                i++;
            }
        }
    }

    public void print() {
        System.out.print("com.gabrielnardes.pcp.entity.masterProductionSchedule.Item{" +
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
        for (Child child : children) {
            child.print();
        }
    }

    public void transverse() {
        for (Child c : children) {
            c.getItem().transverse();
            c.print();
        }
    }

    public BillOfMaterial bom(int quantity) {
        return new BillOfMaterial(this, quantity);
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "com.gabrielnardes.pcp.entity.masterProductionSchedule.Item{" +
                "name='" + name + '\'' +
                ", initialStock=" + initialStock +
                ", leadTime=" + leadTime +
                ", safetyStock=" + safetyStock +
                ", lotSize=" + lotSize +
                ", children=" + children +
                '}';
    }

    public double getLeadTime() {
        return leadTime;
    }

    public double getSafetyStock() {
        return safetyStock;
    }

    public double getLotSize() {
        return lotSize;
    }

    public double getInitialStock() {
        return initialStock;
    }
}
