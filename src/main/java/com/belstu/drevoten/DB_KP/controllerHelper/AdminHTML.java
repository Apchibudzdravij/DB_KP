package com.belstu.drevoten.DB_KP.controllerHelper;

import com.belstu.drevoten.DB_KP.model.*;
import com.belstu.drevoten.DB_KP.model.DAO.AdminInfoDAO;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;

import java.util.LinkedList;
import java.util.List;

public class AdminHTML {

    public static String adminMain(Executive_AdminNoPass admin) {
        AdminInfoDAO adminInfoDAO = new AdminInfoDAO();
        MainDAO mainDAO = new MainDAO();
        List<DB_KP_Logs> generalLogsList = adminInfoDAO.getLogs(admin.getAdminID());
        List<DB_KP_Logs> errorsList = new LinkedList<>();
        List<DB_KP_Logs> actionsList = new LinkedList<>();

        generalLogsList.forEach(log -> {
            if (log.getLWhat().charAt(0) == '-')
                errorsList.add(log);
            else
                actionsList.add(log);
        });

        String response[] = {"<div id=\"admin\">\n<div id=\"hello-block\">\n<p>Welcome, " +
                admin.getFirstName() + " " + admin.getFamilyName() +
                "</p>\n" +
                "      </div>\n" +
                "      <div id=\"admin-main-body\">\n" +
                "        <div id=\"log-ok\" class=\"admin-main-body-column\">\n" +
                "          <p>Log of actions</p>\n" +
                "          <div class=\"rowable\">\n" +
                "            <button class=\"guiable\">Delete oldest 100</button>\n" +
                "            <button class=\"guiable\">Delete newest 100</button>\n" +
                "          </div>\n" +
                "          <table class=\"log-table\">\n" +
                "            <tr>\n" +
                "              <th>Who</th>\n" +
                "              <th>Did</th>\n" +
                "              <th>When</th>\n" +
                "            </tr>\n"};
        actionsList.forEach(action -> {
            response[0] +=  "<tr>\n" +
                            "  <td class=\"td-start\">" + action.getLWho() + "</td>\n" +
                            "  <td class=\"td-middle\">" + action.getLWhat() + "</td>\n" +
                            "  <td class=\"td-finish\">" + action.getLWhen() + "</td>\n" +
                            "</tr>\n";
        });

        response[0] +=
                "          </table>\n" +
                "        </div>\n" +
                "        <div id=\"log-err\" class=\"admin-main-body-column\">\n" +
                "          <p>Log of errors</p>\n" +
                "          <div class=\"rowable\">\n" +
                "            <button class=\"guiable\">Delete oldest 100</button>\n" +
                "            <button class=\"guiable\">Delete newest 100</button>\n" +
                "          </div>\n" +
                "          <table class=\"log-table\">\n" +
                "            <tr>\n" +
                "              <th>Who</th>\n" +
                "              <th>Did</th>\n" +
                "              <th>When</th>\n" +
                "            </tr>\n";
        errorsList.forEach(error -> {
            response[0] +=  "<tr>\n" +
                            "  <td class=\"td-start\">" + error.getLWho() + "</td>\n" +
                            "  <td class=\"td-middle\">" + error.getLWhat() + "</td>\n" +
                            "  <td class=\"td-finish\">" + error.getLWhen() + "</td>\n" +
                            "</tr>\n";
        });

        response[0] +=
                "          </table>\n" +
                "        </div>\n" +
                "        <div id=\"notifics\" class=\"admin-main-body-column\">\n" +
                "          <p>Notifications</p>\n";
        List<Notifications> notificationsList = mainDAO.getNotifications(admin.getAdminID(), "admin");
        if (notificationsList.size()==0)
            response[0] += "<p class=\"notification\"> * No new notifications <br/>" +
                    "<span style=\"font-style: normal\">ヾ(´〇`)ﾉ♪♪♪</span></p>\n";
        else
            notificationsList.forEach(note -> {
                response[0] += "<p class=\"notification\">" +
                        note.getContentText() +
                        "</p>\n";
            });
        response[0] += "        </div>\n" +
                "      </div>\n" +
                "    </div>";
        return response[0];
    }


