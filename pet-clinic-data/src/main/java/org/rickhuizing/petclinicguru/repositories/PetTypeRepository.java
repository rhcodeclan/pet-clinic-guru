package org.rickhuizing.petclinicguru.repositories;

import org.rickhuizing.petclinicguru.model.PetType;
import org.springframework.data.repository.CrudRepository;


public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
