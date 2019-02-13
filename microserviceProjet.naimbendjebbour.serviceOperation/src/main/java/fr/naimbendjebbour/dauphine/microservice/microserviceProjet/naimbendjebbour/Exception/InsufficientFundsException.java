package fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Exception;

public class InsufficientFundsException extends RuntimeException{

    public InsufficientFundsException(String iban) {
        super(" Insufficient Funds " + iban);
    }

}
