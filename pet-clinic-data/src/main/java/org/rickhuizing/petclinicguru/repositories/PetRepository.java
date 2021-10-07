package org.rickhuizing.petclinicguru.repositories;

import org.rickhuizing.petclinicguru.model.Pet;
import org.springframework.data.repository.CrudRepository;


public interface PetRepository extends CrudRepository<Pet, Long> {
}
