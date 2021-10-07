package org.rickhuizing.petclinicguru.services.map;

import org.rickhuizing.petclinicguru.model.PetType;
import org.rickhuizing.petclinicguru.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default, map"})
public class PetTypeServiceMap extends AbstractMapService<PetType> implements PetTypeService {
}
