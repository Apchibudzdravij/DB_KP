package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.controllerHelper.StudentHTML;
import com.belstu.drevoten.DB_KP.model.Student;
import com.belstu.drevoten.DB_KP.model.UserType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class StudentController {

    Student testStudent = new Student("71201091", "Eugene", "Drevoten", "Vladimirovich", "Male", "71201091", 3, "5-2", "FIT", "POIT", 0);

    @GetMapping(value="/settings")
    public ModelAndView settings(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentSettings());
        model.addAttribute("user_name", testStudent.getFirstName());
        model.addAttribute("user_family", testStudent.getFamilyName());
        return modelAndView;
    }
    @GetMapping(value="/student")
    public ModelAndView backToMain(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentMain(testStudent));
        model.addAttribute("user_name", testStudent.getFirstName());
        model.addAttribute("user_family", testStudent.getFamilyName());
        return modelAndView;
    }
    @GetMapping(value="/askquestion")
    public ModelAndView askQuestion(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentAsk());
        model.addAttribute("user_name", testStudent.getFirstName());
        model.addAttribute("user_family", testStudent.getFamilyName());
        return modelAndView;
    }
    @PostMapping(value = "/messages")
    public ModelAndView messagesAfterSending(Model model) {
        ///TODO sendidng
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentMessages());
        model.addAttribute("user_name", testStudent.getFirstName());
        model.addAttribute("user_family", testStudent.getFamilyName());
        return modelAndView;
    }
    @GetMapping(value = "/messages")
    public ModelAndView messages(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentMessages());
        model.addAttribute("user_name", testStudent.getFirstName());
        model.addAttribute("user_family", testStudent.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value = "/change")
    public ModelAndView changeView(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentChange());
        model.addAttribute("user_name", testStudent.getFirstName());
        model.addAttribute("user_family", testStudent.getFamilyName());
        return modelAndView;
    }
}
