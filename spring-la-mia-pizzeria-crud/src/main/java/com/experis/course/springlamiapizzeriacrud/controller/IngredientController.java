package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.exception.IngredientNameUniqueException;
import com.experis.course.springlamiapizzeriacrud.model.Ingredient;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.IngredientRepository;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import com.experis.course.springlamiapizzeriacrud.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model) {
        // passa al model ingredientList con la lista di categorie
        model.addAttribute("ingredientList", ingredientService.getAll());
        // passa al model un ingrediente vuoto come attributo categoryObj del form
        model.addAttribute("ingredientObj", new Ingredient());
        return "ingredients/ingredientForm";
    }

    @PostMapping
    public String doSave(@Valid @ModelAttribute("ingredientObj") Ingredient formIngredient,
                         BindingResult bindingResult,
                         Model model) {
        // valido la categoria
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientList", ingredientService.getAll());
            return "ingredients/ingredientForm";
        }
        try {
            // salvo la nuova categoria su database
            ingredientService.save(formIngredient);
            return "redirect:/ingredients";
        } catch (IngredientNameUniqueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A ingredients with name " + e.getMessage() + " already exists");
        }
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Ingredient ingredientToDelete = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        // Rimuovere l'ingrediente da ogni pizza associata
        for (Pizza pizza : ingredientToDelete.getPizzas()) {
            pizza.getIngredients().remove(ingredientToDelete);
            // Salva la pizza aggiornata
            pizzaRepository.save(pizza);
        }

        // Ora è sicuro eliminare l'ingrediente
        ingredientRepository.deleteById(id);
        return "redirect:/ingredients";
    }


}

//@Controller
//@RequestMapping("/ingredients")
//public class IngredientController {
//    @Autowired
//    IngredientRepository ingredientRepository;
//
//    // metodo che mostra la pagina degli ingredienti
//    @GetMapping
//    public String index(Model model) {
//        model.addAttribute("ingredientList", ingredientRepository.findAll());
//        // passo al model un ingrediente vuoto come attributo ingredientObj del form
//        model.addAttribute("ingredientObj", new Ingredient());
//        return "ingredients/ingredientForm";
//    }
//
//    // metodo che salva gli ingredienti
//    @PostMapping
//    public String save(@Valid @ModelAttribute("ingredientObj") Ingredient ingredient, BindingResult bindingResult,
//                       Model model) {
//        // testo che la categoria è valida
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("ingredientList", ingredientRepository.findAll());
//            return "ingredients/ingredientForm";
//        }
//        ingredientRepository.save(ingredient);
//        return "redirect:/ingredients";
//    }
//
//    // Metodo che gestisce la richiesta di eliminazione

//    @PostMapping("/delete")
//    public String delete(@RequestParam("id") Integer id) {
//        ingredientRepository.deleteById(id);
//        return "redirect:/ingredients";
//    }

//        @PostMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Integer id) {
//        ingredientRepository.deleteById(id);
//        return "redirect:/ingredients";
//    }
//
//}