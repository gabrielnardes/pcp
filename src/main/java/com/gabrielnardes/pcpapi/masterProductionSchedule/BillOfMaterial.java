package com.gabrielnardes.pcpapi.masterProductionSchedule;

import java.util.HashMap;
import java.util.Map;

public class BillOfMaterial {
    private Map<String, Integer> bom = new HashMap<>();
    private String rootItemName;
    private int demand;

    public BillOfMaterial(Item item, int quantity) {
        this.rootItemName = item.getName();
        this.demand = quantity;

        createMap(item);
        transverse(item, quantity);
    }

    public void transverse(Item item, int quantity) {
        bom.put(item.getName(), quantity + bom.get( item.getName()));

        for (Child c : item.getChildren()) {
            transverse(c.getItem(), quantity * c.getQuantity());
        }
    }

    public void createMap(Item item) {
        bom.put(item.getName(), 0);

        for (Child c : item.getChildren()) {
            createMap(c.getItem());
        }
    }

    public void print() {
        System.out.printf("Bill of Materials for item %s\n", rootItemName);

        for( Map.Entry<String, Integer> entry : bom.entrySet() ){
            System.out.printf("%s -> %d\n", entry.getKey(), entry.getValue());
        }
    }

    @Override
    public String toString() {
        return "BOM{" +
                "bom=" + bom +
                ", rootItem='" + rootItemName + '\'' +
                ", demand=" + demand +
                '}';
    }
}
