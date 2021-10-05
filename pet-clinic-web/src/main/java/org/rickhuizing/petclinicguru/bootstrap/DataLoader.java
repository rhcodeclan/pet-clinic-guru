package org.rickhuizing.petclinicguru.bootstrap;

import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.model.Vet;
import org.rickhuizing.petclinicguru.services.OwnerService;
import org.rickhuizing.petclinicguru.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner = new Owner();
        owner.setId(2L);
        owner.setFirstName("John");
        owner.setLastName("Doe");
        ownerService.save(owner);

        Owner owner2 = new Owner();
        owner2.setId(1L);
        owner2.setFirstName("Bill");
        owner2.setLastName("Johnson");
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setId(3L);
        vet1.setFirstName("Lightnin");
        vet1.setLastName("Hopkins");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(4L);
        vet2.setFirstName("Kaladin");
        vet2.setLastName("Stormblessed");
        vetService.save(vet2);

        System.out.println("Loaded owners and vets");
    }
}
