package fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Contrat;

import fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation,Long> {


    List<Optional<Operation>> findAllByIbandestination(String ibanDestination);
    List<Optional<Operation>> findAllByIbanSource(String ibanDestination);

}
