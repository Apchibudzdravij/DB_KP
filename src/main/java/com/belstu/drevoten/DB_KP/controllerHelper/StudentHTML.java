package com.belstu.drevoten.DB_KP.controllerHelper;

import com.belstu.drevoten.DB_KP.model.*;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;

import java.util.List;

public class StudentHTML {

    private static MainDAO mainDAO;
    public static String studentMain(StudentsNoPass students) {
        mainDAO = new MainDAO();
        final String[] response = {"<div id=\"student\">\n<div id=\"hello-block\">\n<p>Welcome, " +
                students.getFirstName() + " " + students.getFamilyName() +
                "</p>\n</div>\n<div id=\"user-stats\">\n<div id=\"calendar-plan\">\n" +
                "<p class=\"calendar-plan-header\">Calendar plan of current project</p>\n"};
        response[0] +=
                "                    <!-- TODO foreach of these plan -->\n" +
                "                    <div class=\"step-of-plan\">\n" +
                "                        <p class=\"date-in-plan\">13.10.2022</p>\n" +
                "                        <input type=\"checkbox\" class=\"step-of-project\" value=\"Step from user list #1\">\n" +
                "                            Analytical review of literature on the topic of the project<hr/>\n" +
                "                    </div>\n" +
                "                    <div class=\"step-of-plan\">\n" +
                "                        <p class=\"date-in-plan\">28.10.2022</p>\n" +
                "                         <input type=\"checkbox\" class=\"step-of-project\" value=\"Step from user list #3\">\n" +
                "                            Creating the necessary objects<hr/>\n" +
                "                    </div>\n" +
                "                    <div class=\"step-of-plan\">\n" +
                "                        <p class=\"date-in-plan\">30.11.2022</p>\n" +
                "                        <input type=\"checkbox\" class=\"step-of-project\" value=\"Step from user list #2\">\n" +
                "                            Project handover<hr/>\n" +
                "                    </div>\n";
        response[0] +=
                "</div>\n<div id=\"general-stats\">\n<div id=\"uniqueness\">\n" +
                "                        <!-- TODO auto calculating of % of uniqueness-->\n" +
                "                        <p class=\"ready-percent\">You did not send explanatory note!</p>\n" +
                                        "<button class=\"guiable\">Send!</button>" +
                "</div>\n<div id=\"notifics\">\n<p class=\"notification-header\">Notifications</p>\n";
        List<Notifications> notificationsList = mainDAO.getNotifications(students.getStudentID(), "student");
        if (notificationsList.size()==0)
            response[0] += "<p class=\"notification\"> * No new notifications " +
                    "<span style=\"font-style: normal\">♡( ◡‿◡ )</span></p>\n";
        else
            notificationsList.forEach(note -> {
                response[0] += "<p class=\"notification\">" +
                                note.getContentText() +
                                "</p>\n";
            });
        response[0] +=
                "</div>\n</div>\n</div>\n</div>";
        return response[0];
    }

