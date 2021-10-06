package org.rickhuizing.petclinicguru.bootstrap;

import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.model.Pet;
import org.rickhuizing.petclinicguru.model.PetType;
import org.rickhuizing.petclinicguru.model.Vet;
import org.rickhuizing.petclinicguru.services.OwnerService;
import org.rickhuizing.petclinicguru.services.PetTypeService;
import org.rickhuizing.petclinicguru.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        petTypeService.save(cat);

        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");

        owner.setAddress("street 1");
        owner.setCity("Groningen");
        owner.setTelephone("1234567890");

        Pet pet = new Pet();
        pet.setPetType(dog);
        pet.setName("Mr Pickles");
        pet.setBirthDay(LocalDate.now());
        pet.setOwner(owner);
        ownerService.save(owner);

        Owner owner2 = new Owner();
        owner2.setFirstName("Bill");
        owner2.setLastName("Johnson");

        owner2.setAddress("Street 5g");
        owner2.setCity("Big City");
        owner2.setTelephone("245123-53234");

        pet = new Pet();
        pet.setPetType(cat);
        pet.setName("Meowingtons");
        pet.setBirthDay(LocalDate.now());
        pet.setOwner(owner2);

        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Lightnin");
        vet1.setLastName("Hopkins");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kaladin");
        vet2.setLastName("Stormblessed");
        vetService.save(vet2);

        System.out.println("Loaded owners and vets");
    }
}
