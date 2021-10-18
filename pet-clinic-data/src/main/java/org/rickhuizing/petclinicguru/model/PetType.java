package org.rickhuizing.petclinicguru.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "petType")
    private Set<Pet> pets = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}
