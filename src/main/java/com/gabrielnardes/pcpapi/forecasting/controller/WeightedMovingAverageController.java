package com.gabrielnardes.pcpapi.forecasting.controller;

import com.gabrielnardes.pcpapi.forecasting.entity.WeightedMovingAverage;
import com.gabrielnardes.pcpapi.forecasting.repository.WeightedMovingAverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/forecasting/wma")
public class WeightedMovingAverageController {
    @Autowired
    private WeightedMovingAverageRepository weightedMovingAverageRepository;

    @GetMapping()
    public @ResponseBody Iterable<WeightedMovingAverage> findAll() {
        return weightedMovingAverageRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Optional<WeightedMovingAverage> findById(@PathVariable Long id) {
        return weightedMovingAverageRepository.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody WeightedMovingAverage create(@RequestBody WeightedMovingAverage wma) {
        wma.calc();
        return weightedMovingAverageRepository.save(wma);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        weightedMovingAverageRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public @ResponseBody WeightedMovingAverage update(@PathVariable Long id, @RequestBody WeightedMovingAverage wma) {
        WeightedMovingAverage newWma = new WeightedMovingAverage(id, wma.getData(), wma.getPeriod(), wma.getWeight());
        newWma.calc();

        return weightedMovingAverageRepository.save(newWma);
    }
}
