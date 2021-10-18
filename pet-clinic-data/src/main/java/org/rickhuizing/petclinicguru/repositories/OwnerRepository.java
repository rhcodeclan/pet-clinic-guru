package org.rickhuizing.petclinicguru.repositories;

import org.rickhuizing.petclinicguru.model.Owner;

import java.util.Optional;


public interface OwnerRepository extends PersonRepository<Owner> {

    Optional<Owner> findByAddress(String address);

    Optional<Owner> findByCity(String city);
}
