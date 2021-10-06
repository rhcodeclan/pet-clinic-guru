package org.rickhuizing.petclinicguru.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person {

    private Set<VetSpeciality> specialities = new HashSet<>();
}
