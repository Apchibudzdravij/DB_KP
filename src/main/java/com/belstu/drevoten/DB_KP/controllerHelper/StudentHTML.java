package com.belstu.drevoten.DB_KP.controllerHelper;

import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;
import com.belstu.drevoten.DB_KP.model.Messages;
import com.belstu.drevoten.DB_KP.model.Notifications;
import com.belstu.drevoten.DB_KP.model.StudentsNoPass;

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
                                "</span></p>\n";
            });
        response[0] +=
                "</div>\n</div>\n</div>\n</div>";
        return response[0];
    }

    public static String studentSettings() {
        return "<div id=\"student\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Settings</p>\n" +
                "            </div>\n" +
                "            <div id=\"user-settings\">\n" +
                "                <div id=\"setting-block\">\n" +
                "                    <div class=\"property\">\n" +
                "                        <div class=\"key-prop\">Theme:</div>\n" +
                "                        <div class=\"value-prop\">\n" +
                "                            <select class=\"select-list\">\n" +
                "                                <option value=\"Standard_Classic\">Standard Classic</option>\n" +
                "                            </select>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"property\">\n" +
                "                        <div class=\"key-prop\">Language:</div>\n" +
                "                        <div class=\"value-prop\">\n" +
                "                            <select class=\"select-list\">\n" +
                "                                <option value=\"english\">English</option>\n" +
                "                            </select>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div id=\"control-save\">\n" +
                "                    <button class=\"settingable\" value=\"Default\">Default</button>\n" +
                "                    <button class=\"settingable\" value=\"Save\">Save</button>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";
    }

    public static String studentAsk() {
        return "<div id=\"student\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Ask question</p>\n" +
                "            </div>\n" +
                "            <div id=\"ask-form\">\n" +
                "                <form th:method=\"POST\" th:action=\"@{/smessages}\" id=\"ask-form-body\">\n" +
                "                    <div class=\"inlineField\">\n" +
                "                        <p id=\"to\">To: </p><input id=\"ask_receiver\" type=\"text\" /><div class=\"shadow-text\"><p>[ID], \"Administrator\", \"Teacher\" or [Family name, First name, Father name]</p></div>\n" +
                "                    </div><br/>\n" +
                "                    <input id=\"ask_header\" type=\"text\" placeholder=\"Header\" /><br/>\n" +
                "                    <textarea cols=\"4\" placeholder=\"Message\" id=\"ask_message\"></textarea><br/>\n" +
                "                    <input id=\"ask_send\" type=\"submit\" value=\"Send\"/>\n" +
                "                </form>\n" +
                "            </div>\n" +
                "        </div>";
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
                response[0] +="<div class=\"message\">\n<div class=\"message-head\">\n" +
                              "<div class=\"mes-sender\">*  " +
                        note.getSender() +
                        ":</div>\n<div class=\"mes-header\">" +
                        note.getSubject() +
                        "</div>\n<div class=\"mes-date-sent\">" +
                        note.getDateAndTime() +
                        "</div>\n</div>\n<div class=\"message-body\">\n" +
                        "<textarea readonly class=\"message-content\">" +
                        note.getMessageBody() +
                        "</textarea>\n<button class=\"guiable answerable\" onclick=\"";
                ///TODO onclick answer
                response[0] +="\">Answer</button>\n</div>\n</div>\n";
            });

        response[0] +=
                "            </div>\n" +
                "        </div>";
        return response[0];
    }

    public static String studentChange() {
        return "<div id= \"student\">\n" +
                "            <div id= \"hello-block\">\n" +
                "                <p>Change user information</p>\n" +
                "            </div>\n" +
                "            <form id= \"user-settings\" method=\"POST\" th:action=\"@{schange}\" th:object=\"${userchangeform}\">\n" +
                "                <div id= \"setting-block\">\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">First name:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"text\" id=\"firstName\" th:field=\"*{firstName}\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Second Name:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"text\" id=\"familyName\" th:field=\"*{familyName}\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Father Name:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"text\" id=\"fatherName\" th:field=\"*{fatherName}\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Gender:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <select class= \"select-list\" th:field=\"*{gender}\">\n" +
                "                                <option value= \"english\">Male</option>\n" +
                "                                <option value= \"english\">Assault helicopter</option>\n" +
                "                            </select>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">New password:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"password\" id=\"newPassword\" th:field=\"*{newPassword}\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Confirm new password:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"password\" id=\"checkNewPassword\" th:field=\"*{checkNewPassword}\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class= \"property\">\n" +
                "                        <div class= \"key-prop\">Actual password:</div>\n" +
                "                        <div class= \"value-prop\">\n" +
                "                            <input type=\"password\" id=\"password\" th:field=\"*{password}\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div id= \"control-save\">\n" +
                "                    <input type=\"submit\" class=\"settingable\" value=\"Save\" />\n" +
                "                    <div th:if=\"${event}\" th:utext=\"${event}\"></div>\n" +
                "                </div>\n" +
                "            </form>\n" +
                "        </div>";
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
