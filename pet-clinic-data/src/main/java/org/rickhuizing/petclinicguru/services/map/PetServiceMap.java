package org.rickhuizing.petclinicguru.services.map;

import org.rickhuizing.petclinicguru.model.Pet;
import org.rickhuizing.petclinicguru.services.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceMap extends AbstractMapService<Pet> implements PetService {
}
