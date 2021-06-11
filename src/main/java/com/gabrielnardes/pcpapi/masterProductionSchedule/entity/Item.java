package com.gabrielnardes.pcpapi.masterProductionSchedule.entity;

import com.vladmihalcea.hibernate.type.array.DoubleArrayType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@TypeDefs({
        @TypeDef(
                name = "double-array",
                typeClass = DoubleArrayType.class
        )
})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double initialStock;

    @Column(nullable = false)
    private double leadTime;

    @Column(nullable = false)
    private double safetyStock;

    @Column(nullable = false)
    private double lotSize;

    @OneToMany(mappedBy = "item", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Child> children = new ArrayList<>();

    public Item(String name, double initialStock, double leadTime, double safetyStock, double lotSize, Child... children) {
        this.name = name;
        this.initialStock = initialStock;
        this.leadTime = leadTime;
        this.safetyStock = safetyStock;
        this.lotSize = lotSize;

        for (Child c : children) {
            c.setOwner(this);
        }

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
}
