package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.controllerHelper.StudentHTML;
import com.belstu.drevoten.DB_KP.controllerHelper.TeacherHTML;
import com.belstu.drevoten.DB_KP.forms.KursTaskForm;
import com.belstu.drevoten.DB_KP.forms.UserChangeForm;
import com.belstu.drevoten.DB_KP.forms.UserPropsForm;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;
import com.belstu.drevoten.DB_KP.model.Teachers;
import com.belstu.drevoten.DB_KP.model.TeachersNoPass;
import com.belstu.drevoten.DB_KP.model.UserGender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Controller
public class TeacherController {

    static TeachersNoPass testTeachers; // = new Teachers("71201092", "Marina", "Dubovik", "Vladimirovna", UserGender.Female, "71201092", "ISiT", "E", "S");

    @GetMapping(value="/tsettings")
    public ModelAndView settings(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherSettings(testTeachers, false));
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value="/tsettings")
    public ModelAndView newSettings(Model model, @Valid @ModelAttribute("userpropsform") UserPropsForm userPropsForm,
                                    @NotNull @RequestParam("lang") String lang, @NotNull @RequestParam("theme") String theme) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        MainDAO mainDAO = new MainDAO();
        userPropsForm.setLang(lang);
        userPropsForm.setColor(theme);
        model.addAttribute("editable_content",
                TeacherHTML.teacherSettings(testTeachers, mainDAO.saveProperties(testTeachers.getTeacherID(),
                        userPropsForm.getLang(), userPropsForm.getColor(),
                        "teacher")));
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value="/teacher")
    public ModelAndView backToMain(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherMain(testTeachers));
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/tmessages")
    public ModelAndView messagesAfterSending(Model model, @RequestParam("subject") String subject,
                                             @RequestParam("sender") String sender,
                                             @RequestParam("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherSendMessage(sender,subject, "", message));
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }
    @GetMapping(value = "/tmessages")
    public ModelAndView messages(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherMessages());
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value = "/tchange")
    public ModelAndView changeView(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm) {

        userChangeForm.setFirstName(testTeachers.getFirstName());
        userChangeForm.setFamilyName(testTeachers.getFamilyName());
        userChangeForm.setFatherName(testTeachers.getFatherName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherChange());
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/tchange")
    public ModelAndView changePost(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherChange());
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
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
        model.addAttribute("editable_content", TeacherHTML.teacherSendMessage("","","",""));
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/sendMessage")
    public ModelAndView sendingAfterSending(Model model, @RequestParam("ask_receiver") String ask_receiver,
                                             @NotNull @RequestParam("ask_header") String ask_header,
                                             @NotNull @RequestParam("ask_message") String ask_message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        MainDAO mainDAO = new MainDAO();
        if (mainDAO.sendMessage(testTeachers.getTeacherID(), ask_receiver, ask_header, ask_message, "teacher"))
            model.addAttribute("editable_content", StudentHTML.studentAsk("","","Sent successfully", ""));
        else
            model.addAttribute("editable_content", StudentHTML.studentAsk("","","Was not send", ""));
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value="/createTask")
    public ModelAndView createTaskForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherCreateTask());
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value="/createTask")
    public ModelAndView createTask(Model model, @ModelAttribute("kurstaskform") KursTaskForm kursTaskForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherCreateTask());
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value = "/projectList")
    public ModelAndView projectList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherProjectList());
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        model.addAttribute("inst_student", "71201091:");
        model.addAttribute("inst_name", "Programing Language DYV-2021");
        model.addAttribute("inst_date", "20.12.2021");
        model.addAttribute("inst_mark", "Mark: 10");
        return modelAndView;
    }
}
