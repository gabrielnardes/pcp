package com.gabrielnardes.pcpapi.forecasting.service;

import com.gabrielnardes.pcpapi.forecasting.dto.SimpleMovingAverageDTO;
import com.gabrielnardes.pcpapi.forecasting.entity.SimpleMovingAverage;
import com.gabrielnardes.pcpapi.forecasting.mapper.SimpleMovingAverageMapper;
import org.springframework.stereotype.Service;

@Service
public class SimpleMovingAverageService {
    private SimpleMovingAverageRepository SimpleMovingAverageRepository;

    private final SimpleMovingAverageMapper personMapper = SimpleMovingAverageMapper.INSTANCE;

    public String createPerson(SimpleMovingAverageDTO personDTO) {
        SimpleMovingAverage personToSave = personMapper.toModel(personDTO);

        SimpleMovingAverage savedPerson = personRepository.save(personToSave);
        return "Created person with ID ";
//        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }
}
