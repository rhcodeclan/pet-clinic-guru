package org.rickhuizing.petclinicguru.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.model.Pet;
import org.rickhuizing.petclinicguru.model.PetType;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    PetServiceMap petServiceMap;

    final long ownerId = 1L;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap(new PetTypeServiceMap());
        ownerServiceMap = new OwnerServiceMap(petServiceMap);
        ownerServiceMap.save(Owner.builder().id(ownerId).build());
    }

    @Test
    void save() { // fixme more of an Integration Test
        // Given
        Pet pet = Pet.builder().petType(new PetType()).build();
        assertNull(pet.getId());
        Owner owner = Owner.builder().build();
        owner.getPets().add(pet);
        // When
        ownerServiceMap.save(owner);
        // Then
        assertNotNull(pet.getId());
        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner huizing = Owner.builder().lastName("Huizing").build();
        ownerServiceMap.save(huizing);
        assertNull(ownerServiceMap.findByLastName("bla"));
        assertNotNull(ownerServiceMap.findByLastName("Huizing"));
    }
}