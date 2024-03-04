package com.example.inditex.infrastructure.mapper.impl;


import com.example.inditex.domain.model.dto.Price;
import com.example.inditex.infrastructure.dbo.CurrencyEntity;
import com.example.inditex.infrastructure.dbo.PriceEntity;
import com.example.inditex.infrastructure.mapper.PriceMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PriceMapperImp extends GenericMapperImpl implements PriceMapper {
    @Autowired
    public PriceMapperImp(MapperAttributeFilter mapperAttributeFilter) {
        super(mapperAttributeFilter);
    }

    @Override
    public Price toDto(PriceEntity entity) {
        Long currencyId = entity.getCurrency().getId();
        entity.setCurrency(null);

        Price response = modelMapper.map(entity, Price.class);
        response.setCurrency(currencyId.toString());
        return response;
    }

    @Override
    public Price toDto(PriceEntity entity, Set<String> attributesToMap) {
        ModelMapper mp = modelMapper(attributesToMap);
        return mp.map(entity, Price.class);
    }

    @Override
    public PriceEntity toEntity(Price resouce) {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setName(resouce.getCurrency());

        PriceEntity response = modelMapper.map(resouce, PriceEntity.class);
        response.setCurrency(currencyEntity);
        return response;
    }

    @Override
    protected void addTypeMaps(ModelMapper modelMapper) {

    }
}
