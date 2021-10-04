package org.rickhuizing.petclinicguru.services;

import org.rickhuizing.petclinicguru.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
