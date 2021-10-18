package org.rickhuizing.petclinicguru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("menu", "home");
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index() {
        return "index";
    }
}