    public static String adminSettings(boolean ismessageok) {
        Localizations localizations = new Localizations();
        Themes themes = new Themes();
        String[] response = {"<div id=\"admin\">\n" +
                "<div id=\"hello-block\">\n" +
                "<p>Settings</p>\n" +
                "</div>\n" +
                "<form id=\"user-settings\" method=\"POST\" action=\"asettings\" modelAttribute=\"userpropsform\">\n" +
                "<div id=\"setting-block\">\n" +
                "<div class=\"property\">\n" +
                "<div class=\"key-prop\">Language:</div>\n" +
                "<div class=\"value-prop\">\n" +
                "<select class=\"select-list\" id=\"lang\" name=\"lang\" path=\"lang\">\n"};
        localizations.getLanguageList().forEach(
                lang -> response[0] += "<option value=\"" + lang.getLetter() +
                        "\">" + lang.getName() +"</option>\n"
        );
        response[0] += "</select>\n</div>\n</div>   \n<div class=\"property\">\n" +
                "<div class=\"key-prop\">Theme:</div>\n<div class=\"value-prop\">\n" +
                "<select class=\"select-list\" id=\"theme\" name=\"theme\" path=\"color\">\n";
        themes.getThemesList().forEach(
                themeEntity -> response[0] += "<option value=\"" + themeEntity.getLetter() +
                        "\">" + themeEntity.getName() + "</option>\n"
        );
        response[0] += "</select>\n</div>\n</div>\n</div>\n<div id=\"control-save\">\n";
        if (ismessageok)
            response[0] += "new properties saved";
        else
            response[0] += "no changes";
        response[0] += "<input type=\"submit\" class=\"settingable\" value=\"Save\" />\n" +
                "</div></form>\n</div>";
        return response[0];
    }

    public static String adminChange(Executive_AdminNoPass admin, String wasChanged) {
        String response[] = {"<div id= \"admin\">\n" +
                "            <div id= \"hello-block\">\n" +
                "                <p>Change user information</p>\n" +
                "            </div>\n" +
                "            <form id= \"user-settings\" method=\"POST\" action=\"achange\" modelAttribute=\"userchangeform\">\n" +
                "                <div id= \"setting-block\">\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">First name:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"text\" name=\"firstName\" value=\""};
        response[0] += admin.getFirstName() + "\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Second Name:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"text\" name=\"familyName\" value=\"" +
                admin.getFamilyName() + "\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Father Name:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"text\" name=\"fatherName\" value=\"" +
                admin.getFatherName() + "\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Gender:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <select class=\"select-list\" name=\"gender\">\n";
        List<UserGender> userGender = List.of(UserGender.values());
        userGender.forEach(gender -> {
            if (gender == admin.getGender())
                response[0] += "<option selected=\"selected\" value=\"" + gender + "\">" + gender.name() + "</option>\n";
            else
                response[0] += "<option value=\"" + gender + "\">" + gender.name() + "</option>\n";
        });
        response[0] +=
                "                            </select>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div class= \"property\">\n" +
                        "                        <div class= \"key-prop\">New password:</div>\n" +
                        "                        <div class= \"value-prop\">\n" +
                        "                            <input type=\"password\" name=\"newPassword\" value=\"\">\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div class= \"property\">\n" +
                        "                        <div class= \"key-prop\">Confirm new password:</div>\n" +
                        "                        <div class= \"value-prop\">\n" +
                        "                            <input type=\"password\" name=\"checkNewPassword\" value=\"\">\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div class= \"property\">\n" +
                        "                        <div class= \"key-prop\">Actual password:</div>\n" +
                        "                        <div class= \"value-prop\">\n" +
                        "                            <input type=\"password\" name=\"password\" value=\"\">\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "                <div id= \"control-save\">\n" +
                        "                    <input type=\"submit\" class=\"settingable\" value=\"Save\" />\n" +
                        "                    <div>" + wasChanged + "</div>\n" +
                        "                </div>\n" +
                        "            </form>\n" +
                        "        </div>";
        return response[0];
    }

