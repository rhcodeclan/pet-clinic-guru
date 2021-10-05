package org.rickhuizing.petclinicguru.services;

import org.rickhuizing.petclinicguru.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
