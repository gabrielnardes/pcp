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
    public @ResponseBody Iterable<SimpleMovingAverage> listAll() {
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
        return simpleMovingAverageRepository.save(sma);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        simpleMovingAverageRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public @ResponseBody SimpleMovingAverage updateById(@PathVariable Long id, @RequestBody SimpleMovingAverage sma) {
        SimpleMovingAverage newSma = new SimpleMovingAverage(id, sma.getPeriod());

        return simpleMovingAverageRepository.save(newSma);
    }
}
