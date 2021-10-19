package org.rickhuizing.petclinicguru.controllers;


import org.apache.coyote.Response;
import org.rickhuizing.petclinicguru.exceptions.NotFoundException;
import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String initFindForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/find";
    }

    @RequestMapping({"", "/"})
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
        Set<Owner> modelOwners;
        if (owner.getLastName() == null) {
            owner.setLastName("");
            modelOwners = ownerService.findAll();
        } else {
            List<Owner> byLastName = ownerService.findByLastNameContaining(owner.getLastName());
            if (byLastName.isEmpty()) {
                bindingResult.rejectValue("lastName", "NotFound", "Lastname containing " + owner.getLastName() + " has not been found");
                return "owners/find";
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

    @GetMapping("/new")
    public String initCreateOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "/owners/createUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreateOwnerForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "/owners/createUpdateOwnerForm";
        }
        owner = ownerService.save(owner);
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping("{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable int ownerId, Model model) {
        Owner byId = ownerService.findById((long) ownerId);
        if (byId == null) {
            throw new NotFoundException("Owner with ID " + ownerId + " not found");
        }
        model.addAttribute("owner", byId);
        return "/owners/createUpdateOwnerForm";
    }

    @PostMapping("{ownerId}/edit")
    public String processUpdateOwnerForm(@PathVariable int ownerId, @Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "/owners/createUpdateOwnerForm";
        }
        Owner byId = ownerService.findById((long) ownerId);
        if (byId == null) {
            throw new NotFoundException("Owner with ID " + ownerId + " not found");
        }
        owner.setId(byId.getId());
        ownerService.save(owner);
        return "redirect:/owners/" + ownerId;
    }
}
