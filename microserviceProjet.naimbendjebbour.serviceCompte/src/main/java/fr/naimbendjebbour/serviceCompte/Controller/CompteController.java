package fr.naimbendjebbour.serviceCompte.Controller;

import fr.naimbendjebbour.serviceCompte.Contrat.CompteRepository;
import fr.naimbendjebbour.serviceCompte.Entity.Compte;
import fr.naimbendjebbour.serviceCompte.Exception.CompteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CompteController {

    @Autowired
    private Environment environment;

    @Autowired
    private CompteRepository repository;

    @Autowired
    private  CompteResourceAssembler assembler;

    @GetMapping("/compte/all")
    public Resources<Resource<Compte>> getAllComptes() {

        List<Resource<Compte>> Comptes = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(Comptes,
                linkTo(methodOn(CompteController.class).getAllComptes()).withSelfRel());
    }

    @GetMapping("/compte/byType/{type}")
    public Resources<Resource<Compte>> getAllComptesByType(String type)
    {

        List<Resource<Compte>> Comptes = repository.findAllByType(type).stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(Comptes,
                linkTo(methodOn(CompteController.class).getAllComptes()).withSelfRel());
    }

    @GetMapping("/compte/{id}")
    public Resource<Compte> getCompteById(@PathVariable Long id) {

        Compte compte = repository.findById(id)
                .orElseThrow(() -> new CompteNotFoundException(id));

        return assembler.toResource(compte);
    }

    @GetMapping("/compte/byIban/{iban}")
    public Resource<Compte> getCompteByIban(@PathVariable String iban) {

        Compte compte = repository.findByIban(iban)
                .orElseThrow(() -> new CompteNotFoundException("iban",iban));

        return assembler.toResource(compte);
    }


    @PostMapping("/compte/add")
    public ResponseEntity<?> newCompte(@RequestBody Compte compte)throws URISyntaxException {

        Resource<Compte> resource = assembler.toResource(repository.save(compte));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
       /* Compte compte1 = repository.save(compte);

        if(compte1 == null){
            return ResponseEntity.noContent().build();
        }


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(compte1.getId()).toUri();

        return ResponseEntity.created(location).build();*/
    }


    @PutMapping("/compte/update/{id}")
    public ResponseEntity<?> updateCompte(@RequestBody Compte newCompte, @PathVariable Long id) throws URISyntaxException {

        Compte updatedCompte =repository.findById(id)
                .map(compte -> {
                    compte.setIban(newCompte.getIban());
                    compte.setFrais_tenu_compte(newCompte.getFrais_tenu_compte());
                    compte.setType(newCompte.getType());
                    compte.setSolde(newCompte.getSolde());
                    compte.setInteret(newCompte.getInteret());
                    return repository.save(compte);
                })
                .orElseGet(() -> {
                    newCompte.setId(id);
                    return repository.save(newCompte);
                });

        Resource<Compte> resource =assembler.toResource(updatedCompte);
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);

    }



    @DeleteMapping("/compte/delete/{id}")
    public void deleteCompte(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
