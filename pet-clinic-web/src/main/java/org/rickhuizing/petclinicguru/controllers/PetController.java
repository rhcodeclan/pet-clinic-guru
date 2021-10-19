package org.rickhuizing.petclinicguru.controllers;

import org.rickhuizing.petclinicguru.exceptions.NotFoundException;
import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.model.Pet;
import org.rickhuizing.petclinicguru.model.PetType;
import org.rickhuizing.petclinicguru.services.OwnerService;
import org.rickhuizing.petclinicguru.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    //    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;


    public PetController(OwnerService ownerService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("petTypes")
    public Collection<PetType> getPetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void setAllowedFields(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initPetCreateForm(Model model, @PathVariable("ownerId") Long ownerId) {
        Owner owner = ownerService.findById(ownerId);
        Pet pet = new Pet();
        owner.addPet(pet);
        model.addAttribute("pet", pet);
        return "/pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processPetCreateForm(Model model, @Valid Pet pet, BindingResult result, @PathVariable("ownerId") Long ownerId) {
        if (result.hasErrors()) {
            return "/pets/createOrUpdatePetForm";
        }
        Owner owner = ownerService.findById(ownerId);
        owner.addPet(pet);
        ownerService.save(owner);
        return "redirect:/owners/" + ownerId;
    }

    @GetMapping("/pets/{petId}/edit")
    public String initPetEditForm(Model model,
                                  @PathVariable("ownerId") Long ownerId,
                                  @PathVariable("petId") Long petId) {
        Owner owner = ownerService.findById(ownerId);
        Optional<Pet> pet = owner.getPets().stream().filter(p -> p.getId().equals(petId)).findFirst();
        if (pet.isEmpty()) {
            throw new NotFoundException("Owner does not have pet with Id " + petId);
        }
        model.addAttribute("pet", pet.get());
        return "/pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String processPetEditForm(Model model, @Valid Pet pet, BindingResult result,
                                     @PathVariable("ownerId") Long ownerId,
                                     @PathVariable("petId") Long petId) {
        Owner owner = ownerService.findById(ownerId);
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return "/pets/createOrUpdatePetForm";
        }
        Optional<Pet> petOpt = owner.getPets().stream().filter(p -> p.getId().equals(petId)).findFirst();
        if (petOpt.isEmpty()) {
            throw new NotFoundException("Owner does not have pet with Id " + petId);
        }
        owner.removePet(petOpt.get());
        owner.addPet(pet);
        ownerService.save(owner);
        return "redirect:/owners/" + ownerId;
    }
}