package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.controllerHelper.StudentHTML;
import com.belstu.drevoten.DB_KP.forms.UserChangeForm;
import com.belstu.drevoten.DB_KP.forms.UserPropsForm;
import com.belstu.drevoten.DB_KP.model.DAO.AuthDAO;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;
import com.belstu.drevoten.DB_KP.model.DAO.StudentDAO;
import com.belstu.drevoten.DB_KP.model.Students;
import com.belstu.drevoten.DB_KP.model.StudentsNoPass;
import com.belstu.drevoten.DB_KP.model.UserGender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Controller
public class StudentController {

    static StudentsNoPass testStudents;// = new StudentsNoPass("71201091", "Eugene", "Drevoten", "Vladimirovich", 2, 3, "5-2", "FIT", "POIT", "E", "S");
    MainDAO mainDAO;
    AuthDAO authDAO;

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
                                    @NotNull @RequestParam("lang") String lang, @NotNull @RequestParam("theme") String theme) {
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

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentMain(testStudents, ""));
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }
    @GetMapping(value="/askquestion")
    public ModelAndView askQuestion(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentAsk("","", "", ""));
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/askquestion")
    public ModelAndView messagesAfterSending(Model model, @RequestParam("ask_receiver") String ask_receiver,
                                             @NotNull @RequestParam("ask_header") String ask_header,
                                             @NotNull @RequestParam("ask_message") String ask_message) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        mainDAO = new MainDAO();
        if (mainDAO.sendMessage(testStudents.getStudentID(), ask_receiver, ask_header, ask_message, "student"))
            model.addAttribute("editable_content", StudentHTML.studentAsk("","","Sent successfully", ""));
        else
            model.addAttribute("editable_content", StudentHTML.studentAsk("","","Was not send", ""));
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

    @PostMapping(value = "/smessages")
    public ModelAndView answerOnMessages(Model model, @RequestParam("subject") String subject,
                                         @RequestParam("sender") String sender,
                                         @RequestParam("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentAsk(sender,subject, "", message));
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value = "/schange")
    public ModelAndView changeView(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        model.addAttribute("editable_content", StudentHTML.studentChange(testStudents, ""));
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/schange")
    public ModelAndView changePost(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm,
                                   @NotNull @RequestParam("firstName") String firstName,
                                   @NotNull @RequestParam("familyName") String familyName,
                                   @NotNull @RequestParam("fatherName") String fatherName,
                                   @NotNull @RequestParam("gender") String gender,
                                   @RequestParam("newPassword") String newPassword,
                                   @RequestParam("checkNewPassword") String checkNewPassword,
                                   @NotNull @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");

        if (!newPassword.equals("")) {
            if (!newPassword.equals(checkNewPassword)) {
                model.addAttribute("editable_content", StudentHTML.studentChange(testStudents, "The password in confirm field is not equal to the new password!"));
            } else if (newPassword.equals(password)) {
                model.addAttribute("editable_content", StudentHTML.studentChange(testStudents, "The new password cannot equals to the old password!"));
            } else {
                mainDAO = new MainDAO();
                authDAO = new AuthDAO();
                mainDAO.updateUser(testStudents.getStudentID(), firstName, familyName, fatherName, newPassword, UserGender.valueOf(gender).ordinal(), password, "student");
                StudentsNoPass tempStudent = authDAO.getStudentIfPassword(testStudents.getStudentID(), newPassword);
                if (tempStudent != null ) {
                    testStudents = tempStudent;
                    model.addAttribute("editable_content", StudentHTML.studentChange(testStudents, "User information updated!"));
                } else {
                    model.addAttribute("editable_content", StudentHTML.studentChange(testStudents, "Incorrect actual password!"));
                }
            }
        } else {
            mainDAO = new MainDAO();
            authDAO = new AuthDAO();
            mainDAO.updateUser(testStudents.getStudentID(), firstName, familyName, fatherName, password, UserGender.valueOf(gender).ordinal(), password, "student");
            StudentsNoPass tempStudent = authDAO.getStudentIfPassword(testStudents.getStudentID(), password);
            if (tempStudent != null ) {
                testStudents = tempStudent;
                model.addAttribute("editable_content", StudentHTML.studentChange(testStudents, "User information updated!"));
            } else {
                model.addAttribute("editable_content", StudentHTML.studentChange(testStudents, "Incorrect actual password!"));
            }
        }
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());

        return modelAndView;
    }

    @GetMapping(value = "/coursePlan")
    public ModelAndView coursePlan(Model model) {

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

    @PostMapping(value = "/sendtaskfile")
    public ModelAndView sendTaskFile(Model model, @RequestParam("fileout1") MultipartFile fileout) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        File byteFile = this.getFile(fileout, fileout.getOriginalFilename());
        StudentDAO studentDAO = new StudentDAO();
        if (studentDAO.sendTask(testStudents.getStudentID(), byteFile))
            model.addAttribute("editable_content", StudentHTML.studentMain(testStudents, "Task sent successful!"));
        else
            model.addAttribute("editable_content", StudentHTML.studentMain(testStudents, "Task was not send"));
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/sendarcfile")
    public ModelAndView sendArcFile(Model model, @RequestParam("fileout2") MultipartFile fileout) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        File byteFile = this.getFile(fileout, "share\\" + fileout.getOriginalFilename());
        StudentDAO studentDAO = new StudentDAO();
        if (studentDAO.sendArc(testStudents.getStudentID(), byteFile))
            model.addAttribute("editable_content", StudentHTML.studentMain(testStudents, "Archive sent successful!"));
        else
            model.addAttribute("editable_content", StudentHTML.studentMain(testStudents, "Archive was not send"));
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/sendexplfile")
    public ModelAndView sendExplFile(Model model, @RequestParam("fileout3") MultipartFile fileout) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        File byteFile = this.getFile(fileout, fileout.getOriginalFilename());
        StudentDAO studentDAO = new StudentDAO();
        if (studentDAO.sendNote(testStudents.getStudentID(), byteFile))
            model.addAttribute("editable_content", StudentHTML.studentMain(testStudents, "Note sent successful!"));
        else
            model.addAttribute("editable_content", StudentHTML.studentMain(testStudents, "Note was not send"));
        model.addAttribute("user_name", testStudents.getFirstName());
        model.addAttribute("user_family", testStudents.getFamilyName());
        return modelAndView;
    }


    private File getFile(MultipartFile multipartFile, String fileName) {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }
}
