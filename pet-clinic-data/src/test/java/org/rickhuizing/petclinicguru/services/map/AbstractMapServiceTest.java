package org.rickhuizing.petclinicguru.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rickhuizing.petclinicguru.model.BaseEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AbstractMapServiceTest {

    AbstractMapService<BaseEntity> mapService;
    final long entityId = 1L;

    @BeforeEach
    void setUp() {
        mapService = new AbstractMapService<>() {
        };
        mapService.save(new BaseEntity(entityId) {
        });
    }

    @Test
    void findAll() {
        Set<BaseEntity> all = mapService.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void findById() {
        BaseEntity byId = mapService.findById(entityId);
        assertEquals(entityId, byId.getId());
    }

    @Test
    void save() {
        // Given
        BaseEntity baseEntity = new BaseEntity() {
        };
        assertNull(baseEntity.getId());
        // When
        mapService.save(baseEntity);
        // Then
        assertEquals(entityId + 1, baseEntity.getId());

        BaseEntity byId = mapService.findById(baseEntity.getId());
        assertSame(baseEntity, byId);
    }

    @Test
    void deleteById() {
        mapService.deleteById(entityId);
        assertEquals(0, mapService.findAll().size());
    }

    @Test
    void delete() {
        BaseEntity byId = mapService.findById(entityId);
        mapService.delete(byId);
        assertEquals(0, mapService.findAll().size());
    }
}