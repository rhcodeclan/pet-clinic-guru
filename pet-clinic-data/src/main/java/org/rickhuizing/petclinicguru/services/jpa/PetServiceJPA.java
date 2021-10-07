package org.rickhuizing.petclinicguru.services.jpa;

import org.rickhuizing.petclinicguru.model.Pet;
import org.rickhuizing.petclinicguru.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class PetServiceJPA extends AbstractJPAService<Pet> implements PetService {

    protected PetServiceJPA(CrudRepository<Pet, Long> repository) {
        super(repository);
    }
}
