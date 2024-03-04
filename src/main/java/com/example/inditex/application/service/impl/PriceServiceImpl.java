package com.example.inditex.application.service.impl;


import com.example.inditex.application.repository.PriceRepository;
import com.example.inditex.application.service.PriceServce;
import com.example.inditex.domain.enums.ErrorDefinitionEnum;
import com.example.inditex.domain.error.exceptions.PriceException;
import com.example.inditex.domain.model.dto.Price;
import com.example.inditex.infrastructure.dbo.PriceEntity;
import com.example.inditex.infrastructure.mapper.PriceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


@Slf4j
@Service
public class PriceServiceImpl implements PriceServce {

    private final PriceRepository repository;

    private final PriceMapper mapper;

    @Autowired
    public PriceServiceImpl(PriceRepository repository, PriceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    /**
     * @Params: String startData, String productId, String brandId
     * Search prices using input parameters.
     */
    @Override
    @Cacheable("price")
    public List<Price> listPrices(String startData, String productId, String brandId) throws PriceException {
        LocalDateTime dateToSearch = getFormatToDate(startData);

        List<PriceEntity> listOfPrices = repository.findAll();

        List<Price> response = listOfPrices.stream()
                .filter(data -> (data.getStartDate().equals(dateToSearch) || data.getEndDate().equals(dateToSearch))
                        && data.getBrand_id().equals(Long.valueOf(brandId))
                        && data.getProductId().equals(Long.valueOf(productId)))
                .map(mapper::toDto)
                .toList();

        if (response.isEmpty()) {
            throw new PriceException(ErrorDefinitionEnum.GENERIC_ERROR,
                    "No products found with the filters sent",
                    HttpStatus.NOT_FOUND.value());
        }

        return response;

    }

    /**
     * @Params string startData
     * receives a String, validates and transforms to localDate
     */
    private LocalDateTime getFormatToDate(String startData) throws PriceException {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateToSearch = LocalDateTime.parse(startData, formatter);

            return dateToSearch;
        } catch (DateTimeParseException e) {
            throw new PriceException(ErrorDefinitionEnum.GENERIC_ERROR,
                    "The received date could not be transformed into date",
                    HttpStatus.BAD_REQUEST.value());
        }


    }


}
