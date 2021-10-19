package org.rickhuizing.petclinicguru.controllers;

import org.rickhuizing.petclinicguru.model.PetType;
import org.rickhuizing.petclinicguru.repositories.PetTypeRepository;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeRepository petTypeRepository;

    public PetTypeFormatter(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Iterable<PetType> petTypes = petTypeRepository.findAll();
        for (PetType type : petTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

}
