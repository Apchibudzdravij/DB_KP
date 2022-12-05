package com.belstu.drevoten.DB_KP.controllerHelper;

import com.belstu.drevoten.DB_KP.model.Student;

public class StudentHTML {

    public static String studentMain(Student student) {
        String response = "<div id=\"student\">\n<div id=\"hello-block\">\n<p>Welcome, " +
                student.getFirstName() + " " + student.getFamilyName() +
                "</p>\n</div>\n<div id=\"user-stats\">\n<div id=\"calendar-plan\">\n" +
                "<p class=\"calendar-plan-header\">Calendar plan of current project</p>\n";
        response +=
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
        response +=
                "</div>\n<div id=\"general-stats\">\n<div id=\"uniqueness\">\n" +
                "                        <!-- TODO auto calculating of % of uniqueness-->\n" +
                "                        <p class=\"ready-percent\">You did not send explanatory note!</p>\n" +
                "</div>\n<div id=\"notifics\">\n";
        response +=
                "                        <!-- TODO notifications -->\n" +
                "                        <p class=\"notification-header\">Notifications</p>\n" +
                "                        <p class=\"notification\"> * No new notifications <span style=\"font-style: normal\">♡( ◡‿◡ )</span></p>\n";
        response +=
                "</div>\n</div>\n</div>\n</div>";
        return response;
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
                "                <form th:method=\"POST\" th:action=\"@{/messages}\" id=\"ask-form-body\">\n" +
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

    public static String studentMessages(){
        return "<div id=\"student\">\n" +
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

    public static String studentChange() {
        return "<div id= \"student\">\n" +
                "            <div id= \"hello-block\">\n" +
                "                <p>Change user information</p>\n" +
                "            </div>\n" +
                "            <form id= \"user-settings\" method=\"POST\" th:action=\"@{change}\" th:object=\"${userchangeform}\">\n" +
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
}
