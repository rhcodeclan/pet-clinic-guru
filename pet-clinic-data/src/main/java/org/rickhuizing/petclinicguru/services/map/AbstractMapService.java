package org.rickhuizing.petclinicguru.services.map;

import org.rickhuizing.petclinicguru.model.BaseEntity;
import org.rickhuizing.petclinicguru.services.CrudService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity> implements CrudService<T, Long> {

    protected Map<Long, T> map = new HashMap<>();

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    public T findById(Long id) {
        return map.get(id);
    }

    public T save(T object) {
        map.put(object.getId(), object);
        return object;
    }

    public void deleteById(Long id) {
        map.remove(id);
    }

    public void delete(T object) {
        map.entrySet().removeIf(e -> e.getValue().equals(object));
    }
}
