package org.rickhuizing.petclinicguru.services.map;

import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.services.OwnerService;
import org.rickhuizing.petclinicguru.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default, map"})
public class OwnerServiceMap extends AbstractMapService<Owner> implements OwnerService {

    private final PetService petService;

    public OwnerServiceMap(PetService petService) {
        this.petService = petService;
    }

    @Override
    public Owner save(Owner object) {
        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getId() == null) {
                        petService.save(pet);
                    }
                });
            }
        }
        return super.save(object);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.map.values().stream()
                .filter(item -> item.getLastName() != null && item.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }
}
