package fr.naimbendjebbour.serviceCompte.Exception;

public class CompteNotFoundException extends RuntimeException {

    public CompteNotFoundException(Long id) {
        super("Could not find compte " + id);
    }
}
