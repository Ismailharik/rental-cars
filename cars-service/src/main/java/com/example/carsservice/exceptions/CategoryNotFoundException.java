package com.example.carsservice.exceptions;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(Long idCategory){
        super("Category with ID : "+idCategory+" is not found");
    }
}
