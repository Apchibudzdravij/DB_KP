package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.controllerHelper.AdminHTML;
import com.belstu.drevoten.DB_KP.forms.UserChangeForm;
import com.belstu.drevoten.DB_KP.model.Executive_Admin;
import com.belstu.drevoten.DB_KP.model.UserGender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminController {

    Executive_Admin testExecuteAdmin = new Executive_Admin("71201093", "Anna", "Anna", "Anna", UserGender.Gender_Fluid, "71201093", 0);

    @GetMapping(value="/asettings")
    public ModelAndView settings(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminSettings());
        model.addAttribute("user_name", testExecuteAdmin.getFirstName());
        model.addAttribute("user_family", testExecuteAdmin.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value="/administrator")
    public ModelAndView backToMain(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminMain(testExecuteAdmin));
        model.addAttribute("user_name", testExecuteAdmin.getFirstName());
        model.addAttribute("user_family", testExecuteAdmin.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/achange")
    public ModelAndView changePost(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminChange());
        model.addAttribute("user_name", testExecuteAdmin.getFirstName());
        model.addAttribute("user_family", testExecuteAdmin.getFamilyName());
        if (userChangeForm.getNewPassword() != null) {
            if (!userChangeForm.getNewPassword().equals(userChangeForm.getCheckNewPassword())) {
                model.addAttribute("event", "The password in confirm field is not equal to the new password!");
            } else if (userChangeForm.getNewPassword().equals(userChangeForm.getPassword())) {
                model.addAttribute("event", "The new password cannot equals to the old password!");
            }
        } else {
            model.addAttribute("event", "User information updated!");
        }

        return modelAndView;
    }

    @GetMapping(value = "/achange")
    public ModelAndView adminChangeForm(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm) {

        userChangeForm.setFirstName(testExecuteAdmin.getFirstName());
        userChangeForm.setFamilyName(testExecuteAdmin.getFamilyName());
        userChangeForm.setFatherName(testExecuteAdmin.getFatherName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminChange());
        model.addAttribute("user_name", testExecuteAdmin.getFirstName());
        model.addAttribute("user_family", testExecuteAdmin.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value="/aSendMessage")
    public ModelAndView sendMessageForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminSendMessage());
        model.addAttribute("user_name", testExecuteAdmin.getFirstName());
        model.addAttribute("user_family", testExecuteAdmin.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/amessages")
    public ModelAndView messagesAfterSending(Model model) {
        ///TODO sendidng
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminMessages());
        model.addAttribute("user_name", testExecuteAdmin.getFirstName());
        model.addAttribute("user_family", testExecuteAdmin.getFamilyName());
        return modelAndView;
    }
    @GetMapping(value = "/amessages")
    public ModelAndView messages(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminMessages());
        model.addAttribute("user_name", testExecuteAdmin.getFirstName());
        model.addAttribute("user_family", testExecuteAdmin.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value = "/addUsers")
    public ModelAndView addUsers(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminAddUsers());
        model.addAttribute("user_name", testExecuteAdmin.getFirstName());
        model.addAttribute("user_family", testExecuteAdmin.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value = "/adminChangeUsers")
    public ModelAndView adminChangeUsers(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminChangeUsers());
        model.addAttribute("user_name", testExecuteAdmin.getFirstName());
        model.addAttribute("user_family", testExecuteAdmin.getFamilyName());
        return modelAndView;
    }
}