    public static String studentSettings(boolean ismessageok) {
        Localizations localizations = new Localizations();
        Themes themes = new Themes();
        String[] response = {"<div id=\"student\">\n" +
                            "<div id=\"hello-block\">\n" +
                                "<p>Settings</p>\n" +
                            "</div>\n" +
                            "<form id=\"user-settings\" method=\"POST\" action=\"ssettings\" modelAttribute=\"userpropsform\">\n" +
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

    public static String studentAsk(String receiver, String subject, String wasSend, String previous) {
        String response = "<div id=\"student\">\n" +
                            "<div id=\"hello-block\">\n" +
                                "<p>Ask question</p>\n" +
                            "</div>\n" +
                            "<div id=\"ask-form\">\n" +
                                "<form method=\"POST\" action=\"askquestion\" id=\"ask-form-body\">\n" +
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

    public static String studentMessages(StudentsNoPass students){
        mainDAO = new MainDAO();
        final String[] response = {"<div id=\"student\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Messages</p>\n" +
                "            </div>\n" +
                "            <div id=\"message-list\">\n"};
        List<Messages> messagesList = mainDAO.getMessages(students.getStudentID(), "student");
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
                response[0] +="<form method=\"POST\" action=\"smessages\" class=\"message\">\n<div class=\"message-head\">\n" +
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

    public static String studentChange(StudentsNoPass students, String wasChanged) {
        String response[] = {"<div id= \"student\">\n" +
                "            <div id= \"hello-block\">\n" +
                "                <p>Change user information</p>\n" +
                "            </div>\n" +
                "            <form id= \"user-settings\" method=\"POST\" action=\"schange\" modelAttribute=\"userchangeform\">\n" +
                "                <div id= \"setting-block\">\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">First name:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"text\" name=\"firstName\" value=\""};
        response[0] += students.getFirstName() + "\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Second Name:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"text\" name=\"familyName\" value=\"" +
                                                students.getFamilyName() + "\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Father Name:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"text\" name=\"fatherName\" value=\"" +
                                                students.getFatherName() + "\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Gender:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <select class=\"select-list\" name=\"gender\">\n";
        List<UserGender> userGender = List.of(UserGender.values());
        userGender.forEach(gender -> {
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

    public static String studentCoursePlan(){
        return "<div id= \"student\">\n" +
                "            <div id= \"hello-block\">\n" +
                "                <p>Course plan</p>\n" +
                "            </div>\n" +
                "            <div id=\"course-body\">\n" +
                "                <div id=\"project-list\">\n" +
                "                    <div id=\"course-projects\">\n" +
                "                        <p class=\"list-title\">Projects</p>\n" +
                "                        <div class=\"project-instance\">\n" +
                "                            <div class=\"project-name-instance\">Development of Programming language</div>\n" +
                "                            <div class=\"project-date-instance\">20.12.2021</div>\n" +
                "                        </div>\n" +
                "                        <div class=\"project-instance\">\n" +
                "                            <div class=\"project-name-instance\">Desktop WPF application</div>\n" +
                "                            <div class=\"project-date-instance\">25.05.2022</div>\n" +
                "                        </div>\n" +
                "                        <div class=\"project-instance blue\">\n" +
                "                            <div class=\"project-name-instance\">Oracle Database</div>\n" +
                "                            <div class=\"project-date-instance\">08.12.2022</div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div id=\"course-projects-sum\">\n" +
                "                        <p id=\"sum_title_text\">Total amount of course projects:</p>\n" +
                "                        <div id=\"projects-amount\" th:utext=\"${project_amount}\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div id=\"statistics\">\n" +
                "                    <div id=\"course-projects-general-statistics\">\n" +
                "                        <div class=\"list-title actual-project-title\">Actual project</div>\n" +
                "                        <div id=\"general-statistics-step-submit\" th:utext=\"${task_submitted}\"></div>\n" +
                "                        <div class=\"general-statistics-step\">\n" +
                "                            <p>Steps completed: </p>\n" +
                "                            <p class=\"bigger\" th:utext=\"${steps_completed}\"></p>\n" +
                "                        </div>\n" +
                "                        <div class=\"general-statistics-step\">\n" +
                "                            <p>Days before completion: </p>\n" +
                "                            <p class=\"bigger\" th:utext=\"${days_left}\"></p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div id=\"course-projects-general-unique\">\n" +
                "                        <p class=\"uniqueness_title_text\">Average uniqueness of your course projects:</p>\n" +
                "                        <div>\n" +
                "                            <div id=\"uniqueness-value\" th:utext=\"${uniqueness_value}\"></div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";
    }

    public static String studentMyProjects() {
        return "<div id= \"student\">\n" +
                "            <div id= \"hello-block\">\n" +
                "                <p>My projects <span id=\"kaomoji-my-project\">( ˘▽˘)っ♨</span></p>\n" +
                "            </div>\n" +
                "            <div id=\"my-projects-body\">\n" +
                "                <div id=\"my-project-list\"><!--TABLE!-->\n" +
                "                    <table id=\"my-project-table\">\n" +
                "                        <tr>\n" +
                "                            <th>Subject</th>\n" +
                "                            <th>Date</th>\n" +
                "                            <th>Uniq.</th>\n" +
                "                            <th>Mark</th>\n" +
                "                        </tr>\n" +
                "                        <tr class=\"tr-content\">\n" +
                "                            <td class=\"td-start\">PL</td>\n" +
                "                            <td class=\"td-middle\">20.12.2021</td>\n" +
                "                            <td class=\"td-middle\">100</td>\n" +
                "                            <td class=\"td-finish\">10</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td class=\"td-start\">MTPiI</td>\n" +
                "                            <td class=\"td-middle\">25.05.2022</td>\n" +
                "                            <td class=\"td-middle\">100</td>\n" +
                "                            <td class=\"td-finish\">8</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td class=\"td-start\">DB</td>\n" +
                "                            <td class=\"td-middle\">08.12.2022</td>\n" +
                "                            <td class=\"td-middle\"></td>\n" +
                "                            <td class=\"td-finish\"></td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </div>\n" +
                "                <div id=\"my-project-statistics\">\n" +
                "                    <div id=\"my-projects-general-statistics\">\n" +
                "                        <div class=\"general-statistics-step my-statistics-step\">\n" +
                "                            <p>Average mark: </p>\n" +
                "                            <p class=\"bigger\" th:utext=\"${average_mark}\"></p>\n" +
                "                        </div>\n" +
                "                        <div class=\"general-statistics-step my-statistics-step\">\n" +
                "                            <p>Average uniqueness: </p>\n" +
                "                            <p class=\"bigger\" th:utext=\"${average_uniqueness}\"></p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div id=\"my-projects-mark-graph\">\n" +
                "                        <!--<p class=\"uniqueness_title_text\">Average uniqueness of your course projects:</p>-->\n" +
                "                        <\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";
    }
}
