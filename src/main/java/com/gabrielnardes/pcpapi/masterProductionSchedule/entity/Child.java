package com.gabrielnardes.pcpapi.masterProductionSchedule.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
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
}
