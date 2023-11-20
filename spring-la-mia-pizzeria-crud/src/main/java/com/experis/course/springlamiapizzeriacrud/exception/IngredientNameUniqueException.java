package com.experis.course.springlamiapizzeriacrud.exception;

public class IngredientNameUniqueException extends RuntimeException {
    public IngredientNameUniqueException(String message) {
        super(message);
    }
}
