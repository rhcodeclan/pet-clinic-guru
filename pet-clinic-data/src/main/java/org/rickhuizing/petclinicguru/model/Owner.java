package org.rickhuizing.petclinicguru.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "owner",
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private Set<Pet> pets = new HashSet<>();

    public Set<Pet> getPets() {
        return Collections.unmodifiableSet(pets);
    }

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setOwner(this);
    }

    public Pet getPet(Long id) {
        return pets.stream().filter(pet -> pet.getId().equals(id)).findFirst().orElse(null);
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
        pet.setOwner(null);
    }
}
