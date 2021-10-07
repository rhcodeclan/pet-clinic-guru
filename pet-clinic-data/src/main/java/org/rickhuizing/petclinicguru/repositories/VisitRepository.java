package org.rickhuizing.petclinicguru.repositories;

import org.rickhuizing.petclinicguru.model.Visit;
import org.springframework.data.repository.CrudRepository;


public interface VisitRepository extends CrudRepository<Visit, Long> {
}
