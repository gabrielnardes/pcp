package com.gabrielnardes.pcpapi.forecasting.mapper;

import com.gabrielnardes.pcpapi.forecasting.dto.SimpleMovingAverageDTO;
import com.gabrielnardes.pcpapi.forecasting.entity.SimpleMovingAverage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.Mapping;

@Mapper
public interface SimpleMovingAverageMapper {

    SimpleMovingAverageMapper INSTANCE = Mappers.getMapper(SimpleMovingAverageMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    SimpleMovingAverage toModel(SimpleMovingAverageDTO SimpleMovingAverageDTO);

    SimpleMovingAverageDTO toDTO(SimpleMovingAverage simpleMovingAverage);
}