    public static String adminSendMessage(String receiver, String subject, String wasSend, String previous) {
        String response = "<div id=\"admin\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Send message</p>\n" +
                "            </div>\n" +
                "            <div id=\"ask-form\">\n" +
                "                <form method=\"POST\" action=\"aSendMessage\" id=\"ask-form-body\">\n" +
                "                    <div class=\"inlineField\">\n" +
                "                        <p id=\"to\">To: </p><input id=\"ask_receiver\" name=\"ask_receiver\" type=\"text\" value=\"" +
                receiver + "\"/>" +
                                        "<div class=\"shadow-text\"><p>[ID], \"Administrator\", \"Teacher\" or [Family name, First name, Father name]</p></div>\n" +
                "                    </div><br/>\n" +
                "                    <input id=\"ask_header\" name=\"ask_header\" type=\"text\" placeholder=\"Subject\" value=\"" +
                subject + "\" /><br/>\n" +
                "                    <textarea cols=\"4\" placeholder=\"Message\" id=\"ask_message\" name=\"ask_message\">";
        response += previous.equals("") ? "" : "->" + previous + "\n--- from " + receiver + "---\n";
        response += "</textarea><br/>\n" +
                "                    <input id=\"ask_send\" type=\"submit\" value=\"Send\"/>\n" + wasSend +
                "                </form>\n" +
                "            </div>\n" +
                "        </div>";
        return response;
    }

    public static String adminMessages(Executive_AdminNoPass admin) {
        MainDAO mainDAO = new MainDAO();
        String response[] = {"<div id=\"admin\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Messages</p>\n" +
                "            </div>\n" +
                "            <div id=\"message-list\">\n"};
        List<Messages> messagesList = mainDAO.getMessages(admin.getAdminID(), "admin");
        if (messagesList.size()==0)
            response[0] += "<div class=\"message\">\n" +
                    "                    <div class=\"message-head\">\n" +
                    "                        <div class=\"mes-sender\">*  No</div>\n" +
                    "                        <div class=\"mes-header\">new</div>\n" +
                    "                        <div class=\"mes-date-sent\">messages</div>\n" +
                    "                    </div>\n" +
                    "                </div>\n";
        else
            messagesList.forEach(note -> {
                response[0] +="<form method=\"POST\" action=\"amessages\" class=\"message\">\n<div class=\"message-head\">\n" +
                        "* <input readonly class=\"mes-sender\" name=\"sender\" value=\"" +
                        note.getSender() +
                        "\"/>:<input readonly class=\"mes-header\" name=\"subject\" value=\"" +
                        note.getSubject() +
                        "\"/><div class=\"mes-date-sent\">" +
                        note.getDateAndTime() +
                        "</div>\n</div>\n<div class=\"message-body\">\n" +
                        "<textarea readonly class=\"message-content\" name=\"message\">" +
                        note.getMessageBody() +
                        "</textarea>\n<button class=\"guiable answerable\" onclick=\"";
                response[0] +="\">Answer</button>\n</div>\n</form>\n";
            });
        response[0] +=
                "            </div>\n" +
                        "        </div>";
        return response[0];
    }

    public static String adminChangeUsers(String messageToUser) {
        return "<div id=\"admin\">\n" +
                "      <div id=\"hello-block\">\n" +
                "        <p>Change users (SI)</p>\n" +
                "      </div>\n" +
                "      <div id=\"change-user-body\">\n" +
                "        <form id=\"admin-change-student\" method=\"POST\" action=\"adminchangestudent\">\n" +
                "          <p class=\"acu-header\">Change student</p>\n" +
                "          <input class=\"acu-field\" name=\"id\"      placeholder=\"ID\"        type=\"text\"/>\n" +
                "          <input class=\"acu-field\" name=\"course\"  placeholder=\"Course\"    type=\"text\"/>\n" +
                "          <input class=\"acu-field\" name=\"subgroup\" placeholder=\"Subgroup\" type=\"text\"/>\n" +
                "          <input class=\"acu-field\" name=\"faculty\" placeholder=\"Faculty\"   type=\"text\"/>\n" +
                "          <input class=\"acu-field\" name=\"special\" placeholder=\"Special\"   type=\"text\"/>\n" +
                "          <input class=\"acu-field\" type=\"submit\" value=\"Change student!\">\n" +
                "        </form>\n" +
                "        <div class=\"rowable\" id=\"admin-change-others\">\n" +
                "          <form id=\"admin-change-teacher\" method=\"POST\" action=\"adminchangeteacher\">\n" +
                "            <p class=\"acu-header\">Change teacher</p>\n" +
                "            <input class=\"acu-field\" name=\"id\"         type=\"text\" placeholder=\"ID\"/>\n" +
                "            <input class=\"acu-field\" name=\"department\" type=\"text\" placeholder=\"Department\"/>\n" +
                "            <input class=\"acu-field\" type=\"submit\" value=\"Change teacher!\">\n" +
                "          </form>\n" +
                "          <div id=\"course-up\">\n" +
                "            <button class=\"guiable\" onclick=\"location.href='adminUpCourse'\">Up students course by 1</button>\n" +
                messageToUser +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>";
    }

