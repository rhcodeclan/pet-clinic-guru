package org.rickhuizing.petclinicguru.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.model.Pet;
import org.rickhuizing.petclinicguru.model.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ServiceIT {

    @Autowired
    OwnerService ownerService;
    @Autowired
    PetService petService;
    @Autowired
    PetTypeService petTypeService;
    @Autowired
    SpecialityService specialityService;
    @Autowired
    VetService vetService;
    @Autowired
    VisitService visitService;


    PetType newPetType;
    final String newPetTypeName = "monkeys";
    Pet newPet;
    final String newPetName = "NON existing petName";

    @Test
    public void addPetToOwner() {
        // given
        newPetType = new PetType();
        newPetType.setName(newPetTypeName);
        petTypeService.save(newPetType);

        newPet = new Pet();
        newPet.setPetType(newPetType);
        newPet.setName(newPetName);
        newPet.setBirthDay(LocalDate.now());

        Owner owner = ownerService.findAll().stream().findFirst().orElseThrow();
        int ownerPets = owner.getPets().size();
        //when
        owner.addPet(newPet);
        owner = ownerService.save(owner);
        //then
        Owner retrievedOwner = ownerService.findById(owner.getId());
        assertNotEquals(ownerPets, retrievedOwner.getPets().size());
        Pet savedPet = retrievedOwner.getPets().stream().filter(p -> p.getName().equals(newPetName)).findFirst().orElse(null);
        assertNotNull(savedPet);
        assertEquals(newPetTypeName, savedPet.getPetType().getName());
    }

    @Test
    @DirtiesContext
    public void removeOwner() {
        // given
        Owner owner = ownerService.findAll().stream()
                .filter(o -> o.getPets().size() != 0)
                .findFirst().orElseThrow();
        Set<Pet> ownerPets = owner.getPets();
        //when
        ownerService.delete(owner);
        //then
        assertNull(ownerService.findById(owner.getId()));
        Pet firstPet = ownerPets.stream().findFirst().orElseThrow();
        assertNull(petService.findById(firstPet.getId()));
    }

    @Test
    @DirtiesContext
    public void deletePetById() {
        //given
        Owner owner = ownerService.findAll().stream().findFirst().orElseThrow();
        Pet ownerPet = owner.getPets().stream().findFirst().orElseThrow();
        Long petId = ownerPet.getId();
        //when
        petService.deleteById(ownerPet.getId());
        //then
        Pet retrievedPet = petService.findById(petId);
        assertNull(retrievedPet);
    }

    @Test
    @DirtiesContext
    public void deletePet() {
        //given
        Owner owner = ownerService.findAll().stream().findFirst().orElseThrow();
        Pet ownerPet = owner.getPets().stream().findFirst().orElseThrow();
        Long petId = ownerPet.getId();
        //when
        petService.delete(ownerPet);
        //then
        Pet retrievedPet = petService.findById(petId);
        assertNull(retrievedPet);
    }

    @Test
    @DirtiesContext
    public void removePetFromOwner() {
        //given
        Owner owner = ownerService.findAll().stream().findFirst().orElseThrow();
        Pet ownerPet = owner.getPets().stream().findFirst().orElseThrow();
        Long petId = ownerPet.getId();
        //when
        owner.removePet(ownerPet);
        ownerService.save(owner);
        Pet petServiceById = petService.findById(petId);
        //then
        assertNull(petServiceById);
    }
}
