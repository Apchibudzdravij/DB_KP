package com.belstu.drevoten.DB_KP.controllerHelper;

import com.belstu.drevoten.DB_KP.model.Executive_Admin;
import com.belstu.drevoten.DB_KP.model.Executive_AdminNoPass;

public class AdminHTML {

    public static String adminMain(Executive_AdminNoPass student) {
        String response = "<div id=\"student\">\n<div id=\"hello-block\">\n<p>Welcome, " +
                student.getFirstName() + " " + student.getFamilyName() +
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
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td class=\"td-start\">71201091</td>\n" +
                "              <td class=\"td-middle\">Change name from Yauheni</td>\n" +
                "              <td class=\"td-finish\">05.12.2022 22:44</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td class=\"td-start\">71201091</td>\n" +
                "              <td class=\"td-middle\">Change name from Eugene</td>\n" +
                "              <td class=\"td-finish\">05.12.2022 22:42</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td class=\"td-start\">71201091</td>\n" +
                "              <td class=\"td-middle\">Change name from Yauheni</td>\n" +
                "              <td class=\"td-finish\">05.12.2022 22:40</td>\n" +
                "            </tr>\n" +
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
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td class=\"td-start\">71201090</td>\n" +
                "              <td class=\"td-middle\">20001: No such user</td>\n" +
                "              <td class=\"td-finish\">05.12.2022 22:30</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td class=\"td-start\">71201094</td>\n" +
                "              <td class=\"td-middle\">20001: No such user</td>\n" +
                "              <td class=\"td-finish\">05.12.2022 22:31</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td class=\"td-start\">71201090</td>\n" +
                "              <td class=\"td-middle\">20001: No such user</td>\n" +
                "              <td class=\"td-finish\">05.12.2022 22:31</td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </div>\n" +
                "        <div id=\"notifics\" class=\"admin-main-body-column\">\n" +
                "          <p>Notification</p>\n" +
                "          <p class=\"notification\"> * No new notifications <br/><span style=\"font-style: normal\">ヾ(´〇`)ﾉ♪♪♪</span></p>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>";
        return response;
    }


    public static String adminSettings() {
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

    public static String adminChange() {
        return  "<div id= \"admin\">\n" +
                "            <div id= \"hello-block\">\n" +
                "                <p>Change user information</p>\n" +
                "            </div>\n" +
                "            <form id= \"user-settings\" method=\"POST\" th:action=\"@{achange}\" th:object=\"${userchangeform}\">\n" +
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

    public static String adminSendMessage() {
        return "<div id=\"admin\">\n" +
                "            <div id=\"hello-block\">\n" +
                "                <p>Send message</p>\n" +
                "            </div>\n" +
                "            <div id=\"ask-form\">\n" +
                "                <form th:method=\"POST\" th:action=\"@{/amessages}\" id=\"ask-form-body\">\n" +
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

    public static String adminMessages() {
        return "<div id=\"admin\">\n" +
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

    public static String adminChangeUsers() {
        return "";
    }

    public static String adminAddUsers() {
        return "<div id=\"admin\">\n" +
                "      <div id=\"hello-block\">\n" +
                "        <p>Register new users</p>\n" +
                "      </div>\n" +
                "      <div id=\"registration-body\">\n" +
                "        <div id=\"register-field\">\n" +
                "          <div id=\"register-field-text\">\n" +
                "            Drag and drop XML file here\n" +
                "          </div>\n" +
                "        </div>\n" +
                "        <div id=\"apply-registration\">\n" +
                "          <button class=\"guiable\" id=\"register-button\">Register now!</button>\n" +
                "          <div id=\"register-error\"></div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>";
    }
}
