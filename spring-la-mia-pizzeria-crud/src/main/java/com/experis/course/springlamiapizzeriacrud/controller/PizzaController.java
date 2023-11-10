package com.experis.course.springlamiapizzeriacrud.controller;


import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(@RequestParam Optional<String> search, Model model) {
//        List<Pizza> pizzaList = pizzaRepository.findAll();
//        model.addAttribute("pizzaList", pizzaList);
//        return "pizzas/pizzaList";
        List<Pizza> pizzaList;
        if (search.isPresent()) {
            pizzaList = pizzaRepository.findByNomeContainingIgnoreCase(search.get());
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
            // se non ho trovato il libro sollevo un'eccezione
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found");
        }
    }
}
