package fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Exception;

public class OperationNotFoundException extends RuntimeException {

    public OperationNotFoundException(Long id) {
        super("Could not find operation " + id);
    }
}
