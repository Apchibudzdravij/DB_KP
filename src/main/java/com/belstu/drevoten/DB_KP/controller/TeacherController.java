package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.controllerHelper.StudentHTML;
import com.belstu.drevoten.DB_KP.controllerHelper.TeacherHTML;
import com.belstu.drevoten.DB_KP.forms.KursTaskForm;
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

    @PostMapping(value = "/tmessages")
    public ModelAndView messagesAfterSending(Model model) {
        ///TODO sendidng
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherMessages());
        model.addAttribute("user_name", testTeacher.getFirstName());
        model.addAttribute("user_family", testTeacher.getFamilyName());
        return modelAndView;
    }
    @GetMapping(value = "/tmessages")
    public ModelAndView messages(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherMessages());
        model.addAttribute("user_name", testTeacher.getFirstName());
        model.addAttribute("user_family", testTeacher.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value = "/tchange")
    public ModelAndView changeView(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm) {

        userChangeForm.setFirstName(testTeacher.getFirstName());
        userChangeForm.setFamilyName(testTeacher.getFamilyName());
        userChangeForm.setFatherName(testTeacher.getFatherName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherChange());
        model.addAttribute("user_name", testTeacher.getFirstName());
        model.addAttribute("user_family", testTeacher.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/tchange")
    public ModelAndView changePost(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherChange());
        model.addAttribute("user_name", testTeacher.getFirstName());
        model.addAttribute("user_family", testTeacher.getFamilyName());
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

    @GetMapping(value="/sendMessage")
    public ModelAndView sendMessageForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherSendMessage());
        model.addAttribute("user_name", testTeacher.getFirstName());
        model.addAttribute("user_family", testTeacher.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value="/createTask")
    public ModelAndView createTaskForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherCreateTask());
        model.addAttribute("user_name", testTeacher.getFirstName());
        model.addAttribute("user_family", testTeacher.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value="/createTask")
    public ModelAndView createTask(Model model, @ModelAttribute("kurstaskform") KursTaskForm kursTaskForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherCreateTask());
        model.addAttribute("user_name", testTeacher.getFirstName());
        model.addAttribute("user_family", testTeacher.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value = "/projectList")
    public ModelAndView projectList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherProjectList());
        model.addAttribute("user_name", testTeacher.getFirstName());
        model.addAttribute("user_family", testTeacher.getFamilyName());
        model.addAttribute("inst_student", "71201091:");
        model.addAttribute("inst_name", "Programing Language DYV-2021");
        model.addAttribute("inst_date", "20.12.2021");
        model.addAttribute("inst_mark", "Mark: 10");
        return modelAndView;
    }
}
