package org.rickhuizing.petclinicguru.services.map;

import org.rickhuizing.petclinicguru.model.Pet;
import org.rickhuizing.petclinicguru.services.PetService;
import org.rickhuizing.petclinicguru.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default, map"})
public class PetServiceMap extends AbstractMapService<Pet> implements PetService {

    private final PetTypeService petTypeService;

    public PetServiceMap(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public Pet save(Pet object) {
        if (object != null) {
            if (object.getPetType() != null) {
                if (object.getPetType().getId() == null) {
                    petTypeService.save(object.getPetType());
                }
            } else {
                throw new RuntimeException("Pet Type cant be null");
            }
        }
        return super.save(object);
    }
}
