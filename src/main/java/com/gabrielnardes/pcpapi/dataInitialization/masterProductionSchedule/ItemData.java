package com.gabrielnardes.pcpapi.dataInitialization.masterProductionSchedule;

import com.gabrielnardes.pcpapi.masterProductionSchedule.entity.Child;
import com.gabrielnardes.pcpapi.masterProductionSchedule.entity.Item;
import com.gabrielnardes.pcpapi.masterProductionSchedule.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ItemData implements ApplicationRunner {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Initializing Item datasource creation");

        Item b = new Item("B", 50, 2, 20, 80);
        itemRepository.save(b);

        Item d = new Item("D", 100, 2 , 100, 400);
        itemRepository.save(d);

        Item c = new Item("C", 100, 1, 50, 600, new Child(b, 5));
        itemRepository.save(c);

        Item a = new Item("A", 50,  1, 0, 200, new Child(d, 4), new Child(d, 3));
        itemRepository.save(a);

        System.out.println("Finishing Item datasource creation");
    }
}
