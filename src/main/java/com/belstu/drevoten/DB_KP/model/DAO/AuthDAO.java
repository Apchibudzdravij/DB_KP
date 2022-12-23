package com.belstu.drevoten.DB_KP.model.DAO;

import com.belstu.drevoten.DB_KP.model.*;
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
                        UserGender.values()[resultSet.getInt("Gender")],
                        resultSet.getString("UserLanguage"),
                        resultSet.getString("UserTheme")
                );
                studentsList.add(student);
            }
            if (counter == 0)
                return null;
        } catch (SQLException e) {
            System.err.println("AuthDAO getStudentIfPassword: SQL: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("AuthDAO getStudentIfPassword: " + e.getMessage());
            return null;
        }
        return studentsList.get(0);
    }

    public Executive_AdminNoPass getAdminIfPassword(String uid, String upass) {

        String runSP = "call AdminAuth(?,?,?)";
        List<Executive_AdminNoPass> adminsList = new LinkedList<>();
        Executive_AdminNoPass admin;
        String user = "KP_USER_ADMIN";
        String pass = "kp_user_admin";
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
                admin = new Executive_AdminNoPass(
                        resultSet.getString("AdminID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("FamilyName"),
                        resultSet.getString("FatherName"),
                        UserGender.values()[resultSet.getInt("Gender")],
                        resultSet.getString("UserLanguage"),
                        resultSet.getString("UserTheme")
                );
                adminsList.add(admin);
            }
            if (counter == 0)
                return null;
        } catch (SQLException e) {
            System.err.println("AuthDAO getAdminIfPassword: SQL: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("AuthDAO getAdminIfPassword: " + e.getMessage());
            return null;
        }
        return adminsList.get(0);
    }

    public TeachersNoPass getTeacherIfPassword(String uid, String upass) {

        String runSP = "call TeacherAuth(?,?,?)";
        List<TeachersNoPass> teachersList = new LinkedList<>();
        TeachersNoPass teacher;
        String user = "KP_USER_TEACHER";
        String pass = "kp_user_teacher";
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
                teacher = new TeachersNoPass(
                        resultSet.getString("TeacherID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("FamilyName"),
                        resultSet.getString("FatherName"),
                        resultSet.getString("Department"),
                        UserGender.values()[resultSet.getInt("Gender")],
                        resultSet.getString("UserLanguage"),
                        resultSet.getString("UserTheme")
                );
                teachersList.add(teacher);
            }
            if (counter == 0)
                return null;
        } catch (SQLException e) {
            System.err.println("AuthDAO getTeacherIfPassword: SQL: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("AuthDAO getTeacherIfPassword: " + e.getMessage());
            return null;
        }
        return teachersList.get(0);
    }
}
