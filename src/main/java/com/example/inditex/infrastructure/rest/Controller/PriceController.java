package com.example.inditex.infrastructure.rest.Controller;


import com.example.inditex.application.service.PriceServce;
import com.example.inditex.domain.error.exceptions.PriceException;
import com.example.inditex.domain.model.dto.Price;
import com.example.inditex.infrastructure.rest.PriceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PriceController implements PriceApi {


    private final PriceServce servce;

    @Autowired
    public PriceController(PriceServce servce) {
        this.servce = servce;
    }

    @Override
    public ResponseEntity<List<Price>> getPrices(String brandId, String productId, String dateToApply) throws PriceException {
        return new ResponseEntity<>(servce.listPrices(dateToApply, productId, brandId), HttpStatus.OK);
    }
}
