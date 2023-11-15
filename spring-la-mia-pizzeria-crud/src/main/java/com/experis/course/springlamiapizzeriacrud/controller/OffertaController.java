package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.model.OffertaSpeciale;
import com.experis.course.springlamiapizzeriacrud.service.OffertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/offerte")
public class OffertaController {
    @Autowired
    OffertaService offertaService;

    @GetMapping("/create")
    public String create(@RequestParam Integer pizzaId, Model model) {
        try {
            OffertaSpeciale offertaSpeciale = offertaService.createNewOfferta(pizzaId);
            model.addAttribute("offertaSpeciale", offertaSpeciale);
            return "offerte/offertaForm";
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute("offertaSpeciale") OffertaSpeciale formOffertaSpeciale,
                           BindingResult bindingResult) {
        // Valida i dati
        if (bindingResult.hasErrors()) {
            return "offerte/offertaForm";
        }
        // Salva su database
        OffertaSpeciale savedOffertaSpeciale = offertaService.saveOfferta(formOffertaSpeciale);
        // Redirect alla visualizzazione dell'offerta speciale
        return "redirect:/offerte/show/" + savedOffertaSpeciale.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            OffertaSpeciale offertaSpeciale = offertaService.getOfferta(id);
            model.addAttribute("offertaSpeciale", offertaSpeciale);
            return "offerte/offertaForm";
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id,
                         @ModelAttribute("offertaSpeciale") OffertaSpeciale formOffertaSpeciale,
                         BindingResult bindingResult) {
        // Valida i dati
        if (bindingResult.hasErrors()) {
            return "offerte/offertaForm";
        }
        // Salva su database
        OffertaSpeciale savedOffertaSpeciale = offertaService.saveOfferta(formOffertaSpeciale);
        // Redirect alla visualizzazione dell'offerta speciale
        return "redirect:/offerte/show/" + savedOffertaSpeciale.getId();
    }


}
