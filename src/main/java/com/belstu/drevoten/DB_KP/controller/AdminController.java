package com.belstu.drevoten.DB_KP.controller;

import com.belstu.drevoten.DB_KP.controllerHelper.AdminHTML;
import com.belstu.drevoten.DB_KP.controllerHelper.StudentHTML;
import com.belstu.drevoten.DB_KP.forms.UserChangeForm;
import com.belstu.drevoten.DB_KP.forms.UserPropsForm;
import com.belstu.drevoten.DB_KP.model.DAO.AuthDAO;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;
import com.belstu.drevoten.DB_KP.model.Executive_Admin;
import com.belstu.drevoten.DB_KP.model.Executive_AdminNoPass;
import com.belstu.drevoten.DB_KP.model.StudentsNoPass;
import com.belstu.drevoten.DB_KP.model.UserGender;
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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


@Controller
public class AdminController {

    static Executive_AdminNoPass testAdmin;

    //Executive_Admin testExecuteAdmin = new Executive_Admin("71201093", "Anna", "Anna", "Anna", UserGender.Gender_Fluid, "71201093", 0);

    @GetMapping(value="/asettings")
    public ModelAndView settings(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminSettings(false));
        model.addAttribute("user_name", testAdmin.getFirstName());
        model.addAttribute("user_family", testAdmin.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value="/asettings")
    public ModelAndView newSettings(Model model, @Valid @ModelAttribute("userpropsform") UserPropsForm userPropsForm,
                                    @NotNull @RequestParam("lang") String lang, @NotNull @RequestParam("theme") String theme) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        MainDAO mainDAO;
        mainDAO = new MainDAO();
        userPropsForm.setLang(lang);
        userPropsForm.setColor(theme);
        model.addAttribute("editable_content",
                AdminHTML.adminSettings(mainDAO.saveProperties(testAdmin.getAdminID(),
                        userPropsForm.getLang(), userPropsForm.getColor(),
                        "admin")));
        model.addAttribute("user_name", testAdmin.getFirstName());
        model.addAttribute("user_family", testAdmin.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value="/administrator")
    public ModelAndView backToMain(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminMain(testAdmin));
        model.addAttribute("user_name", testAdmin.getFirstName());
        model.addAttribute("user_family", testAdmin.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/achange")
    public ModelAndView changePost(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm,
                                   @NotNull @RequestParam("firstName") String firstName,
                                   @NotNull @RequestParam("familyName") String familyName,
                                   @NotNull @RequestParam("fatherName") String fatherName,
                                   @NotNull @RequestParam("gender") String gender,
                                   @RequestParam("newPassword") String newPassword,
                                   @RequestParam("checkNewPassword") String checkNewPassword,
                                   @NotNull @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        MainDAO mainDAO;
        AuthDAO authDAO;

        if (!newPassword.equals("")) {
            if (!newPassword.equals(checkNewPassword)) {
                model.addAttribute("editable_content", AdminHTML.adminChange(testAdmin, "The password in confirm field is not equal to the new password!"));
            } else if (newPassword.equals(password)) {
                model.addAttribute("editable_content", AdminHTML.adminChange(testAdmin, "The new password cannot equals to the old password!"));
            } else {
                mainDAO = new MainDAO();
                authDAO = new AuthDAO();
                mainDAO.updateUser(testAdmin.getAdminID(), firstName, familyName, fatherName, newPassword, UserGender.valueOf(gender).ordinal(), password, "admin");
                Executive_AdminNoPass tempStudent = authDAO.getAdminIfPassword(testAdmin.getAdminID(), newPassword);
                if (tempStudent != null ) {
                    testAdmin = tempStudent;
                    model.addAttribute("editable_content", AdminHTML.adminChange(testAdmin, "User information updated!"));
                } else {
                    model.addAttribute("editable_content", AdminHTML.adminChange(testAdmin, "Incorrect actual password!"));
                }
            }
        } else {
            mainDAO = new MainDAO();
            authDAO = new AuthDAO();
            mainDAO.updateUser(testAdmin.getAdminID(), firstName, familyName, fatherName, password, UserGender.valueOf(gender).ordinal(), password, "student");
            Executive_AdminNoPass tempStudent = authDAO.getAdminIfPassword(testAdmin.getAdminID(), password);
            if (tempStudent != null ) {
                testAdmin = tempStudent;
                model.addAttribute("editable_content", AdminHTML.adminChange(testAdmin, "User information updated!"));
            } else {
                model.addAttribute("editable_content", AdminHTML.adminChange(testAdmin, "Incorrect actual password!"));
            }
        }
        model.addAttribute("user_name", testAdmin.getFirstName());
        model.addAttribute("user_family", testAdmin.getFamilyName());

        return modelAndView;
    }

    @GetMapping(value = "/achange")
    public ModelAndView adminChangeForm(Model model, @ModelAttribute("userchangeform") UserChangeForm userChangeForm) {

        userChangeForm.setFirstName(testAdmin.getFirstName());
        userChangeForm.setFamilyName(testAdmin.getFamilyName());
        userChangeForm.setFatherName(testAdmin.getFatherName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminChange(testAdmin, ""));
        model.addAttribute("user_name", testAdmin.getFirstName());
        model.addAttribute("user_family", testAdmin.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value="/aSendMessage")
    public ModelAndView sendMessageForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminSendMessage("","","", ""));
        model.addAttribute("user_name", testAdmin.getFirstName());
        model.addAttribute("user_family", testAdmin.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value="/aSendMessage")
    public ModelAndView sendMessageFromForm(Model model, @RequestParam("ask_receiver") String ask_receiver,
                                    @NotNull @RequestParam("ask_header") String ask_header,
                                    @NotNull @RequestParam("ask_message") String ask_message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        MainDAO mainDAO = new MainDAO();
        if (mainDAO.sendMessage(testAdmin.getAdminID(), ask_receiver, ask_header, ask_message, "admin"))
            model.addAttribute("editable_content", AdminHTML.adminSendMessage("","","Sent successfully", ""));
        else
            model.addAttribute("editable_content", AdminHTML.adminSendMessage("","","Was not send", ""));
        model.addAttribute("user_name", testAdmin.getFirstName());
        model.addAttribute("user_family", testAdmin.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/amessages")
    public ModelAndView messagesAfterSending(Model model, @RequestParam("subject") String subject,
                                             @RequestParam("sender") String sender,
                                             @RequestParam("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminSendMessage(sender, subject, "", message));
        model.addAttribute("user_name", testAdmin.getFirstName());
        model.addAttribute("user_family", testAdmin.getFamilyName());
        return modelAndView;
    }
    @GetMapping(value = "/amessages")
    public ModelAndView messages(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminMessages(testAdmin));
        model.addAttribute("user_name", testAdmin.getFirstName());
        model.addAttribute("user_family", testAdmin.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value = "/addUsers")
    public ModelAndView addUsers(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminAddUsers("Drag and drop XML file below"));
        model.addAttribute("user_name", testAdmin.getFirstName());
        model.addAttribute("user_family", testAdmin.getFamilyName());
        return modelAndView;
    }

    @GetMapping(value = "/adminChangeUsers")
    public ModelAndView adminChangeUsers(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");
        model.addAttribute("editable_content", AdminHTML.adminChangeUsers());
        model.addAttribute("user_name", testAdmin.getFirstName());
        model.addAttribute("user_family", testAdmin.getFamilyName());
        return modelAndView;
    }

    @PostMapping(value = "/uploadFile")
    public ModelAndView getXMLFile(Model model, @RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administrator");

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                File serverFile = new File("src\\main\\resources\\files"
                        + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                System.out.println("\nServer File Location=" + serverFile.getAbsolutePath());

                model.addAttribute("editable_content", AdminHTML.adminAddUsers("File successfully uploaded"));
                System.out.println("->File " + file.getOriginalFilename() + " uploaded!");
            } catch (Exception e) {
                model.addAttribute("editable_content", AdminHTML.adminAddUsers("Failed to upload: parsing error"));
                System.err.println("Failed to upload: error in try body: " + e.getMessage());
            }
        } else {
            model.addAttribute("editable_content", AdminHTML.adminAddUsers("Failed to upload: empty file"));
            System.err.println("Failed to upload: the file was empty");
        }

        model.addAttribute("user_name", testAdmin.getFirstName());
        model.addAttribute("user_family", testAdmin.getFamilyName());
        return modelAndView;
    }
}
