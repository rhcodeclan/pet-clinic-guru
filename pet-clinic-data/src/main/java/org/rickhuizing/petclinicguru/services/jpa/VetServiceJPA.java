package org.rickhuizing.petclinicguru.services.jpa;

import org.rickhuizing.petclinicguru.model.Vet;
import org.rickhuizing.petclinicguru.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class VetServiceJPA extends AbstractJPAService<Vet> implements VetService {
    protected VetServiceJPA(CrudRepository<Vet, Long> repository) {
        super(repository);
    }
}
