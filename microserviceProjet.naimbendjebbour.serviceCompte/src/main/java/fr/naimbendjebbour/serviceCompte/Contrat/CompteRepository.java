package fr.naimbendjebbour.serviceCompte.Contrat;

import fr.naimbendjebbour.serviceCompte.Entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte,Long> {

    List<Compte> findAll();


}
