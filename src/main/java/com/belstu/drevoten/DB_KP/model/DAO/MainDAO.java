package com.belstu.drevoten.DB_KP.model.DAO;

import com.belstu.drevoten.DB_KP.model.Messages;
import com.belstu.drevoten.DB_KP.model.Notifications;
import com.belstu.drevoten.DB_KP.model.StudentsNoPass;
import com.belstu.drevoten.DB_KP.model.User_List;
import org.hibernate.type.CharacterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.*;
import oracle.sql.*;
import oracle.jdbc.*;

import java.util.LinkedList;
import java.util.List;

import static java.sql.Types.STRUCT;

public class MainDAO {

    private String serverURI = "jdbc:oracle:thin:@192.168.201.160:1521/DB_KP_PDB";

    public Character getIsUserInDB(String suserid) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(serverURI, "THE_LOST_ONE","the_lost_one");
            CallableStatement cstmt = conn.prepareCall("begin ? := FindIsUserInDB(?,?); end;");
            String userType = "Q";
            cstmt.registerOutParameter(1, Types.CHAR);
            cstmt.setString(2, suserid);
            cstmt.setString(3, userType);
            cstmt.execute();
            String result = cstmt.getString(1);
            System.out.print("getIsUserInDB Result: " + result.charAt(0));
            cstmt.close();
            conn.close();
            return result.charAt(0);
        } catch (SQLException e) {
            System.err.println("MainDAO getIsUserInDB: " + e.getMessage().charAt(0));
            return 'e';
        } catch (ClassNotFoundException e) {
            System.err.println("MainDAO getIsUserInDB: " + e.getMessage().charAt(0));
            return 'e';
        }
    }
    public StudentsNoPass getStudentIfPassword(String id, String pass) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(serverURI, "KP_USER_STUDENT","kp_user_student");
            CallableStatement cstmt = conn.prepareCall("begin ? := StudentAuth(?,?); end;");
            cstmt.registerOutParameter(1, OracleTypes.ARRAY, "TABLE_STUDENTSNOPASS");
            cstmt.setString(2, id);
            cstmt.setString(3, pass);
            cstmt.execute();
            StudentsNoPass[] result = (StudentsNoPass[]) cstmt.getObject(1);
            System.out.print("getStudentIfPassword: " + result[0].toString());
            cstmt.close();
            conn.close();
            return result[0];
        } catch (SQLException e) {
            System.err.print("MainDAO getStudentIfPassword: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.err.print("MainDAO getStudentIfPassword: " + e.getMessage());
            return null;
        }
    }

    public List<Notifications> getNotifications(String id, String who) {

        String runSP = "call GetNotificationProc(?,?)";
        List<Notifications> notificationsList = new LinkedList<>();
        Notifications note;
        String user;
        String pass;
        int counter = 0;
        switch (who) {
            case "student" -> {
                user = "KP_USER_STUDENT";
                pass = "kp_user_student";
            }
            case "teacher" -> {
                user = "KP_USER_TEACHER";
                pass = "kp_user_teacher";
            }
            default -> {
                user = "KP_USER_ADMIN";
                pass = "kp_user_admin";
            }
        }
        try (Connection conn = DriverManager.getConnection(
                serverURI, user, pass);
             Statement statement = conn.createStatement();
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, id);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(2);
            while (resultSet.next()) {
                ++counter;
                note = new Notifications(
                        String.valueOf(counter),
                        resultSet.getString("UserID"),
                        resultSet.getString("ContentText"),
                        resultSet.getString("DateAppear")
                );
                notificationsList.add(note);
                System.out.println(note);
            }
        } catch (SQLException e) {
            System.err.println("MainDAO getNotifications: SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("MainDAO getNotifications: " + e.getMessage());
        }
        return notificationsList;
    }

    public List<Messages> getMessages(String id, String who) {

        String runSP = "call GetMessagesProc(?,?)";
        List<Messages> messagesList = new LinkedList<>();
        Messages mess;
        String user;
        String pass;
        int counter = 0;
        switch (who) {
            case "student" -> {
                user = "KP_USER_STUDENT";
                pass = "kp_user_student";
            }
            case "teacher" -> {
                user = "KP_USER_TEACHER";
                pass = "kp_user_teacher";
            }
            default -> {
                user = "KP_USER_ADMIN";
                pass = "kp_user_admin";
            }
        }
        try (Connection conn = DriverManager.getConnection(
                serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, id);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(2);
            while (resultSet.next()) {
                ++counter;
                mess = new Messages(
                        String.valueOf(counter),
                        resultSet.getString("Sender"),
                        resultSet.getString("Receiver"),
                        resultSet.getString("Subject"),
                        resultSet.getString("MessageBody"),
                        resultSet.getString("DateAndTime")
                );
                messagesList.add(mess);
                System.out.println(mess);
            }
        } catch (SQLException e) {
            System.err.println("MainDAO getNotifications: SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("MainDAO getNotifications: " + e.getMessage());
        }
        return messagesList;
    }

    public boolean saveProperties(String id, String lang, String theme, String who) {

        String runSP = "call ChangeUserProperties(?,?,?)";
        String user;
        String pass;
        switch (who) {
            case "student" -> {
                user = "KP_USER_STUDENT";
                pass = "kp_user_student";
            }
            case "teacher" -> {
                user = "KP_USER_TEACHER";
                pass = "kp_user_teacher";
            }
            default -> {
                user = "KP_USER_ADMIN";
                pass = "kp_user_admin";
            }
        }
        try (Connection conn = DriverManager.getConnection(
                serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, id);
            cs.setString(2, lang);
            cs.setString(3, theme);
            cs.execute();
            return true;
        } catch (Exception e) {
            System.err.println("MainDAO getNotifications: " + e.getMessage());
            return false;
        }
    }
}
