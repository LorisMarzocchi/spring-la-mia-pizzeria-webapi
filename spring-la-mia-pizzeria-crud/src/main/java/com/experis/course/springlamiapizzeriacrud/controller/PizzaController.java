package com.experis.course.springlamiapizzeriacrud.controller;


import com.experis.course.springlamiapizzeriacrud.dto.PizzaDto;
import com.experis.course.springlamiapizzeriacrud.exception.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.service.IngredientService;
import com.experis.course.springlamiapizzeriacrud.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private IngredientService indredientService;


    //    }
//    @GetMapping
//    public String index(@RequestParam Optional<String> search, @RequestParam Optional<BigDecimal> searchPrezzo, Model model) {
////        List<Pizza> pizzaList = pizzaService.getPizzaList(search, searchPrezzo);
//        model.addAttribute("pizzaList", pizzaService.getPizzaList(search, searchPrezzo));
//        return "pizzas/pizzaList";
//    }

    // Cambiato tipo da List a Page in JPA Repo, Service e Api
    @GetMapping
    public String index(@RequestParam Optional<String> search,
                        @RequestParam Optional<BigDecimal> searchPrezzo,
                        @RequestParam(defaultValue = "5") int size,
                        @RequestParam(defaultValue = "0") int page,
                        Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pizza> pizzaPage = pizzaService.getPizzaList(search, searchPrezzo, pageable);
        model.addAttribute("pizzaPage", pizzaPage);
        return "pizzas/pizzaList";
    }

    //    public String index(
//            @RequestParam Optional<String> search,
//            @RequestParam Optional<BigDecimal> searchPrezzo,
//            Model model) {
////        throw new RuntimeException("FORCE ERROR"); serve per forzare errori
////        List<Pizza> pizzaList = pizzaRepository.findAll();
////        model.addAttribute("pizzaList", pizzaList);
////        return "pizzas/pizzaList";
//        List<Pizza> pizzaList;
//
//        if (search.isPresent() && searchPrezzo.isPresent()) {
//            pizzaList = pizzaRepository.findByNomeContainingIgnoreCaseAndPrezzoLessThanEqual(search.get(), searchPrezzo.get());
//        } else if (search.isPresent()) {
//            // Applica solo il filtro per il nome
//            pizzaList = pizzaRepository.findByNomeContainingIgnoreCase(search.get());
//        } else if (searchPrezzo.isPresent()) {
//            pizzaList = pizzaRepository.findByPrezzoLessThanEqual(searchPrezzo.get());
//        } else {
//            pizzaList = pizzaRepository.findAll();
//        }
//        model.addAttribute("pizzaList", pizzaList);
////        model.addAttribute("searchKeyword", search.orElse(""));
//        return "pizzas/pizzaList";
//

    //    INSERT INTO books (authors, created_at, isbn, publisher, synopsis, title, `year`) VALUES('Melville', '2023-11-09 11:37:00', '5556547896541', 'Penguin', '', 'Moby Dick', 1865);


    //    public String show(@PathVariable Integer id, Model model) {
//        Optional<Pizza> result = pizzaRepository.findById(id);
//        // verifico se il risultato è presente
//        if (result.isPresent()) {
//            // passo al template l'oggetto Book
//            model.addAttribute("pizza", result.get());
//            return "/pizzas/pizzaShow";
//        } else {
//            // se non ho trovato la pizza sollevo un'eccezione
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found");
//        }
//    }
    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        try {
            Pizza pizza = pizzaService.getPizzaById(id);
            model.addAttribute("pizza", pizza);
            return "pizzas/pizzaShow";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new PizzaDto());
        model.addAttribute("ingredientList", indredientService.getAll());
        return "pizzas/pizzaForm";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientList", indredientService.getAll());

            return "pizzas/pizzaForm";
        }

        try {
            Pizza savedPizza = pizzaService.savePizza(formPizza);
            return "redirect:/pizzas/show/" + savedPizza.getId();
        } catch (RuntimeException e) {
            bindingResult.addError(new FieldError("pizza", "nome", e.getMessage(), false, null, null, "Il nome deve essere unico"));
            return "pizzas/pizzaForm";
        }
    }

