package fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Controller;

import fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Contrat.OperationRepository;
import fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Entity.Compte;
import fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Entity.Operation;
import fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Exception.CompteNotFoundException;
import fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Exception.InsufficientFundsException;
import fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Exception.OperationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class OperationController {

    @Autowired
    private Environment environment;

    @Autowired
    private OperationRepository repository;

    @Autowired
    private OperationResourceAssembler assembler;

    @GetMapping("/operation/all")
    public Resources<Resource<Operation>> getAllOperations()
    {

        List<Resource<Operation>> operations = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(operations,
                linkTo(methodOn(OperationController.class).getAllOperations()).withSelfRel());
    }


    @GetMapping("/operation/{id}")
    public Resource<Operation> getOperationById(@PathVariable Long id) {

        Operation operation = repository.findById(id)
                .orElseThrow(() -> new OperationNotFoundException(id));

        return assembler.toResource(operation);
    }


    @PostMapping("/operation/add")
    public ResponseEntity<Void> newCompte(@RequestBody Operation operation) {

        RestTemplate restTemplate = new RestTemplate();

        // récuperation du compte source
        ResponseEntity<Compte> response = restTemplate.exchange(
                "http://192.168.99.100:8011/compte/byIban/"+operation.getIbanSource(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Compte>(){});
        Compte compteSource = response.getBody();

        // recuperation du compte destination
        ResponseEntity<Compte> response2 = restTemplate.exchange(
                "http://192.168.99.100:8011/compte/byIban/"+operation.getIbandestination(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Compte>(){});
        Compte compteDestination = response2.getBody();

        // verification que les comptes existent
        if (compteSource == null || compteDestination == null)
            throw new CompteNotFoundException(compteSource.getIban());


        //verification du solde
        if(compteSource.getSolde() < operation.getMontant())
            throw new InsufficientFundsException(operation.getIbanSource());

        // a mettre dans un transaction
        // sauvegarde de l'operation
        Operation ope = repository.save(operation);

        //upgrade du montant du
        compteSource.setSolde(compteSource.getSolde() - operation.getMontant());
        compteDestination.setSolde(compteDestination.getSolde() + operation.getMontant());
        restTemplate.put("http://192.168.99.100:8011/compte/update/"+compteSource.getId(), compteSource);
        restTemplate.put("http://192.168.99.100:8011/compte/update/"+compteDestination.getId(), compteDestination);

        if(ope == null){
            return ResponseEntity.noContent().build();
        }


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(ope.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping("/operation/update/{id}")
    public Operation updateOperation(@RequestBody Operation newOperation, @PathVariable Long id) {

        return repository.findById(id)
                .map(operation -> {
                    operation.setIbanSource(newOperation.getIbanSource());
                    operation.setIbandestination(newOperation.getIbandestination());
                    operation.setDate(newOperation.getDate());
                    operation.setDevise(newOperation.getDevise());
                    operation.setMontant(newOperation.getMontant());
                    operation.setType(newOperation.getType());

                    return repository.save(operation);
                })
                .orElseGet(() -> {
                    newOperation.setId(id);
                    return repository.save(newOperation);
                });
    }



    @DeleteMapping("/operation/delete/{id}")
    public void deleteOperation(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
