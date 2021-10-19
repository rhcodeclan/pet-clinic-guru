package org.rickhuizing.petclinicguru.controllers;

import org.rickhuizing.petclinicguru.model.Vet;
import org.rickhuizing.petclinicguru.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("menu", "vets");
    }

    @RequestMapping({"/vets", "/vets.html"})
    public String listVets(Model model) {
        model.addAttribute("listVets", vetService.findAll());
        return "vets/index";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsApi(){
        return vetService.findAll();
    }
}
