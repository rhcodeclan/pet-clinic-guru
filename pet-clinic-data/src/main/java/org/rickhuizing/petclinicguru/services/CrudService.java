package org.rickhuizing.petclinicguru.services;

import org.rickhuizing.petclinicguru.model.BaseEntity;

import java.util.Set;

public interface CrudService<T extends BaseEntity, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
