package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.model.UserType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SignInController {

    UserType studentType;

    @PostMapping(value = "/userpage")
    public ModelAndView enter(Model model) {
        ModelAndView modelAndView = new ModelAndView();

        studentType = new UserType("71201091", "s");

        switch (studentType.getType()){
            case "s":
                modelAndView.setViewName("student");
                break;
            default :
                System.err.println("User is not S, not T and not A!");
                break;
        }

        //model.addAttribute("message", message);
        //model.addAttribute("errorMessage", errorMessage);

        return modelAndView;
    }
}
