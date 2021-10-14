package org.rickhuizing.petclinicguru.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rickhuizing.petclinicguru.model.Owner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AbstractMapServiceTest {

    AbstractMapService<Owner> mapService;
    final long ownerId = 1L;

    @BeforeEach
    void setUp() {
        mapService = new OwnerServiceMap(new PetServiceMap(new PetTypeServiceMap()));
        mapService.save(Owner.builder().id(ownerId).build());
    }

    @Test
    void findAll() {
        Set<Owner> all = mapService.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void findById() {
        Owner byId = mapService.findById(ownerId);
        assertEquals(ownerId, byId.getId());
    }

    @Test
    void save() {
        // Given
        Owner owner = new Owner();
        assertNull(owner.getId());
        // When
        mapService.save(owner);
        // Then
        assertEquals(ownerId + 1, owner.getId());

        Owner byId = mapService.findById(owner.getId());
        assertSame(owner, byId);
    }

    @Test
    void deleteById() {
        mapService.deleteById(ownerId);
        assertEquals(0, mapService.findAll().size());
    }

    @Test
    void delete() {
        Owner byId = mapService.findById(ownerId);
        mapService.delete(byId);
        assertEquals(0, mapService.findAll().size());
    }
}