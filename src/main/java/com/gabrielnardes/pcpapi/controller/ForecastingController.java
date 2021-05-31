package com.gabrielnardes.pcpapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/forecasting")
public class ForecastingController {

    @GetMapping
    public String listAll() {
        return "Hello pcp";
    }
}
