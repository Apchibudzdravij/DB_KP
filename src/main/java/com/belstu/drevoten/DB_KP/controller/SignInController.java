package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.controllerHelper.AdminHTML;
import com.belstu.drevoten.DB_KP.controllerHelper.StudentHTML;
import com.belstu.drevoten.DB_KP.controllerHelper.TeacherHTML;
import com.belstu.drevoten.DB_KP.forms.UserTypeForm;
import com.belstu.drevoten.DB_KP.model.*;
import com.belstu.drevoten.DB_KP.model.DAO.AuthDAO;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;
import com.belstu.drevoten.DB_KP.model.exceptions.ProblemPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Controller
public class SignInController {
    //User_List studentType;

    //Students userStudents;
    //ArrayList<UserType> studentTypeArrayList;
    //ArrayList<Students> studentsArrayList;
    //String ut;
    //@Autowired
    //MainServiceInt mainService;

    @Value("${welcome_en.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    @PostMapping(value = "/userpage")
    public ModelAndView enter(Model model, @Valid @ModelAttribute("userForm") UserTypeForm userTypeForm) {
        ModelAndView modelAndView = new ModelAndView();

        MainDAO mainDAO = new MainDAO();
        AuthDAO authDAO = new AuthDAO();

        String tempID = userTypeForm.getAdminID();
        String tempPass = userTypeForm.getPassword();

        try {
            switch (mainDAO.getIsUserInDB(tempID)){
                case 's':
                    modelAndView.setViewName("student");
                    StudentsNoPass student = authDAO.getStudentIfPassword(tempID, tempPass);
                    if (student == null)
                        throw new ProblemPasswordException("Incorrect username and/or password!");
                    model.addAttribute("editable_content", StudentHTML.studentMain(student, ""));
                    model.addAttribute("user_name", student.getFirstName());
                    model.addAttribute("user_family", student.getFamilyName());
                    StudentController.testStudents = student;
                    break;
                case 't':
                    modelAndView.setViewName("teacher");
                    TeachersNoPass teacher = authDAO.getTeacherIfPassword(tempID, tempPass);
                    if (teacher == null)
                        throw new ProblemPasswordException("Incorrect username and/or password!");
                    model.addAttribute("editable_content", TeacherHTML.teacherMain(teacher));
                    model.addAttribute("user_name", teacher.getFirstName());
                    model.addAttribute("user_family", teacher.getFamilyName());
                    TeacherController.testTeachers = teacher;
                    break;
                case 'a':
                    modelAndView.setViewName("administrator");
                    Executive_AdminNoPass executeAdmin = authDAO.getAdminIfPassword(tempID, tempPass);
                    if (executeAdmin == null)
                        throw new ProblemPasswordException("Incorrect username and/or password!");
                    modelAndView.setViewName("administrator");
                    model.addAttribute("editable_content", AdminHTML.adminMain(executeAdmin));
                    model.addAttribute("user_name", executeAdmin.getFirstName());
                    model.addAttribute("user_family", executeAdmin.getFamilyName());
                    AdminController.testAdmin = executeAdmin;
                    break;
                case 'e' :
                    //System.err.println("User is not S, not T and not A!");
                    log.error("User is not S, not T and not A!");
                    model.addAttribute("errorMessage", "User not found");
                    model.addAttribute("message", message);
                    modelAndView.setViewName("welcome");
                    break;
                default  :
                    //System.err.println("Incorrect crushing login");
                    log.error("Incorrect crushing login");
                    model.addAttribute("errorMessage", "User not found");
                    model.addAttribute("message", message);
                    modelAndView.setViewName("welcome");
                    break;
            }
        } catch (ProblemPasswordException e) {
            //System.err.println(e.toString());
            log.error(e.toString());
            model.addAttribute("errorMessage", e.toString());
            model.addAttribute("message", message);
            modelAndView.setViewName("welcome");
        }
        return modelAndView;
    }
}
