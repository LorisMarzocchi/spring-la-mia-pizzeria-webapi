package com.experis.course.springlamiapizzeriacrud.repository;

import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    Page<Pizza> findByNomeContainingIgnoreCase(String nomeKeyword, Pageable pageable);

    Page<Pizza> findByPrezzoLessThanEqual(BigDecimal nomeKeyword, Pageable pageable);

    Page<Pizza> findByNomeContainingIgnoreCaseAndPrezzoLessThanEqual(String nomeKeyword, BigDecimal maxPrezzo, Pageable pageable);

}
