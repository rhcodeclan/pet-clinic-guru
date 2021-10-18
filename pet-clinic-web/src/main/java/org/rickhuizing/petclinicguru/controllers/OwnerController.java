package org.rickhuizing.petclinicguru.controllers;


import org.apache.coyote.Response;
import org.rickhuizing.petclinicguru.exceptions.NotFoundException;
import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("menu", "owners");
    }

    @RequestMapping("/find")
    public String initFindForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/find";
    }

    @RequestMapping({"", "/"})
    public String processFindForm(Owner owner, Model model) {
        Set<Owner> modelOwners;
        if(owner.getLastName() == null){
            owner.setLastName("");
            modelOwners = ownerService.findAll();
        } else {
            List<Owner> byLastName = ownerService.findByLastNameContaining(owner.getLastName());
            if (byLastName.isEmpty()){
                modelOwners = Collections.emptySet();
            } else {
                modelOwners = Set.copyOf(byLastName);
            }
        }
        model.addAttribute("owners", modelOwners);
        return "owners/owners";
    }

    @GetMapping("/{ownerId}")
    public String getOwner(@PathVariable int ownerId, Model model, Response response) {
        Owner byId = ownerService.findById((long) ownerId);
        if (byId == null) {
            throw new NotFoundException("Owner with ID " + ownerId + " not found");
        }
        model.addAttribute("owner", byId);
        return "owners/viewOwner";
    }
}