    public static String adminAddUsers(String messageToUser) {

        return "<div id=\"admin\">\n" +
                "      <div id=\"hello-block\">\n" +
                "        <p>Register users</p>\n" +
                "      </div>\n" +
                "      <div class=\"rowable\">\n" +
                "        <form class=\"admin-reg\" id=\"admin-reg-student\" method=\"POST\" action=\"adminregstudent\">\n" +
                "          <p class=\"admin-reg-label\">Student</p>\n" +
                "          <input type=\"text\" name=\"id\" placeholder=\"ID\" />\n" +
                "          <input type=\"text\" name=\"firstname\" placeholder=\"First name\" />\n" +
                "          <input type=\"text\" name=\"secondname\" placeholder=\"Second name\" />\n" +
                "          <input type=\"text\" name=\"fathername\" placeholder=\"Father name\" />\n" +
                "          <input type=\"text\" name=\"special\" placeholder=\"Speciality\" />\n" +
                "          <input type=\"text\" name=\"subgroup\" placeholder=\"Subgroup\" />\n" +
                "          <input type=\"text\" name=\"course\" placeholder=\"Course\" value=\"1\" />\n" +
                "          <input type=\"submit\" value=\"Register student\">\n" +
                "        </form>\n" +
                "        <form class=\"admin-reg\" id=\"admin-reg-teacher\" method=\"POST\" action=\"adminregteacher\">\n" +
                "          <p class=\"admin-reg-label\">Teacher</p>\n" +
                "          <input type=\"text\" name=\"id\" placeholder=\"ID\" />\n" +
                "          <input type=\"text\" name=\"firstname\" placeholder=\"First name\" />\n" +
                "          <input type=\"text\" name=\"secondname\" placeholder=\"Second name\" />\n" +
                "          <input type=\"text\" name=\"fathername\" placeholder=\"Father name\" />\n" +
                "          <input type=\"text\" name=\"department\" placeholder=\"Department\" />\n" +
                "          <input type=\"submit\" value=\"Register teacher\">\n" +
                "        </form>\n" +
                "        <form class=\"admin-reg\" id=\"admin-reg-admin\" method=\"POST\" action=\"adminregadmin\">\n" +
                "          <p class=\"admin-reg-label\">Administrator</p>\n" +
                "          <input type=\"text\" name=\"id\" placeholder=\"ID\" />\n" +
                "          <input type=\"text\" name=\"firstname\" placeholder=\"First name\" />\n" +
                "          <input type=\"text\" name=\"secondname\" placeholder=\"Second name\" />\n" +
                "          <input type=\"text\" name=\"fathername\" placeholder=\"Father name\" />\n" +
                "          <input type=\"submit\" value=\"Register admin\">\n" +
                "        </form>\n" +
                "      </div>\n" +
                messageToUser +
                "    </div>";
        /* //For XML parsing
        return "<div id=\"admin\">\n" +
                "      <div id=\"hello-block\">\n" +
                "        <p>Register new users</p>\n" +
                "      </div>\n" +
                "      <form id=\"registration-body\" method=\"POST\" action=\"uploadFile\" enctype=\"multipart/form-data\">\n" +
                "          <div id=\"register-field-text\">\n" +
                messageToUser + "\n" +
                "          </div>\n" +
                "        <input type=\"file\" name=\"file\" id=\"register-field\" />\n" +
                "        <div id=\"apply-registration\">\n" +
                "          <button class=\"guiable\" id=\"register-button\">Register now!</button>\n" +
                "          <div id=\"register-error\"></div>\n" +
                "        </div>\n" +
                "      </form>\n" +
                "    </div>";*/
    }
}
