package com.experis.course.springlamiapizzeriacrud.service;

import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    //metodo per la lista di tutte le pizze
    public List<Pizza> getPizzaList(Optional<String> search, Optional<BigDecimal> searchPrezzo) {
        List<Pizza> pizzaList;
        if (search.isPresent() && searchPrezzo.isPresent()) {
            return pizzaRepository.findByNomeContainingIgnoreCaseAndPrezzoLessThanEqual(search.get(), searchPrezzo.get());
        } else if (search.isPresent()) {
            // Applica solo il filtro per il nome
            return pizzaRepository.findByNomeContainingIgnoreCase(search.get());
        } else if (searchPrezzo.isPresent()) {
            return pizzaRepository.findByPrezzoLessThanEqual(searchPrezzo.get());
        } else {
            return pizzaRepository.findAll();
        }
    }
}
