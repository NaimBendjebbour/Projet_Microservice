package fr.naimbendjebbour.serviceCompte.Controller;

import fr.naimbendjebbour.serviceCompte.Entity.Compte;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Component
class CompteResourceAssembler implements ResourceAssembler<Compte, Resource<Compte>> {

    @Override
    public Resource<Compte> toResource(Compte Compte) {

        return new Resource<>(Compte,
                linkTo(methodOn(CompteController.class).getCompteById(Compte.getId())).withSelfRel(),
                linkTo(methodOn(CompteController.class).getAllComptes()).withRel("Comptes"));
    }
}
