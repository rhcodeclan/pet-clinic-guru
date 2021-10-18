package org.rickhuizing.petclinicguru.services;

import org.rickhuizing.petclinicguru.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findByLastNameContaining(String partialLastName);
}
