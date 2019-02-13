package fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Controller;

import fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Entity.Operation;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Component
class OperationResourceAssembler implements ResourceAssembler<Operation, Resource<Operation>> {

    @Override
    public Resource<Operation> toResource(Operation operation) {

        return new Resource<>(operation,
                linkTo(methodOn(OperationController.class).getOperationById(operation.getId())).withSelfRel(),
                linkTo(methodOn(OperationController.class).getAllOperations()).withRel("Operations"));
    }
}
