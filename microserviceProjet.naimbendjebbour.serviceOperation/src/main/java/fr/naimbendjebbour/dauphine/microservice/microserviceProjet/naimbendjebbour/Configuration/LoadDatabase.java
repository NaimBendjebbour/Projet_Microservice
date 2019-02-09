package fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Configuration;

import fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Contrat.OperationRepository;
import fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Entity.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Calendar;

@Configuration
@Slf4j
public class  LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(OperationRepository repository)
    {

        return args -> {

            log.info("Preloading Operation A" + repository.save(new Operation("virement","FR4940BBBBBBBBB","FR4940CCCCCCCCC","EUR", 1700.7f, Calendar.getInstance().getTime())));
            log.info("Preloading Operation B" + repository.save(new Operation("virement","FR4940FFFFFFFFF","FR4940CCCCCCCCC","EUR", 2500.7f, Calendar.getInstance().getTime())));
            log.info("Preloading Operation C" + repository.save(new Operation("virement","FR4940CCCCCCCCC","FR4940EEEEEEEEE","EUR", 4500.7f, Calendar.getInstance().getTime())));
            log.info("Preloading Operation D" + repository.save(new Operation("virement","FR4940DDDDDDDDD","FR4940EEEEEEEEE","EUR", 10500f, Calendar.getInstance().getTime())));

        };
    }
}
