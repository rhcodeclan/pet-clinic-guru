package org.rickhuizing.petclinicguru.repositories;

import org.rickhuizing.petclinicguru.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PersonRepository<T extends Person> extends CrudRepository<T, Long> {

    Optional<T> findByFirstName(String firstName);

    Optional<T> findByLastName(String lastName);
}
