package com.experis.course.springlamiapizzeriacrud.repository;

import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    List<Pizza> findByNomeContainingIgnoreCase(String nomeKeyword);

    List<Pizza> findByPrezzoLessThanEqual(BigDecimal nomeKeyword);
    
    List<Pizza> findByNomeContainingIgnoreCaseAndPrezzoLessThanEqual(String nomeKeyword, BigDecimal maxPrezzo);

}
