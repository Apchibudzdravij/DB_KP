package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.controllerHelper.StudentHTML;
import com.belstu.drevoten.DB_KP.forms.UserTypeForm;
import com.belstu.drevoten.DB_KP.model.Student;
import com.belstu.drevoten.DB_KP.model.UserType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;


@Controller
public class SignInController {
    UserType studentType;
    Student userStudent;
    ArrayList<UserType> studentTypeArrayList;
    ArrayList<Student> studentArrayList;


    @Value("${welcome_en.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    @PostMapping(value = "/userpage")
    public ModelAndView enter(Model model, @ModelAttribute("userForm") UserTypeForm userTypeForm) {
        ModelAndView modelAndView = new ModelAndView();

        studentTypeArrayList = new ArrayList<>();
        studentTypeArrayList.add(new UserType("71201091", "s"));
        studentTypeArrayList.add(new UserType("71201092", "t"));
        studentTypeArrayList.add(new UserType("71201093", "s"));

        studentArrayList = new ArrayList<>();
        studentArrayList.add(new Student("71201091", "Eugene", "Drevoten", "Vladimirovich", "Male", "71201091", 3, "5-2", "FIT", "POIT", 0));
        studentArrayList.add(new Student("71201092", "Ivan", "Ivanov", "Ivanovich", "Male", "71201092", 3, "5-2", "FIT", "POIT", 0));
        studentArrayList.add(new Student("71201093", "Anna", "Anna", "Anna", "Female", "71201093", 3, "5-2", "FIT", "POIT", 0));

        String tempID = userTypeForm.getAdminID();
        AtomicBoolean isInList = new AtomicBoolean(false);
        AtomicBoolean isStudent = new AtomicBoolean(false);

        studentTypeArrayList.forEach((user) -> {
            if (user.getAdminID().equals(tempID)) {
                isInList.set(true);
                studentType = user;
                return;
            }
        });
        if (!isInList.get()) {
            model.addAttribute("errorMessage", "User not found");
            model.addAttribute("message", message);
            modelAndView.setViewName("welcome");
            return modelAndView;
        }

        studentArrayList.forEach((student) -> {
            if (student.getStudentID().equals(studentType.getAdminID())){
                userStudent = student;
                isStudent.set(true);
                return;
            }
        });
        if (!isStudent.get()) {
            model.addAttribute("errorMessage", "Student not found");
            model.addAttribute("message", message);
            modelAndView.setViewName("welcome");
            return modelAndView;
        } else if (!userTypeForm.getPassword().equals(userStudent.getPassword())) {
            model.addAttribute("errorMessage", "Incorrect password and/or username");
            model.addAttribute("message", message);
            modelAndView.setViewName("welcome");
            return modelAndView;
        }


        switch (studentType.getType()){
            case "s":
                modelAndView.setViewName("student");
                model.addAttribute("editable_content", StudentHTML.studentMain(userStudent));
                model.addAttribute("user_name", userStudent.getFirstName());
                model.addAttribute("user_family", userStudent.getFamilyName());
                break;
            case "t":
                modelAndView.setViewName("teacher");
                break;
            case "a":
                modelAndView.setViewName("admin");
                break;
            default :
                System.err.println("User is not S, not T and not A!");
                break;
        }
        return modelAndView;
    }
}
