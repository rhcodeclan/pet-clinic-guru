package org.rickhuizing.petclinicguru.repositories;

import org.rickhuizing.petclinicguru.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Optional<Owner> findByAddress(String address);

    Optional<Owner> findByCity(String city);

    Optional<Owner> findByTelephone(String telephone);

}
