package com.experis.course.springlamiapizzeriacrud.service;

import com.experis.course.springlamiapizzeriacrud.exception.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getPizzaListDue() {
        return pizzaRepository.findAll();
    }

    //metodo per la lista di tutte le pizze
    public Page<Pizza> getPizzaList(Optional<String> search, Optional<BigDecimal> searchPrezzo, Pageable pageable) {
//        List<Pizza> pizzaList;
        if (search.isPresent() && searchPrezzo.isPresent()) {
            return pizzaRepository.findByNomeContainingIgnoreCaseAndPrezzoLessThanEqual(search.get(), searchPrezzo.get(), pageable);
        } else if (search.isPresent()) {
            // Applica solo il filtro per il nome
            return pizzaRepository.findByNomeContainingIgnoreCase(search.get(), pageable);
        } else if (searchPrezzo.isPresent()) {
            return pizzaRepository.findByPrezzoLessThanEqual(searchPrezzo.get(), pageable);
        } else {
            return pizzaRepository.findAll(pageable);
        }
    }

    // Metodo per ottenere una pizza per ID
    // metodo che restituisce una pizza presa per id, se non lo trova solleva un'eccezione
    public Pizza getPizzaById(Integer id) throws PizzaNotFoundException {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            // se non ho trovato la pizza sollevo un'eccezione
            throw new PizzaNotFoundException("Pizza with id " + id + " not found");
        }
    }

    // metodo per creare un una nuova pizza
    public Pizza savePizza(Pizza pizza) {
        pizza.setId(null);
        try {
            return pizzaRepository.save(pizza);
        } catch (RuntimeException e) {
            // Gestisci l'eccezione o rilanciala, a seconda delle tue esigenze
            throw new RuntimeException("Errore durante il salvataggio della pizza: " + e.getMessage(), e);
        }
    }

//    public Pizza savePizza(PizzaDto pizzaDto) throws IOException, PizzaNotFoundException {
//        // converto il PizzaDto in Pizza
//        Pizza pizza = convertDtoToPizza(pizzaDto);
//        // chiamo il metodo che salva sul database
//        return savePizza(pizza);
//    }

//    public Pizza convertDtoToPizza(PizzaDto pizzaDto) throws IOException {
//        Pizza pizza = new Pizza();
//        pizza.setId(pizzaDto.getId());
//        pizza.setNome(pizzaDto.getNome());
//        pizza.setDescrizione(pizzaDto.getDescrizione());
//        pizza.setUrlImage(pizzaDto.getUrlImage());
//        pizza.setPrezzo(pizzaDto.getPrezzo());
//        pizza.setIngredients(pizzaDto.getIngredients());
//        if (pizzaDto.getCoverFile() != null && !pizzaDto.getCoverFile().isEmpty()) {
//            byte[] bytes = pizzaDto.getCoverFile().getBytes();
//            pizza.setCover(bytes);
//        }
//        return pizza;
//
//    }

//    private static PizzaDto convertPizzaToDto(Pizza pizza) throws IOException {
//        PizzaDto pizzaDto = new PizzaDto();
//        pizzaDto.setNome(pizza.getNome());
//        pizzaDto.setDescrizione(pizza.getDescrizione());
//        pizzaDto.setUrlImage();
//
//    }


    //    public Pizza updatePizza(Pizza pizza, Integer id) {
//        return pizzaRepository.findById(id).map(existingPizza -> {
//            existingPizza.setNome(pizza.getNome());
//            existingPizza.setDescrizione(pizza.getDescrizione());
//            existingPizza.setUrlImage(pizza.getUrlImage());
//            existingPizza.setPrezzo(pizza.getPrezzo());
//            return pizzaRepository.save(existingPizza);
//        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found"));
//    }

    // metodo per modificare una pizza con un id
    public Pizza updatePizza(Pizza pizza) {
        Pizza pizzaToEdit = getPizzaById(pizza.getId());
        pizzaToEdit.setNome(pizza.getNome());
        pizzaToEdit.setDescrizione(pizza.getDescrizione());
        pizzaToEdit.setUrlImage(pizza.getUrlImage());
        pizzaToEdit.setPrezzo(pizza.getPrezzo());
        pizzaToEdit.setIngredients(pizza.getIngredients());
        return pizzaRepository.save(pizzaToEdit);
    }

    public void deletePizza(Integer id) {
        pizzaRepository.deleteById(id);
    }

    // metodo che prende in ingresso un Pageable e restituisce la Page di libri
    public Page<Pizza> getPage(Pageable pageable) {
        return pizzaRepository.findAll(pageable);
    }
}
