package org.rickhuizing.petclinicguru.services;

import org.rickhuizing.petclinicguru.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet vet);

    Set<Pet> findAll();
}
