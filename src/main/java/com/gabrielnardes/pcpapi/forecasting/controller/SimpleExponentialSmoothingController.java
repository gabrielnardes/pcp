package com.gabrielnardes.pcpapi.forecasting.controller;

import com.gabrielnardes.pcpapi.forecasting.entity.SimpleExponentialSmoothing;
import com.gabrielnardes.pcpapi.forecasting.repository.SimpleExponentialSmoothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/forecasting/ses")
public class SimpleExponentialSmoothingController {
    @Autowired
    private SimpleExponentialSmoothingRepository simpleExponentialSmoothingRepository;

    @GetMapping()
    public @ResponseBody Iterable<SimpleExponentialSmoothing> findAll() {
        return simpleExponentialSmoothingRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Optional<SimpleExponentialSmoothing> findById(@PathVariable Long id) {
        return simpleExponentialSmoothingRepository.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody SimpleExponentialSmoothing create(@RequestBody SimpleExponentialSmoothing ses) {
        ses.calc();
        return simpleExponentialSmoothingRepository.save(ses);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        simpleExponentialSmoothingRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public @ResponseBody SimpleExponentialSmoothing update(@PathVariable Long id, @RequestBody SimpleExponentialSmoothing ses) {
        SimpleExponentialSmoothing newSes = new SimpleExponentialSmoothing(id, ses.getData(), ses.getPeriod(), ses.getAlpha());
        newSes.calc();

        return simpleExponentialSmoothingRepository.save(newSes);
    }
}
