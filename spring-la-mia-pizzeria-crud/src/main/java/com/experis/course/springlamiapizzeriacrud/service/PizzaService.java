package com.experis.course.springlamiapizzeriacrud.service;

import com.experis.course.springlamiapizzeriacrud.exception.PizzaNotFoundException;
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

    // Metodo per ottenere una pizza per ID
    public Pizza getPizzaById(Integer id) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            // se non ho trovato la pizza sollevo un'eccezione
            throw new PizzaNotFoundException("Pizza with id " + id + " not found");
        }
    }


    public Pizza savePizza(Pizza pizza) {
        try {
            return pizzaRepository.save(pizza);
        } catch (RuntimeException e) {
            // Gestisci l'eccezione o rilanciala, a seconda delle tue esigenze
            throw new RuntimeException("Errore durante il salvataggio della pizza: " + e.getMessage(), e);
        }
    }

    //    public Pizza updatePizza(Pizza pizza, Integer id) {
//        return pizzaRepository.findById(id).map(existingPizza -> {
//            existingPizza.setNome(pizza.getNome());
//            existingPizza.setDescrizione(pizza.getDescrizione());
//            existingPizza.setUrlImage(pizza.getUrlImage());
//            existingPizza.setPrezzo(pizza.getPrezzo());
//            return pizzaRepository.save(existingPizza);
//        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found"));
//    }
    public Pizza updatePizza(Pizza pizza) {
        Pizza pizzaToEdit = getPizzaById(pizza.getId());
        pizzaToEdit.setNome(pizza.getNome());
        pizzaToEdit.setDescrizione(pizza.getDescrizione());
        pizzaToEdit.setUrlImage(pizza.getUrlImage());
        pizzaToEdit.setPrezzo(pizza.getPrezzo());
        return pizzaRepository.save(pizzaToEdit);
    }

    public void deletePizza(Integer id) {
        pizzaRepository.deleteById(id);
    }
}
