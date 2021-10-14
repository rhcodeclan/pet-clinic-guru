package org.rickhuizing.petclinicguru.services.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.repositories.OwnerRepository;
import org.rickhuizing.petclinicguru.repositories.PetRepository;
import org.rickhuizing.petclinicguru.repositories.PetTypeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJPATest {

    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerServiceJPA ownerService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastName() {
        Owner owner = Owner.builder().id(1L).lastName(LAST_NAME).build();
        when(ownerRepository.findByLastName(any())).thenReturn(Optional.of(owner));
        Owner smith = ownerService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, smith.getLastName());
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void delete() {
    }
}