package com.experis.course.springlamiapizzeriacrud.service;

import com.experis.course.springlamiapizzeriacrud.exception.IngredientNameUniqueException;
import com.experis.course.springlamiapizzeriacrud.model.Ingredient;
import com.experis.course.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findByOrderByNome();
    }

    public Ingredient save(Ingredient ingredient) throws IngredientNameUniqueException {
        // verifico che questo nome non esista già
        if (ingredientRepository.existsByNome(ingredient.getNome())) {
            throw new IngredientNameUniqueException(ingredient.getNome());
        }
        // trasformo il nome in lowercase
        ingredient.setNome(ingredient.getNome().toLowerCase());
        // salvo su database
        return ingredientRepository.save(ingredient);
    }
}



