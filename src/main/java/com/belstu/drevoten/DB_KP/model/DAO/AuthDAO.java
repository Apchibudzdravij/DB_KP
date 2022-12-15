package com.belstu.drevoten.DB_KP.model.DAO;

import com.belstu.drevoten.DB_KP.model.Notifications;
import com.belstu.drevoten.DB_KP.model.StudentsNoPass;
import oracle.jdbc.OracleTypes;

import javax.persistence.Column;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AuthDAO {

    public StudentsNoPass getStudentIfPassword(String uid, String upass) {

        String runSP = "call StudentAuth(?,?,?)";
        List<StudentsNoPass> studentsList = new LinkedList<>();
        StudentsNoPass student;
        String user = "KP_USER_STUDENT";
        String pass = "kp_user_student";
        MainDAO mainDAO = new MainDAO();
        int counter = 0;
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, uid);
            cs.setString(2, upass);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(3);
            while (resultSet.next()) {
                ++counter;
                student = new StudentsNoPass(
                        resultSet.getString("StudentID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("FamilyName"),
                        resultSet.getString("FatherName"),
                        resultSet.getInt("Course"),
                        resultSet.getString("SubGroup"),
                        resultSet.getString("Faculty"),
                        resultSet.getString("Special"),
                        resultSet.getInt("UnreadMessages"),
                        resultSet.getInt("Gender"),
                        resultSet.getString("UserLanguage"),
                        resultSet.getString("UserTheme")
                );
                studentsList.add(student);
            }
            if (counter == 0)
                return null;
        } catch (SQLException e) {
            System.err.println("MainDAO getNotifications: SQL: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("MainDAO getNotifications: " + e.getMessage());
            return null;
        }
        return studentsList.get(0);
    }

    public StudentsNoPass getAdminIfPassword(String uid, String upass) {

        String runSP = "call AdminAuth(?,?,?)";
        List<StudentsNoPass> studentsList = new LinkedList<>();
        StudentsNoPass student;
        String user = "KP_USER_STUDENT";
        String pass = "kp_user_student";
        MainDAO mainDAO = new MainDAO();
        int counter = 0;
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, uid);
            cs.setString(2, upass);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(3);
            while (resultSet.next()) {
                ++counter;
                student = new AdminsNoPass(
                        resultSet.getString("AdminID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("FamilyName"),
                        resultSet.getString("FatherName"),
                        resultSet.getInt("UnreadMessages"),
                        resultSet.getInt("Gender"),
                        resultSet.getString("UserLanguage"),
                        resultSet.getString("UserTheme")
                );
                studentsList.add(student);
            }
            if (counter == 0)
                return null;
        } catch (SQLException e) {
            System.err.println("MainDAO getNotifications: SQL: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("MainDAO getNotifications: " + e.getMessage());
            return null;
        }
        return studentsList.get(0);
    }
}
