package org.rickhuizing.petclinicguru.services.jpa;

import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.model.Pet;
import org.rickhuizing.petclinicguru.services.OwnerService;
import org.rickhuizing.petclinicguru.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class PetServiceJPA extends AbstractJPAService<Pet> implements PetService {

    private final OwnerService ownerService;

    protected PetServiceJPA(CrudRepository<Pet, Long> repository, OwnerService ownerService) {
        super(repository);
        this.ownerService = ownerService;
    }


    public void delete(Pet pet) {
        Owner petOwner = pet.getOwner();
        petOwner.removePet(pet);
        ownerService.save(petOwner);
    }

    public void deleteById(Long id) {
        repository.findById(id).ifPresent(this::delete);
    }

}
