package fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Controller;


import fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Exception.OperationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OperationNotFoundAdvice {

        @ResponseBody
        @ExceptionHandler(OperationNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String OperationNotFoundHandler(OperationNotFoundException ex) {
            return ex.getMessage();
        }
}
