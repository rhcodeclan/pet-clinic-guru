package org.rickhuizing.petclinicguru.services.map;

import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.services.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {
        return this.map.values().stream().filter(item -> item.getLastName().equals(lastName)).findFirst().orElse(null);
    }
}
