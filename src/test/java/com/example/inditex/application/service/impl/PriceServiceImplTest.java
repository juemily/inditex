package com.example.inditex.application.service.impl;

import com.example.inditex.domain.error.exceptions.PriceException;
import com.example.inditex.domain.model.dto.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PriceServiceImplTest {

    @Autowired
    private PriceServiceImpl service;

    @Test
    void listPrices() throws PriceException {

        List<String> listStarDate = new ArrayList<>();
        listStarDate.add("2024-02-15 10:00:00");
        listStarDate.add("2024-02-16 21:00:00");
        listStarDate.add("2024-02-14 21:00:00");
        listStarDate.add("2024-02-14 16:00:00");
        listStarDate.add("2024-02-14 10:00:00");

        for(String date : listStarDate){
            List<Price> result = service.listPrices(date, "1", "1");//listPrices("2024-03-01 00:00:00", "1", "1");

            // Realiza las aserciones necesarias
            assertNotNull(result);
            for(Price price : result){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateToSearch = LocalDateTime.parse(date, formatter);

                assertEquals(dateToSearch,price.getEndDate());

            }
        }
    }
}