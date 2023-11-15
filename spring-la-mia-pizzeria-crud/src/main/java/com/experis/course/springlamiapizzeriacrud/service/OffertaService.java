package com.experis.course.springlamiapizzeriacrud.service;

import com.experis.course.springlamiapizzeriacrud.model.OffertaSpeciale;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.OffertaSpecialeRepository;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class OffertaService {
    @Autowired
    private OffertaSpecialeRepository offertaSpecialeRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    public OffertaSpeciale createNewOfferta(Integer pizzaId) {
        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + pizzaId + " not found"));
        OffertaSpeciale offertaSpeciale = new OffertaSpeciale();

        offertaSpeciale.setDataInizio(LocalDate.now());
        offertaSpeciale.setDataFine(LocalDate.now().plusMonths(1));
        offertaSpeciale.setPizza(pizza);
        return offertaSpeciale;
    }

    public OffertaSpeciale saveOfferta(OffertaSpeciale offertaSpeciale) {
        return offertaSpecialeRepository.save(offertaSpeciale);
    }

    public OffertaSpeciale getOfferta(Integer id) throws ResponseStatusException {
        return offertaSpecialeRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found"));

    }
}
