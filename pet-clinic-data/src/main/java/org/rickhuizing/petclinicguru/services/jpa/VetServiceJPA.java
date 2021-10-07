package org.rickhuizing.petclinicguru.services.jpa;

import org.rickhuizing.petclinicguru.model.Vet;
import org.rickhuizing.petclinicguru.services.VetService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class VetServiceJPA extends AbstractJPAService<Vet> implements VetService {
    protected VetServiceJPA(CrudRepository<Vet, Long> repository) {
        super(repository);
    }
}
