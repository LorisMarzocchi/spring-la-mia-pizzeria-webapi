package com.experis.course.springlamiapizzeriacrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String home() {
        return "redirect:/pizzas";
    }
}
//@Controller
//@RequestMapping("/")
//public class indexController {
//
//    @Autowired
//    private PizzaRepository pizzaRepository;
//
//    @GetMapping
//    public String index(Model model) {
//        List<Pizza> pizzaList = pizzaRepository.findAll();
//        model.addAttribute("pizzaList", pizzaList);
//        return "pizzas/pizzaList";
//    }


