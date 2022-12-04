package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.forms.UserTypeForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
    @Value("${welcome_en.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping(value = {"/","/index", "/welcome"})
    public ModelAndView index(Model model, @ModelAttribute("userForm") UserTypeForm userTypeForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return modelAndView;
    }
}
