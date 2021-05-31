package com.gabrielnardes.pcpapi.forecasting.controller;

import com.gabrielnardes.pcpapi.forecasting.dto.SimpleMovingAverageDTO;
import com.gabrielnardes.pcpapi.forecasting.service.SimpleMovingAverageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/forecasting/sma")
public class SimpleMovingAverageController {

    @PostMapping()
    public String createSimpleMovingAverage(@RequestBody SimpleMovingAverageDTO simpleMovingAverageDTO) {
        return simpleMovingAverageService.createSimpleMovingAverage(simpleMovingAverageDTO);
    }

    @GetMapping()
    public String hello() {
        return "Hello: Simple Moving Average";
    }
}
