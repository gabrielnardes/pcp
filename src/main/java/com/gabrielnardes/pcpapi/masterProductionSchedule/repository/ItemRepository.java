package com.gabrielnardes.pcpapi.masterProductionSchedule.repository;

import com.gabrielnardes.pcpapi.masterProductionSchedule.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
