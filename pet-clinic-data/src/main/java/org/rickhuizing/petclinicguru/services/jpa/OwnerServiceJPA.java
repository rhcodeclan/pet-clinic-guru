package org.rickhuizing.petclinicguru.services.jpa;

import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.repositories.OwnerRepository;
import org.rickhuizing.petclinicguru.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdatajpa")
public class OwnerServiceJPA extends AbstractJPAService<Owner> implements OwnerService {

    private final OwnerRepository ownerRepository;

    protected OwnerServiceJPA(OwnerRepository ownerRepository) {
        super(ownerRepository);
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName).orElse(null);
    }

    @Override
    public List<Owner> findByLastNameContaining(String partialLastName) {
        return ownerRepository.findByLastNameContainingIgnoreCase(partialLastName);
    }
}
