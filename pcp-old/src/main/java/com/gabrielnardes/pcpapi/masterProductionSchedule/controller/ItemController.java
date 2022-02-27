package com.gabrielnardes.pcpapi.masterProductionSchedule.controller;

import com.gabrielnardes.pcpapi.masterProductionSchedule.entity.Item;
import com.gabrielnardes.pcpapi.masterProductionSchedule.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/masterproductionschedule/item")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping()
    public @ResponseBody Iterable<Item> findAll() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Item findById(@PathVariable Long id) {
        return itemRepository.findById(id).get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Item create(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public @ResponseBody Item update(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);
        return itemRepository.save(item);
    }
}
