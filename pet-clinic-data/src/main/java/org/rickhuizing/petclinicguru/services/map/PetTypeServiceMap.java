package org.rickhuizing.petclinicguru.services.map;

import org.rickhuizing.petclinicguru.model.PetType;
import org.rickhuizing.petclinicguru.services.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType> implements PetTypeService {
}
