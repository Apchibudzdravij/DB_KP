package com.belstu.drevoten.DB_KP.controllerHelper;

public class StudentHTML {

    public static String studentMain() {
        return "<div id=\"student\">\n" +
               "            <div id=\"hello-block\">\n" +
               "                <p>Welcome, Eugene Drevoten</p>\n" +
               "            </div>\n" +
               "            <div id=\"user-stats\">\n" +
               "                <div id=\"calendar-plan\">\n" +
               "                    <p class=\"calendar-plan-header\">Calendar plan of current project</p>\n" +
               "                    <!-- TODO foreach of these plan -->\n" +
               "                    <div class=\"step-of-plan\">\n" +
               "                        <p class=\"date-in-plan\">13.10.2022</p>\n" +
               "                        <input type=\"checkbox\" class=\"step-of-project\" value=\"Step from user list #1\">\n" +
               "                            Analytical review of literature on the topic of the project<hr/>\n" +
               "                    </div>\n" +
               "                    <div class=\"step-of-plan\">\n" +
               "                        <p class=\"date-in-plan\">28.10.2022</p>\n" +
               "                        <input type=\"checkbox\" class=\"step-of-project\" value=\"Step from user list #3\">\n" +
               "                            Creating the necessary objects<hr/>\n" +
               "                    </div>\n" +
               "                    <div class=\"step-of-plan\">\n" +
               "                        <p class=\"date-in-plan\">30.11.2022</p>\n" +
               "                        <input type=\"checkbox\" class=\"step-of-project\" value=\"Step from user list #2\">\n" +
               "                            Project handover<hr/>\n" +
               "                    </div>\n" +
               "                </div>\n" +
               "                <div id=\"general-stats\">\n" +
               "                    <div id=\"uniqueness\">\n" +
               "                        <!-- TODO auto calculating of % of uniqueness-->\n" +
               "                        <p class=\"ready-percent\">You did not send explanatory note!</p>\n" +
               "                    </div>\n" +
               "                    <div id=\"notifics\">\n" +
               "                        <!-- TODO notifications -->\n" +
               "                        <p class=\"notification-header\">Notifications</p>\n" +
               "                        <p class=\"notification\"> * No new notifications <span style=\"font-style: normal\">♡( ◡‿◡ )</span></p>\n" +
               "                    </div>\n" +
               "                </div>\n" +
               "            </div>\n" +
               "        </div>";
    }

    public static String studentSettings() {
        return "";
    }
}
