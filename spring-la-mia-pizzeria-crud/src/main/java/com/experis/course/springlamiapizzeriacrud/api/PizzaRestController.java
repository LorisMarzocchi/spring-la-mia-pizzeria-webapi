package com.experis.course.springlamiapizzeriacrud.api;

import com.experis.course.springlamiapizzeriacrud.exception.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pizzas")
@CrossOrigin
public class PizzaRestController {
    // endpoint per la lista delle pizze

    @Autowired
    private PizzaService pizzaService;

    // index per lista paginata (Pageable in PizzaService e PizzaRepository )
    @GetMapping
    public Page<Pizza> index(@RequestParam Optional<String> search,
                             @RequestParam Optional<BigDecimal> searchPrezzo,
                             @RequestParam(name = "size", defaultValue = "2") Integer size,
                             @RequestParam(name = "page", defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return pizzaService.getPizzaList(search, searchPrezzo, pageable);

    }

    @GetMapping("/{id}")
    public Pizza details(@PathVariable Integer id) {
        try {
            return pizzaService.getPizzaById(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        try {
            return pizzaService.savePizza(pizza);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza) {
        pizza.setId(id);
        try {
            return pizzaService.updatePizza(pizza);
        } catch (PizzaNotFoundException e) {
            throw new PizzaNotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            Pizza pizzaToDelete = pizzaService.getPizzaById(id);
            pizzaService.deletePizza(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    // endpoint di test per lista paginata
    @GetMapping("/page")
    public Page<Pizza> pagedIndex(
            @RequestParam(name = "size", defaultValue = "2") Integer size,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {

        return pizzaService.getPage(PageRequest.of(page, size));
    }
//
//    @GetMapping("/page/v2")
//    public Page<Book> pagedIndexV2(@PageableDefault(page = 0, size = 2) Pageable pageable) {
//        return bookService.getPage(pageable);
//    }

}
