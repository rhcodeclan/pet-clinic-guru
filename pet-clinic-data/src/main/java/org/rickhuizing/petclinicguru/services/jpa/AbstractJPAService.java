package org.rickhuizing.petclinicguru.services.jpa;

import org.rickhuizing.petclinicguru.model.BaseEntity;
import org.rickhuizing.petclinicguru.services.CrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractJPAService<T extends BaseEntity> implements CrudService<T, Long> {

    protected final CrudRepository<T, Long> repository;

    protected AbstractJPAService(CrudRepository<T, Long> repository) {
        this.repository = repository;
    }

    public Set<T> findAll() {
        HashSet<T> set = new HashSet<>();
        repository.findAll().iterator().forEachRemaining(set::add);
        return set;
    }

    public T findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public T save(T object) {
        repository.save(object);
        return object;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void delete(T object) {
        repository.delete(object);
    }
}
