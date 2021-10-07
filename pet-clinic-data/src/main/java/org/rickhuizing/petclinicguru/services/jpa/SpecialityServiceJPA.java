package org.rickhuizing.petclinicguru.services.jpa;

import org.rickhuizing.petclinicguru.model.Speciality;
import org.rickhuizing.petclinicguru.services.SpecialityService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecialityServiceJPA extends AbstractJPAService<Speciality> implements SpecialityService {
    protected SpecialityServiceJPA(CrudRepository<Speciality, Long> repository) {
        super(repository);
    }
}
