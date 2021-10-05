package org.rickhuizing.petclinicguru.services.map;

import org.rickhuizing.petclinicguru.model.BaseEntity;
import org.rickhuizing.petclinicguru.services.CrudService;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity> implements CrudService<T, Long> {

    protected Map<Long, T> map = new HashMap<>();

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    public T findById(Long id) {
        return map.get(id);
    }

    public T save(T object) {
        if (object == null) return null;
        if (object.getId() == null) {
            object.setId(getNextId());
        }
        map.put(object.getId(), object);
        return object;
    }

    public void deleteById(Long id) {
        map.remove(id);
    }

    public void delete(T object) {
        map.entrySet().removeIf(e -> e.getValue().equals(object));
    }

    private Long getNextId() {
        if (map.isEmpty()) return 0L;
        return Collections.max(map.keySet()) + 1;
    }
}
