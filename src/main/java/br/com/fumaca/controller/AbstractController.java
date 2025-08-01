package br.com.fumaca.controller;

import br.com.fumaca.enterprise.CustomValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AbstractController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        List<String> collect = ex.getBindingResult()
                .getAllErrors().stream()
                .map(p -> ((FieldError) p).getField() + " "
                        + p.getDefaultMessage())
                .collect(Collectors.toList());
        errors.put("erro", collect.toString());
        return errors;
    }


    @ExceptionHandler(CustomValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, String> handleCustomValidation(CustomValidationException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("erro", ex.getMessage());
    return error;
    }
}
