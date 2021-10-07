package org.rickhuizing.petclinicguru.services.jpa;

import org.rickhuizing.petclinicguru.model.PetType;
import org.rickhuizing.petclinicguru.services.PetTypeService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PetTypeServiceJPA extends AbstractJPAService<PetType> implements PetTypeService {
    protected PetTypeServiceJPA(CrudRepository<PetType, Long> repository) {
        super(repository);
    }
}
