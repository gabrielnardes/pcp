import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BOM {
    private HashMap<String, Integer> bom = new HashMap<String, Integer>();
    private String rootItem;
    private int demand;

    public BOM(Item item, int quantity) {
        rootItem = item.getName();
        demand = quantity;

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
        System.out.printf("Bill of Materials for item %s\n", rootItem);

        for( Map.Entry<String, Integer> entry : bom.entrySet() ){
            System.out.printf("%s -> %d\n", entry.getKey(), entry.getValue());
        }
    }
}