//    @PostMapping("/store")
//    public String store(@Valid @ModelAttribute("pizza") PizzaDto formPizza, BindingResult bindingResult, Model model) {
//        // Valida i dati
//        if (bindingResult.hasErrors()) {
//            // se ci sono errori ricarico il form
//            model.addAttribute("ingredientList", indredientService.getAll());
//
//            return "pizzas/pizzaForm";
//        }
//        // salvo su database
//        try {
//            Pizza savedPizza = pizzaService.savePizza(formPizza);
//            return "redirect:/pizzas/show/" + savedPizza.getId();
//        } catch (RuntimeException e) {
//            bindingResult.addError(new FieldError("pizza", "nome", e.getMessage(), false, null, null, "Il nome deve essere unico"));
//            return "pizzas/pizzaForm";
//        } catch (IOException e) {
//            bindingResult.addError(new FieldError("pizza", "coverFile", e.getMessage(), false, null, null, "impossibile salvare il file"));
//            return "pizzas/pizzaForm";
//        }
//    }


    // metodo che mostra la pagina di modifica di un libro
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable Integer id, Model model) {
////       DALL'ID RECUPERO I DATI
//        Optional<Pizza> result = pizzaRepository.findById(id);
//        if (result.isPresent()) {
//
//            model.addAttribute("pizza", result.get());
//
//            return "pizzas/pizzaForm";
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found");
//        }
//    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Pizza pizza = pizzaService.getPizzaById(id);
        model.addAttribute("pizza", pizza);
        model.addAttribute("ingredientList", indredientService.getAll());
        return "pizzas/pizzaForm";
    }


    // metodo che riceve il submit del form di edit e salva il libro
//    @PostMapping("/edit/{id}")
//    public String update(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
//
//        //  VALIDO LA MAPPA DI BindingResult
//
//        if (bindingResult.hasErrors()) {
//
//            // SE CI SONO ERRORI RICARICA LA PAGINA
//
//            return "pizzas/pizzaForm";
//
//        } else {
//            // recupero la pizza che voglio modificare da db
//            Pizza pizzaToEdit = pizzaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//            //  se lo trovo modifico solo gli attributi che erano campi del form
//            pizzaToEdit.setNome(formPizza.getNome());
//            pizzaToEdit.setDescrizione(formPizza.getDescrizione());
//            pizzaToEdit.setUrlImage(formPizza.getUrlImage());
//            pizzaToEdit.setPrezzo(formPizza.getPrezzo());
//
//            Pizza savedPizza = pizzaRepository.save(pizzaToEdit);
//
//            return "redirect:/pizzas/show/" + savedPizza.getId();
//        }
//    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientList", indredientService.getAll());
            return "pizzas/pizzaForm";
        }

        try {
            Pizza updatedPizza = pizzaService.updatePizza(formPizza);
            return "redirect:/pizzas/show/" + updatedPizza.getId();
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    // metodo per eliminare un libro da database
//    @PostMapping("/delete/{id}")
//    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
//
//        // recuperiamo la pizza con quell'id
//        Pizza deletePizza = pizzaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        // se esiste lo elimino
//        pizzaRepository.deleteById(id);
//        redirectAttributes.addFlashAttribute("deleteMessage", "pizza " + deletePizza.getNome() + " eliminata definitivamente");
//
//        return "redirect:/pizzas";
//    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Pizza deletedPizza = pizzaService.getPizzaById(id);
            pizzaService.deletePizza(id);
            redirectAttributes.addFlashAttribute("deleteMessage", "Pizza " + deletedPizza.getNome() + " eliminata definitivamente");
            return "redirect:/pizzas";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


}
