package org.rickhuizing.petclinicguru.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String address;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    private String telephone;

    @Setter(AccessLevel.NONE)
    @Builder.Default
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
