package com.gabrielnardes.pcpapi.entity.masterProductionSchedule;

public class Child {
    private Item item;
    private int quantity;

    public Child(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public void print() {
        item.print();
        System.out.println("Quantity " + quantity);
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "com.gabrielnardes.pcp.entity.masterProductionSchedule.Child{" +
                "item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
