package com.gabrielnardes.erp.location;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/location")
public class LocationController {

    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @PostMapping
    public Location create(@RequestBody Location location) {
        return locationRepository.save(location);
    }

    @PutMapping("/{id}")
    public Location update(@PathVariable Long id, @RequestBody Location updatedLocation) {
        locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        return locationRepository.save(updatedLocation);
    }

    @PutMapping("/{id}/status")
    public Location status(@PathVariable Long id, @RequestBody String statusJson) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        if (statusJson.contains("INACTIVE")) {
            location.setStatus(Status.INACTIVE);
        } else {
            location.setStatus(Status.ACTIVE);
        }
        return locationRepository.save(location);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationRepository.deleteById(id);
    }

}
