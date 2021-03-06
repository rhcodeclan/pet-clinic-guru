package org.rickhuizing.petclinicguru.services.jpa;

import org.rickhuizing.petclinicguru.model.Visit;
import org.rickhuizing.petclinicguru.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class VisitServiceJPA extends AbstractJPAService<Visit> implements VisitService {
    protected VisitServiceJPA(CrudRepository<Visit, Long> repository) {
        super(repository);
    }
}
