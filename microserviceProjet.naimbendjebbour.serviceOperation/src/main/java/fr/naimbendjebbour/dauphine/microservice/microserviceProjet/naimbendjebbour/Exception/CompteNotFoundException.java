package fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Exception;

public class CompteNotFoundException extends RuntimeException {

    public CompteNotFoundException(String iban) {
        super("  the account does not exist or has been deleted IBAN :" + iban);
    }

}
