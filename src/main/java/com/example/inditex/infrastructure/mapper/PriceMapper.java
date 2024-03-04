package com.example.inditex.infrastructure.mapper;

import com.example.inditex.domain.model.dto.Price;
import com.example.inditex.infrastructure.dbo.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    Price toDto(PriceEntity entity);
    Price toDto(PriceEntity entity, Set<String> attributesToMap);
    PriceEntity toEntity(Price resouce );


}
