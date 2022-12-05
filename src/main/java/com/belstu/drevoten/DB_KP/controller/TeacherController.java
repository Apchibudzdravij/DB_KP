package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.controllerHelper.StudentHTML;
import com.belstu.drevoten.DB_KP.controllerHelper.TeacherHTML;
import com.belstu.drevoten.DB_KP.forms.UserChangeForm;
import com.belstu.drevoten.DB_KP.forms.UserTypeForm;
import com.belstu.drevoten.DB_KP.model.Student;
import com.belstu.drevoten.DB_KP.model.Teacher;
import com.belstu.drevoten.DB_KP.model.UserGender;
import com.belstu.drevoten.DB_KP.model.UserType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TeacherController {

    Teacher testTeacher = new Teacher("71201092", "Marina", "Dubovik", "Vladimirovna", UserGender.Female, "71201092", "ISiT", 0);

    @GetMapping(value="/tsettings")
    public ModelAndView settings(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherSettings());
        model.addAttribute("user_name", testTeacher.getFirstName());
        model.addAttribute("user_family", testTeacher.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value="/teacher")
    public ModelAndView backToMain(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherMain(testTeacher));
        model.addAttribute("user_name", testTeacher.getFirstName());
        model.addAttribute("user_family", testTeacher.getFamilyName());
        return modelAndView;
    }
}
