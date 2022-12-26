package com.belstu.drevoten.DB_KP.controllerHelper;

import com.belstu.drevoten.DB_KP.model.*;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;

import java.util.List;

public class TeacherHTML {

    public static String teacherMain(TeachersNoPass teachers)  {
        MainDAO mainDAO = new MainDAO();
        String response[] = {"<div id=\"teacher\">\n<div id=\"hello-block\">\n<p>Welcome, " +
                teachers.getFirstName() + " " + teachers.getFamilyName() +
                "</p>\n</div>\n<div id=\"main-teacher-body\">\n<div id=\"project-acceptance\">\n" +
                " <div class=\"project-acceptance-header\">Acceptance of course projects</div>\n"};
        response[0] +=
                "<div class=\"acceptance-step\">\n" +
                "   <div class=\"project-name-instance\">Development of Programming language</div>\n" +
                "   <div class=\"project-date-instance\">09.12.2022</div>\n" +
                "</div>\n" +
                "<div class=\"acceptance-step\">\n" +
                "   <div class=\"project-name-instance\">Development of Programming language</div>\n" +
                "   <div class=\"project-date-instance\">10.12.2022</div>\n" +
                "</div>\n" +
                "<div class=\"acceptance-step\">\n" +
                "   <div class=\"project-name-instance\">Development of Programming language</div>\n" +
                "   <div class=\"project-date-instance\">12.12.2022</div>\n" +
                "</div>\n";
        response[0] +=
                "</div>\n<div id=\"notifics\">\n\t<p class=\"notification-header\">Notifications</p>";
        List<Notifications> notificationsList = mainDAO.getNotifications(teachers.getTeacherID(), "teacher");
        if (notificationsList.size()==0)
            response[0] += "<p class=\"notification\"> * No new notifications " +
                    "<span style=\"font-style: normal\">ヽ(°〇°)ﾉ</span></p>\n";
        else
            notificationsList.forEach(note -> {
                response[0] += "<p class=\"notification\">" +
                        note.getContentText() +
                        "</p>\n";
            });
        response[0] +=
                "</div>\n</div>\n</div>\n";
        return response[0];
    }

