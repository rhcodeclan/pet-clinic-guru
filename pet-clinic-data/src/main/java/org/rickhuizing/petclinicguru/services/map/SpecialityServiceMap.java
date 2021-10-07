package org.rickhuizing.petclinicguru.services.map;

import org.rickhuizing.petclinicguru.model.Speciality;
import org.rickhuizing.petclinicguru.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default, map"})
public class SpecialityServiceMap extends AbstractMapService<Speciality> implements SpecialityService {
}
