package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.controllerHelper.AdminHTML;
import com.belstu.drevoten.DB_KP.controllerHelper.StudentHTML;
import com.belstu.drevoten.DB_KP.forms.UserTypeForm;
import com.belstu.drevoten.DB_KP.model.*;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


@Controller
public class SignInController {
    User_List studentType;
    Students userStudents;
    ArrayList<UserType> studentTypeArrayList;
    ArrayList<Students> studentsArrayList;
    String ut;

    @Value("${welcome_en.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    @PostMapping(value = "/userpage")
    public ModelAndView enter(Model model, @ModelAttribute("userForm") UserTypeForm userTypeForm) {
        ModelAndView modelAndView = new ModelAndView();

        MainDAO mainDAO = new MainDAO();

        /*studentsArrayList = new ArrayList<>();
        studentsArrayList.add(new Students("71201091", "Eugene", "Drevoten", "Vladimirovich", "Male", "71201091", 3, "5-2", "FIT", "POIT", 0));
        studentsArrayList.add(new Students("71201092", "Ivan", "Ivanov", "Ivanovich", "Male", "71201092", 3, "5-2", "FIT", "POIT", 0));
        studentsArrayList.add(new Students("71201093", "Anna", "Anna", "Anna", "Female", "71201093", 3, "5-2", "FIT", "POIT", 0));

        */String tempID = userTypeForm.getAdminID();
        /*AtomicBoolean isInList = new AtomicBoolean(false);
        AtomicBoolean isStudent = new AtomicBoolean(false);

        studentsArrayList.forEach((students) -> {
            if (students.getStudentID().equals(studentType.getUserId())){
                userStudents = students;
                isStudent.set(true);
                return;
            }
        });
        /*if (!isStudent.get()) {
            model.addAttribute("errorMessage", "Student not found");
            model.addAttribute("message", message);
            modelAndView.setViewName("welcome");
            return modelAndView;
        } else if (!userTypeForm.getPassword().equals(userStudents.getPassword())) {
            model.addAttribute("errorMessage", "Incorrect password and/or username");
            model.addAttribute("message", message);
            modelAndView.setViewName("welcome");
            return modelAndView;
        }*/


        switch (mainDAO.getIsUserInDB(tempID, ut)){
            case "s":
                modelAndView.setViewName("student");
                model.addAttribute("editable_content", StudentHTML.studentMain(userStudents));
                model.addAttribute("user_name", userStudents.getFirstName());
                model.addAttribute("user_family", userStudents.getFamilyName());
                break;
            case "t":
                modelAndView.setViewName("teacher");
                break;
            case "a":
                Executive_Admin executeAdmin = new Executive_Admin(userStudents.getStudentID(), userStudents.getFirstName(), userStudents.getFamilyName(),
                                        userStudents.getFatherName(), UserGender.Gender_Fluid, userStudents.getPassword(), userStudents.getUnreadMessages());
                modelAndView.setViewName("administrator");
                model.addAttribute("editable_content", AdminHTML.adminMain(executeAdmin));
                model.addAttribute("user_name", userStudents.getFirstName());
                model.addAttribute("user_family", userStudents.getFamilyName());
                break;
            default :
                System.err.println("User is not S, not T and not A!");
                model.addAttribute("errorMessage", "User not found");
                model.addAttribute("message", message);
                modelAndView.setViewName("welcome");
                break;
        }
        return modelAndView;
    }
}