    public static String teacherSettings(TeachersNoPass teacher, boolean ismessageok) {
        Localizations localizations = new Localizations();
        Themes themes = new Themes();
        String[] response = {"<div id=\"teacher\">\n" +
                "<div id=\"hello-block\">\n" +
                "<p>Settings</p>\n" +
                "</div>\n" +
                "<form id=\"user-settings\" method=\"POST\" action=\"tsettings\" modelAttribute=\"userpropsform\">\n" +
                "<div id=\"setting-block\">\n" +
                "<div class=\"property\">\n" +
                "<div class=\"key-prop\">Language:</div>\n" +
                "<div class=\"value-prop\">\n" +
                "<select class=\"select-list\" id=\"lang\" name=\"lang\" path=\"lang\">\n"};
        localizations.getLanguageList().forEach(lang -> {
            if (lang.getLetter().equals((teacher.getUserLanguage())))
                response[0] += "<option selected=\"selected\" value=\"" + lang.getLetter() +
                        "\">" + lang.getName() +"</option>\n";
            else
                response[0] += "<option value=\"" + lang.getLetter() +
                        "\">" + lang.getName() +"</option>\n";
            }
        );
        response[0] += "</select>\n</div>\n</div>   \n<div class=\"property\">\n" +
                "<div class=\"key-prop\">Theme:</div>\n<div class=\"value-prop\">\n" +
                "<select class=\"select-list\" id=\"theme\" name=\"theme\" path=\"color\">\n";
        themes.getThemesList().forEach(themeEntity -> {
            if (themeEntity.getLetter().equals(teacher.getUserTheme()))
                response[0] += "<option selected=\"selected\" value=\"" + themeEntity.getLetter() +
                            "\">" + themeEntity.getName() + "</option>\n";
            else
                response[0] += "<option value=\"" + themeEntity.getLetter() +
                            "\">" + themeEntity.getName() + "</option>\n";
            }
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

    public static String teacherMessages(TeachersNoPass teacher) {
        MainDAO mainDAO = new MainDAO();
        final String[] response = {"<div id=\"teacher\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Messages</p>\n" +
                "            </div>\n" +
                "            <div id=\"message-list\">\n"};
        List<Messages> messagesList = mainDAO.getMessages(teacher.getTeacherID(), "teacher");
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
                response[0] +="<form method=\"POST\" action=\"tmessages\" class=\"message\">\n<div class=\"message-head\">\n" +
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

    public static String teacherChange(TeachersNoPass teacher, String wasChanged) {
        String response[] = {"<div id= \"teacher\">\n" +
                "            <div id= \"hello-block\">\n" +
                "                <p>Change user information</p>\n" +
                "            </div>\n" +
                "            <form id= \"user-settings\" method=\"POST\" action=\"tchange\" modelAttribute=\"userchangeform\">\n" +
                "                <div id= \"setting-block\">\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">First name:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"text\" name=\"firstName\" value=\""};
        response[0] += teacher.getFirstName() + "\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Second Name:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"text\" name=\"familyName\" value=\"" +
                teacher.getFamilyName() + "\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Father Name:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"text\" name=\"fatherName\" value=\"" +
                teacher.getFatherName() + "\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Gender:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <select class=\"select-list\" name=\"gender\">\n";
        List<UserGender> userGender = List.of(UserGender.values());
        userGender.forEach(gender -> {
            if (gender == teacher.getGender())
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

    public static String teacherSendMessage(String receiver, String subject, String wasSend, String previous) {
        String response = "<div id=\"teacher\">\n" +
                "<div id=\"hello-block\">\n" +
                "<p>Ask question</p>\n" +
                "</div>\n" +
                "<div id=\"ask-form\">\n" +
                "<form method=\"POST\" action=\"sendMessage\" id=\"ask-form-body\">\n" +
                "<div class=\"inlineField\">\n" +
                "<p id=\"to\">To: </p><input id=\"ask_receiver\" name=\"ask_receiver\" type=\"text\" value=\"" +
                receiver + "\" />" +
                "<div class=\"shadow-text\"><p>[ID], \"Administrator\", \"Teacher\" or [Family name, First name, Father name]</p></div>\n" +
                "</div><br/>\n" +
                "<input id=\"ask_header\" name=\"ask_header\" type=\"text\" placeholder=\"Subject\" value=\"" +
                subject +  "\" /><br/>\n" +
                "<textarea cols=\"4\" placeholder=\"Message\" id=\"ask_message\" name=\"ask_message\">";
        response += previous.equals("") ? "" : "->" + previous + "\n--- from " + receiver + "---\n";
        response += "</textarea><br/>\n" +
                "<input id=\"ask_send\" type=\"submit\" value=\"Send\"/>\n" + wasSend +
                "</form>\n" +
                "</div>\n" +
                "</div>";
        return response;
    }

    public static String teacherCreateTask(String message) {
        return "<div id=\"teacher\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Create new course project</p>\n" +
                "            </div>\n" +
                "            <div id=\"create-form\">\n" +
                "                <form method=\"POST\" action=\"createTask\" enctype=\"multipart/form-data\">\n" +
                "                    <p>Enter project information</p>\n" +
                "                    <div id=\"new-project-name\">\n" +
                "                        <input class=\"new-project-input longer\" type=\"text\" name=\"ftitle\" placeholder=\"Full title\">\n" +
                "                        <input class=\"new-project-input shorter\" type=\"text\" name=\"ytitle\" placeholder=\"Year\">\n" +
                "                    </div>\n" +
                "                    <div id=\"target-description\">\n" +
                "                        <input class=\"new-project-input shorter\" type=\"number\" name=\"course\" placeholder=\"Study year (1-4)\"/>\n" +
                "                        <input class=\"new-project-input longer\" type=\"text\" name=\"faculty\" placeholder=\"Faculty (FIT)\"/>\n" +
                "                        <input class=\"new-project-input longer\" type=\"text\" name=\"speciality\" placeholder=\"Speciality (POIT)\"/>\n" +
                "                    </div>\n" +
                "                    <input class=\"new-project-input\" id=\"create-form-subject-name\" type=\"text\" name=\"subjname\" placeholder=\"Subject\"/>\n" +
                "                    <div id=\"create-drag-drop\">\n" +
                "                        <input type=\"file\" name=\"goal\"/>\n" +
                "                    </div>\n" +
                "                    <input class=\"guiable\" type=\"submit\" value=\"Create new task!\"/>\n" +
                message +
                "                </form>\n" +
                "            </div>\n" +
                "        </div>";
    }

    public static String teacherProjectList() {
        return "<div id=\"teacher\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Student projects</p>\n" +
                "            </div>\n" +
                "            <div id=\"student-project-list\">\n" +
                "                <id class=\"student-course-project-instance\">\n" +
                "                    <div class=\"rowable\">\n" +
                "                        <div class=\"student-course-project-instance-student\"\n" +
                "                            th:utext=\"${inst_student}\">\n" +
                "                        </div>\n" +
                "                        <div class=\"student-course-project-instance-title\"\n" +
                "                             th:utext=\"${inst_name}\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"rowable\">\n" +
                "                        <div class=\"student-course-project-instance-file\">\n" +
                "                            No task file\n" +
                "                        </div>\n" +
                "                        <div class=\"student-course-project-instance-file\">\n" +
                "                            No note file\n" +
                "                        </div>\n" +
                "                        <div class=\"student-course-project-instance-file\">\n" +
                "                            No archive file\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"rowable\">\n" +
                "                        <div class=\"student-course-project-instance-date\"\n" +
                "                             th:utext=\"${inst_date}\">\n" +
                "                        </div>\n" +
                "                        <div class=\"student-course-project-instance-mark\"\n" +
                "                             th:utext=\"${inst_mark}\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </id>\n" +
                "                <id class=\"student-course-project-instance\">\n" +
                "                    <div class=\"rowable\">\n" +
                "                        <div class=\"student-course-project-instance-student\"\n" +
                "                             th:utext=\"${inst_student}\">\n" +
                "                        </div>\n" +
                "                        <div class=\"student-course-project-instance-title\"\n" +
                "                             th:utext=\"${inst_name}\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"rowable\">\n" +
                "                        <div class=\"student-course-project-instance-file\">\n" +
                "                            No task file\n" +
                "                        </div>\n" +
                "                        <div class=\"student-course-project-instance-file\">\n" +
                "                            No note file\n" +
                "                        </div>\n" +
                "                        <div class=\"student-course-project-instance-file\">\n" +
                "                            No archive file\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"rowable\">\n" +
                "                        <div class=\"student-course-project-instance-date\"\n" +
                "                             th:utext=\"${inst_date}\">\n" +
                "                        </div>\n" +
                "                        <div class=\"student-course-project-instance-mark\"\n" +
                "                             th:utext=\"${inst_mark}\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </id>\n" +
                "                <id class=\"student-course-project-instance\">\n" +
                "                    <div class=\"rowable\">\n" +
                "                        <div class=\"student-course-project-instance-student\"\n" +
                "                             th:utext=\"${inst_student}\">\n" +
                "                        </div>\n" +
                "                        <div class=\"student-course-project-instance-title\"\n" +
                "                             th:utext=\"${inst_name}\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"rowable\">\n" +
                "                        <div class=\"student-course-project-instance-file\">\n" +
                "                            No task file\n" +
                "                        </div>\n" +
                "                        <div class=\"student-course-project-instance-file\">\n" +
                "                            No note file\n" +
                "                        </div>\n" +
                "                        <div class=\"student-course-project-instance-file\">\n" +
                "                            No archive file\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"rowable\">\n" +
                "                        <div class=\"student-course-project-instance-date\"\n" +
                "                             th:utext=\"${inst_date}\">\n" +
                "                        </div>\n" +
                "                        <div class=\"student-course-project-instance-mark\"\n" +
                "                             th:utext=\"${inst_mark}\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </id>\n" +
                "            </div>\n" +
                "        </div>";
    }

    public static String changeProject(String messageToUser) {
        return "<div id=\"teacher\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Create new course project</p>\n" +
                "            </div>\n" +
                "            <div class=\"rowable twoform\">\n" +
                "                <form class=\"project-change-form\" method=\"POST\" action=\"changenomark\">\n" +
                "                    <p>Change teacher and deadline</p>\n" +
                "                    <input type=\"text\" name=\"student\" placeholder=\"Student ID\" />\n" +
                "                    <input type=\"text\" name=\"project\" placeholder=\"Project name\" />\n" +
                "                    <input type=\"text\" name=\"newteacher\" placeholder=\"ID of a new teacher\" />\n" +
                "                    <input type=\"date\" name=\"deadline\" placeholder=\"Deadline date\" />\n" +
                "                    <input type=\"submit\" value=\"Update this project\" />\n" +
                "                </form>\n" +
                "                <form class=\"project-change-form\" method=\"POST\" action=\"changemark\">\n" +
                "                    <p>Mark project</p>\n" +
                "                    <input type=\"text\" name=\"student\" placeholder=\"Student ID\" />\n" +
                "                    <input type=\"text\" name=\"project\" placeholder=\"Project name\" />\n" +
                "                    <input type=\"text\" name=\"mark\" placeholder=\"Mark\" />\n" +
                "                    <input type=\"submit\" value=\"Mark this project\" />\n" +
                "                </form>\n" +
                "            </div>\n" +
                messageToUser + "\n" +
                "        </div>";
    }
}
