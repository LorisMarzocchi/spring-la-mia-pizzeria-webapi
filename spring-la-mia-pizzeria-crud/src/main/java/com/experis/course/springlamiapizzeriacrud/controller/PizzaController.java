package com.experis.course.springlamiapizzeriacrud.controller;


import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(
            @RequestParam Optional<String> search,
            @RequestParam Optional<BigDecimal> searchPrezzo,
            Model model) {
//        List<Pizza> pizzaList = pizzaRepository.findAll();
//        model.addAttribute("pizzaList", pizzaList);
//        return "pizzas/pizzaList";
        List<Pizza> pizzaList;
        if (search.isPresent() && searchPrezzo.isPresent()) {
            pizzaList = pizzaRepository.findByNomeContainingIgnoreCaseAndPrezzoLessThanEqual(search.get(), searchPrezzo.get());
        } else if (search.isPresent()) {
            // Applica solo il filtro per il nome
            pizzaList = pizzaRepository.findByNomeContainingIgnoreCase(search.get());
        } else if (searchPrezzo.isPresent()) {
            pizzaList = pizzaRepository.findByPrezzoLessThanEqual(searchPrezzo.get());
        } else {
            pizzaList = pizzaRepository.findAll();
        }
        model.addAttribute("pizzaList", pizzaList);
        return "pizzas/pizzaList";

    }

    //    INSERT INTO books (authors, created_at, isbn, publisher, synopsis, title, `year`) VALUES('Melville', '2023-11-09 11:37:00', '5556547896541', 'Penguin', '', 'Moby Dick', 1865);
    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        // verifico se il risultato è presente
        if (result.isPresent()) {
            // passo al template l'oggetto Book
            model.addAttribute("pizza", result.get());
            return "pizzas/pizzaShow";
        } else {
            // se non ho trovato la pizza sollevo un'eccezione
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found");
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzas/pizzaCreate";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pizzas/pizzaCreate";
        }
//        SETTO IL TIMESTAMP
//        formPizza.setCreatedAt(LocalDateTime.now());
        Pizza savedPizza = null;


        try {
            savedPizza = pizzaRepository.save(formPizza);
        } catch (RuntimeException e) {
            bindingResult.addError(new FieldError("pizza", "nome", formPizza.getNome(), false, null, null, "il nome deve essere unico"));
            return "pizzas/pizzaCreate";
        }
//        String redirectUrl = "redirect:/pizzas/show" +
        return "redirect:/pizzas/show/" + savedPizza.getId();
    }


}
