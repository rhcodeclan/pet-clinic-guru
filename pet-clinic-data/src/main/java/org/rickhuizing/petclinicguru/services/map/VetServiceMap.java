package org.rickhuizing.petclinicguru.services.map;

import org.rickhuizing.petclinicguru.model.Vet;
import org.rickhuizing.petclinicguru.services.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet> implements VetService {
}
