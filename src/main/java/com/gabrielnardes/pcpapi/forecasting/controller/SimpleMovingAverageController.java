package com.gabrielnardes.pcpapi.forecasting.controller;

import com.gabrielnardes.pcpapi.forecasting.entity.SimpleMovingAverage;
import com.gabrielnardes.pcpapi.forecasting.repository.SimpleMovingAverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/forecasting/sma")
public class SimpleMovingAverageController {
    @Autowired
    private SimpleMovingAverageRepository simpleMovingAverageRepository;

    @GetMapping()
    public @ResponseBody Iterable<SimpleMovingAverage> getAll() {
        // This returns a JSON or XML with the users
        return simpleMovingAverageRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Optional<SimpleMovingAverage> findById(@PathVariable Long id) {
        return simpleMovingAverageRepository.findById(id);
    }

    @PostMapping()
    public @ResponseBody SimpleMovingAverage add(@RequestBody SimpleMovingAverage sma) {
        return simpleMovingAverageRepository.save(sma);
    }
}
