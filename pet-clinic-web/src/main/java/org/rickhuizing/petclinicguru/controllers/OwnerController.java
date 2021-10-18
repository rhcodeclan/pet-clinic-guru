package org.rickhuizing.petclinicguru.controllers;


import org.apache.coyote.Response;
import org.rickhuizing.petclinicguru.configuration.ErrorControllerAdvice.NotFoundException;
import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping({"", "/"})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/owners";
    }

    @RequestMapping("/find")
    public String findOwners() {
        return "owners/find";
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
