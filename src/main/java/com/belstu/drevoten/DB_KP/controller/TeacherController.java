package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.controllerHelper.AdminHTML;
import com.belstu.drevoten.DB_KP.controllerHelper.StudentHTML;
import com.belstu.drevoten.DB_KP.controllerHelper.TeacherHTML;
import com.belstu.drevoten.DB_KP.forms.KursTaskForm;
import com.belstu.drevoten.DB_KP.forms.UserChangeForm;
import com.belstu.drevoten.DB_KP.forms.UserPropsForm;
import com.belstu.drevoten.DB_KP.model.DAO.AuthDAO;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;
import com.belstu.drevoten.DB_KP.model.DAO.TeacherDAO;
import com.belstu.drevoten.DB_KP.model.StudentsNoPass;
import com.belstu.drevoten.DB_KP.model.Teachers;
import com.belstu.drevoten.DB_KP.model.TeachersNoPass;
import com.belstu.drevoten.DB_KP.model.UserGender;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;


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
        model.addAttribute("editable_content", TeacherHTML.teacherMessages(testTeachers));
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/tchange")
    public ModelAndView changeView(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm,
                                   @NotNull @RequestParam("firstName") String firstName,
                                   @NotNull @RequestParam("familyName") String familyName,
                                   @NotNull @RequestParam("fatherName") String fatherName,
                                   @NotNull @RequestParam("gender") String gender,
                                   @RequestParam("newPassword") String newPassword,
                                   @RequestParam("checkNewPassword") String checkNewPassword,
                                   @NotNull @RequestParam("password") String password) {
        MainDAO mainDAO;
        AuthDAO authDAO;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");

        if (!newPassword.equals("")) {
            if (!newPassword.equals(checkNewPassword)) {
                model.addAttribute("editable_content", TeacherHTML.teacherChange(testTeachers, "The password in confirm field is not equal to the new password!"));
            } else if (newPassword.equals(password)) {
                model.addAttribute("editable_content", TeacherHTML.teacherChange(testTeachers, "The new password cannot equals to the old password!"));
            } else {
                mainDAO = new MainDAO();
                authDAO = new AuthDAO();
                mainDAO.updateUser(testTeachers.getTeacherID(), firstName, familyName, fatherName, newPassword, UserGender.valueOf(gender).ordinal(), password, "teacher");
                TeachersNoPass tempTeacher = authDAO.getTeacherIfPassword(testTeachers.getTeacherID(), newPassword);
                if (tempTeacher != null ) {
                    testTeachers = tempTeacher;
                    model.addAttribute("editable_content", TeacherHTML.teacherChange(testTeachers, "User information updated!"));
                } else {
                    model.addAttribute("editable_content", TeacherHTML.teacherChange(testTeachers, "Incorrect actual password!"));
                }
            }
        } else {
            mainDAO = new MainDAO();
            authDAO = new AuthDAO();
            mainDAO.updateUser(testTeachers.getTeacherID(), firstName, familyName, fatherName, password, UserGender.valueOf(gender).ordinal(), password, "teacher");
            TeachersNoPass tempTeacher = authDAO.getTeacherIfPassword(testTeachers.getTeacherID(), password);
            if (tempTeacher != null ) {
                testTeachers = tempTeacher;
                model.addAttribute("editable_content", TeacherHTML.teacherChange(testTeachers, "User information updated!"));
            } else {
                model.addAttribute("editable_content", TeacherHTML.teacherChange(testTeachers, "Incorrect actual password!"));
            }
        }

        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value = "/tchange")
    public ModelAndView changePost(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.teacherChange(testTeachers, ""));
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
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
        model.addAttribute("editable_content", TeacherHTML.teacherCreateTask(""));
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value="/createTask")
    public ModelAndView createTask(Model model, @RequestParam("ftitle") String ftitle,
                                   @RequestParam("ytitle") String ytitle,
                                   @RequestParam("course") String course,
                                   @RequestParam("faculty") String faculty,
                                   @RequestParam("speciality") String speciality,
                                   @RequestParam("subjname") String subjname,
                                   @RequestParam("goal") MultipartFile file){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        TeacherDAO teacherDAO = new TeacherDAO();
        File byteFile = this.getFile(file, file.getOriginalFilename());
        try {
            //Blob blob = Hibernate.createBlob(file.getInputStream());
            if (teacherDAO.createTask(testTeachers.getTeacherID(), ytitle, ftitle, course, faculty, speciality,
                    subjname, byteFile))
                model.addAttribute("editable_content", TeacherHTML.teacherCreateTask("Task created successfully!"));
            else
                model.addAttribute("editable_content", TeacherHTML.teacherCreateTask("Task not created"));
        } catch (Exception e) {
            model.addAttribute("editable_content", TeacherHTML.teacherCreateTask("File parsing error"));
        }
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

    @GetMapping(value = "/changeProject")
    public ModelAndView changeProject(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        model.addAttribute("editable_content", TeacherHTML.changeProject(""));
        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        /*model.addAttribute("inst_student", "71201091:");
        model.addAttribute("inst_name", "Programing Language DYV-2021");
        model.addAttribute("inst_date", "20.12.2021");
        model.addAttribute("inst_mark", "Mark: 10");*/
        return modelAndView;
    }

    @PostMapping(value = "/changenomark")
    public ModelAndView changeNoMark(Model model, @RequestParam("student") String student,
                                     @RequestParam("project") String project,
                                     @RequestParam("newteacher") String newteacher,
                                     @RequestParam("deadline") String deadline) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        TeacherDAO teacherDAO = new TeacherDAO();

        if (teacherDAO.changeCourseProjectEntity(testTeachers.getTeacherID(), deadline, newteacher, student, project))
            model.addAttribute("editable_content", TeacherHTML.changeProject("Updated successful!"));
        else
            model.addAttribute("editable_content", TeacherHTML.changeProject("Did not update"));

        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/changemark")
    public ModelAndView changeNoMark(Model model, @RequestParam("student") String student,
                                     @RequestParam("project") String project,
                                     @RequestParam("mark") String mark) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        TeacherDAO teacherDAO = new TeacherDAO();

        if (teacherDAO.markCourseProject(testTeachers.getTeacherID(), student, project, Integer.valueOf(mark)))
            model.addAttribute("editable_content", TeacherHTML.changeProject("Updated successful!"));
        else
            model.addAttribute("editable_content", TeacherHTML.changeProject("Did not update"));

        model.addAttribute("user_name", testTeachers.getFirstName());
        model.addAttribute("user_family", testTeachers.getFamilyName());
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
    private String encodeFileToBase64Binary(File file, String fileName) throws IOException {
        byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
