package com.gabrielnardes.pcpapi.masterProductionSchedule.entity;

import javax.persistence.*;

@Entity
//@Data
//@NoArgsConstructor
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne()
    @JoinColumn(name = "item_owner_id")
    private Item owner;

    @Column(nullable = false)
    private int quantity;

    public Child(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public void print() {
//        item.print();
        System.out.println("Quantity " + quantity);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getOwner() {
        return owner;
    }

    public void setOwner(Item owner) {
        this.owner = owner;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
