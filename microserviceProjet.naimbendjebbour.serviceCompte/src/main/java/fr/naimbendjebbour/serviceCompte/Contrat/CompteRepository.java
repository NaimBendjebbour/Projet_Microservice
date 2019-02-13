package fr.naimbendjebbour.serviceCompte.Contrat;

import fr.naimbendjebbour.serviceCompte.Entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte,Long> {




    Optional<Compte> findByIban(String iban);

    List<Compte> findAllByType(String type);

}
