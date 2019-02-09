package fr.naimbendjebbour.serviceCompte.Configuration;

import fr.naimbendjebbour.serviceCompte.Contrat.CompteRepository;
import fr.naimbendjebbour.serviceCompte.Entity.Compte;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(CompteRepository repository)
    {

        return args -> {

            log.info("Preloading Compte A" + repository.save(new Compte("FR4940AAAAAAAAA","Courant",0.2f, "Gratuit")));
            log.info("Preloading Compte B" + repository.save(new Compte("FR4940BBBBBBBBB","Courant",0.2f, "Gratuit")));
            log.info("Preloading Compte C" + repository.save(new Compte("FR4940CCCCCCCCC","Courant",0.2f, "Gratuit")));
            log.info("Preloading Compte D" + repository.save(new Compte("FR4940DDDDDDDDD","Courant",0.2f, "Gratuit")));
            log.info("Preloading Compte E" + repository.save(new Compte("FR4940EEEEEEEEE","Courant",0.2f, "Gratuit")));
            log.info("Preloading Compte F" + repository.save(new Compte("FR4940FFFFFFFFF","Courant",0.2f, "Gratuit")));
        };
    }
}
