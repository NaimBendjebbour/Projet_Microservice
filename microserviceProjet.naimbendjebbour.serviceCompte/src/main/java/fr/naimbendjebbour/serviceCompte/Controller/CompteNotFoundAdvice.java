package fr.naimbendjebbour.serviceCompte.Controller;

import fr.naimbendjebbour.serviceCompte.Exception.CompteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CompteNotFoundAdvice {

        @ResponseBody
        @ExceptionHandler(CompteNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String CompteNotFoundHandler(CompteNotFoundException ex) {
            return ex.getMessage();
        }
}
