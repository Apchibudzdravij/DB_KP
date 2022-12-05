package com.belstu.drevoten.DB_KP.controllerHelper;

import com.belstu.drevoten.DB_KP.model.Teacher;

public class TeacherHTML {

    public static String teacherMain(Teacher teacher)  {
        String response = "<div id=\"student\">\n<div id=\"hello-block\">\n<p>Welcome, " +
                teacher.getFirstName() + " " + teacher.getFamilyName() +
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
                        "<button class=\"guiable\">Send!</button>" +
                        "</div>\n<div id=\"notifics\">\n";
        response +=
                "                        <!-- TODO notifications -->\n" +
                        "                        <p class=\"notification-header\">Notifications</p>\n" +
                        "                        <p class=\"notification\"> * No new notifications <span style=\"font-style: normal\">♡( ◡‿◡ )</span></p>\n";
        response +=
                "</div>\n</div>\n</div>\n</div>";
        return response;
    }

    public static String teacherSettings() {
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
}
