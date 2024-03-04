package com.example.inditex.application.service;



import com.example.inditex.domain.error.exceptions.PriceException;
import com.example.inditex.domain.model.dto.Price;

import java.util.List;

public interface PriceServce {

    List<Price> listPrices(String startData, String productId, String brandId) throws PriceException;
}
