package org.rickhuizing.petclinicguru.services.map;

import org.rickhuizing.petclinicguru.model.VetSpeciality;
import org.rickhuizing.petclinicguru.services.SpecialityService;
import org.springframework.stereotype.Service;

@Service
public class SpecialityServiceMap extends AbstractMapService<VetSpeciality> implements SpecialityService {
}
