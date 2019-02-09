package fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Contrat;

import fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Long> {

    List<Operation> findAll();


}
