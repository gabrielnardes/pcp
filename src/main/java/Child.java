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
}
