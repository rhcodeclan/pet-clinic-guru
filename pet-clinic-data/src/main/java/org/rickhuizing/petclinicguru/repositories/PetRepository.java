package org.rickhuizing.petclinicguru.repositories;

import org.rickhuizing.petclinicguru.model.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface PetRepository extends CrudRepository<Pet, Long> {

    Optional<Pet> findByName(String name);
}
