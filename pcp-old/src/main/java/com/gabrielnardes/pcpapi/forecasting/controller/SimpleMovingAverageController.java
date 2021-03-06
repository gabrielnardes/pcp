package com.gabrielnardes.pcpapi.forecasting.controller;

import com.gabrielnardes.pcpapi.forecasting.entity.SimpleMovingAverage;
import com.gabrielnardes.pcpapi.forecasting.repository.SimpleMovingAverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/forecasting/sma")
public class SimpleMovingAverageController {
    @Autowired
    private SimpleMovingAverageRepository simpleMovingAverageRepository;

    @GetMapping()
    public @ResponseBody Iterable<SimpleMovingAverage> findAll() {
        return simpleMovingAverageRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Optional<SimpleMovingAverage> findById(@PathVariable Long id) {
        return simpleMovingAverageRepository.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody SimpleMovingAverage create(@RequestBody SimpleMovingAverage sma) {
        sma.calc();
        return simpleMovingAverageRepository.save(sma);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        simpleMovingAverageRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public @ResponseBody SimpleMovingAverage update(@PathVariable Long id, @RequestBody SimpleMovingAverage sma) {
        SimpleMovingAverage newSma = new SimpleMovingAverage(id, sma.getPeriod(), sma.getData());
        newSma.calc();

        return simpleMovingAverageRepository.save(newSma);
    }

    @PatchMapping("/{id}")
    public @ResponseBody SimpleMovingAverage updatePeriod(@PathVariable Long id, @RequestBody int period) {
        Optional<SimpleMovingAverage> optionalSma = simpleMovingAverageRepository.findById(id);

        SimpleMovingAverage updatedSma = optionalSma.get();
        updatedSma.setPeriod(period);
        updatedSma.calc();

        return simpleMovingAverageRepository.save(updatedSma);
    }
}
