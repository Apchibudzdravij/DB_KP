package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.controllerHelper.StudentHTML;
import com.belstu.drevoten.DB_KP.forms.UserChangeForm;
import com.belstu.drevoten.DB_KP.forms.UserPropsForm;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;
import com.belstu.drevoten.DB_KP.model.Students;
import com.belstu.drevoten.DB_KP.model.StudentsNoPass;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@SessionAttributes("user_entity")
public class StudentController {

    static StudentsNoPass testStudents;// = new StudentsNoPass("71201091", "Eugene", "Drevoten", "Vladimirovich", 2, 3, "5-2", "FIT", "POIT", 0, "E", "S");
    MainDAO mainDAO;

    @GetMapping(value="/ssettings")
    public ModelAndView settings(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentSettings(false));
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value="/ssettings")
    public ModelAndView newSettings(Model model, @Valid @ModelAttribute("userpropsform") UserPropsForm userPropsForm,
                                    @RequestParam("lang") String lang, @RequestParam("theme") String theme) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        mainDAO = new MainDAO();
        userPropsForm.setLang(lang);
        userPropsForm.setColor(theme);
        model.addAttribute("editable_content",
                            StudentHTML.studentSettings(mainDAO.saveProperties(testStudents.getStudentID(),
                                                        userPropsForm.getLang(), userPropsForm.getColor(),
                                                        "student")));
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value="/student")
    public ModelAndView backToMain(Model model) {

        //testStudents = (StudentsNoPass) model.getAttribute("user_entity");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentMain(testStudents));
        model.addAttribute("user_entity", testStudents);
        return modelAndView;
    }
    @GetMapping(value="/askquestion")
    public ModelAndView askQuestion(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentAsk("",""));
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }
    @PostMapping(value = "/smessages")
    public ModelAndView messagesAfterSending(Model model) {
        ///TODO sendidng
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentMessages(testStudents));
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }
    @GetMapping(value = "/smessages")
    public ModelAndView messages(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentMessages(testStudents));
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value = "/schange")
    public ModelAndView changeView(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm) {

        userChangeForm.setFirstName(testStudents.getFirstName());
        userChangeForm.setFamilyName(testStudents.getFamilyName());
        userChangeForm.setFatherName(testStudents.getFatherName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentChange());
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/schange")
    public ModelAndView changePost(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm) {
        ///TODO DB request
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentChange());
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        ///TODO into helpHTML
        if (userChangeForm.getNewPassword() != null) {
            if (!userChangeForm.getNewPassword().equals(userChangeForm.getCheckNewPassword())) {
                model.addAttribute("event", "The password in confirm field is not equal to the new password!");
            } else if (userChangeForm.getNewPassword().equals(userChangeForm.getPassword())) {
                model.addAttribute("event", "The new password cannot equals to the old password!");
            }
        } else {
            model.addAttribute("event", "User information updated!");
        }
        model.addAttribute("user_entity", testStudents);

        return modelAndView;
    }

    @GetMapping(value = "/coursePlan")
    public ModelAndView coursePlan(Model model) {

        testStudents = (StudentsNoPass) model.getAttribute("user_entity");

        ModelAndView modelAndView = new ModelAndView();

        ///TODO projects list and amount

        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentCoursePlan());
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        model.addAttribute("user_entity", testStudents);

        model.addAttribute("project_amount", 3);
        model.addAttribute("uniqueness_value", 100);
        model.addAttribute("task_submitted", "Task submitted!");
        model.addAttribute("steps_completed", "2/3");
        model.addAttribute("days_left", 3);

        return modelAndView;
    }

    @GetMapping(value = "/myProjects")
    public ModelAndView myProjects(Model model) {

        testStudents = (StudentsNoPass) model.getAttribute("user_entity");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentMyProjects());
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        model.addAttribute("user_entity", testStudents);

        model.addAttribute("average_mark", 9);
        model.addAttribute("average_uniqueness", 100);

        return modelAndView;
    }

    @ModelAttribute("user_name")
    String getUserName() {
        return testStudents.getFirstName();
    }
    @ModelAttribute("user_family")
    String getUserSirname() {
        return testStudents.getFamilyName();
    }
}
