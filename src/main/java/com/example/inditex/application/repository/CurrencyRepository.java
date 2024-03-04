package com.example.inditex.application.repository;


import com.example.inditex.infrastructure.dbo.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long>, JpaSpecificationExecutor<CurrencyEntity> {
}
