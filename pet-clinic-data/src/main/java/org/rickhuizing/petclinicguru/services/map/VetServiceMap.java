package org.rickhuizing.petclinicguru.services.map;

import org.rickhuizing.petclinicguru.model.Vet;
import org.rickhuizing.petclinicguru.services.SpecialityService;
import org.rickhuizing.petclinicguru.services.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet> implements VetService {

    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Vet save(Vet object) {
        if (object != null) {
            object.getSpecialities().forEach(spec -> {
                if (spec.getId() == null) {
                    specialityService.save(spec);
                }
            });
        }
        return super.save(object);
    }
}
