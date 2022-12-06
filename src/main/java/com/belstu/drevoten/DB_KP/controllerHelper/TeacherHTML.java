package com.belstu.drevoten.DB_KP.controllerHelper;

import com.belstu.drevoten.DB_KP.model.Teacher;

public class TeacherHTML {

    public static String teacherMain(Teacher teacher)  {
        String response = "<div id=\"teacher\">\n<div id=\"hello-block\">\n<p>Welcome, " +
                teacher.getFirstName() + " " + teacher.getFamilyName() +
                "</p>\n</div>\n<div id=\"main-teacher-body\">\n<div id=\"project-acceptance\">\n" +
                " <div class=\"project-acceptance-header\">Acceptance of course projects</div>\n";
        response +=
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
        response +=
                "</div>\n<div id=\"notifics\">\n\t<p class=\"notification-header\">Notifications</p>";
        response +=
                "<div class=\"notification\">\n\t<p>* No new notifications ヽ(°〇°)ﾉ</p>\n</div>";
        response +=
                "</div>\n</div>\n</div>\n";
        return response;
    }

    public static String teacherSettings() {
        return "<div id=\"teacher\">\n" +
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

    public static String teacherMessages() {
        return "<div id=\"teacher\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Messages</p>\n" +
                "            </div>\n" +
                "            <div id=\"message-list\">\n" +
                "                <!--TODO auto list from DB-->\n" +
                "                <div class=\"message\">\n" +
                "                    <div class=\"message-head\">\n" +
                "                        <div class=\"mes-sender\">*  71201091:</div>\n" +
                "                        <div class=\"mes-header\">Test 1</div>\n" +
                "                        <div class=\"mes-date-sent\">29.11.2022 23:57</div>\n" +
                "                    </div>\n" +
                "                    <div class=\"message-body\">\n" +
                "                        <textarea readonly class=\"message-content\">Hello from test 1</textarea>\n" +
                "                        <button class=\"guiable answerable\">Answer</button>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"message\">\n" +
                "                    <div class=\"message-head\">\n" +
                "                        <div class=\"mes-sender\">*  71201091:</div>\n" +
                "                        <div class=\"mes-header\">Test 2</div>\n" +
                "                        <div class=\"mes-date-sent\">29.11.2022 23:59</div>\n" +
                "                    </div>\n" +
                "                    <div class=\"message-body\">\n" +
                "                        <textarea readonly class=\"message-content\">Hello from test 2</textarea>\n" +
                "                        <button class=\"guiable answerable\">Answer</button>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";
    }

    public static String teacherChange() {
        return "<div id= \"teacher\">\n" +
                "            <div id= \"hello-block\">\n" +
                "                <p>Change user information</p>\n" +
                "            </div>\n" +
                "            <form id= \"user-settings\" method=\"POST\" th:action=\"@{tchange}\" th:object=\"${userchangeform}\">\n" +
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

    public static String teacherSendMessage() {
        return "<div id=\"teacher\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Send message</p>\n" +
                "            </div>\n" +
                "            <div id=\"ask-form\">\n" +
                "                <form th:method=\"POST\" th:action=\"@{/tmessages}\" id=\"ask-form-body\">\n" +
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

    public static String teacherCreateTask() {
        return "<div id=\"teacher\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Create new course project</p>\n" +
                "            </div>\n" +
                "            <div id=\"create-form\">\n" +
                "                <form method=\"POST\" th:action=\"@{createTask}\" th:object=\"${kurstaskform}\">\n" +
                "                    <p>Enter project information</p>\n" +
                "                    <div id=\"new-project-name\">\n" +
                "                        <input class=\"new-project-input longer\" type=\"text\" placeholder=\"Full title\">\n" +
                "                        <input class=\"new-project-input shorter\" type=\"text\" placeholder=\"Year\">\n" +
                "                    </div>\n" +
                "                    <div id=\"target-description\">\n" +
                "                        <input class=\"new-project-input shorter\" type=\"number\" placeholder=\"Study year (1-4)\"/>\n" +
                "                        <input class=\"new-project-input longer\" type=\"text\" placeholder=\"Faculty (FIT)\"/>\n" +
                "                        <input class=\"new-project-input longer\" type=\"text\" placeholder=\"Speciality (POIT)\"/>\n" +
                "                    </div>\n" +
                "                    <input class=\"new-project-input\" id=\"create-form-subject-name\" type=\"text\" placeholder=\"Subject\"/>\n" +
                "                    <div id=\"create-drag-drop\">\n" +
                "                        <\n" +
                "                    </div>\n" +
                "                    <input class=\"guiable\" type=\"submit\" value=\"Create new task!\"/>\n" +
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
}
