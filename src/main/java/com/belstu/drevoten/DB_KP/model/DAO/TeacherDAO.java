package com.belstu.drevoten.DB_KP.model.DAO;

import com.belstu.drevoten.DB_KP.model.Messages;
import com.belstu.drevoten.DB_KP.model.ProjectsAdmissions;
import oracle.jdbc.OracleTypes;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class TeacherDAO {

    public boolean createTask(String login, String year, String title, String course, String faculty, String  special,
                              String subject, File goal) {
        String runSP = "call CreateCourseProjectTask(?,?,?,?,?,?,?,?)";
        String user = "KP_USER_TEACHER";
        String pass = "kp_user_teacher";
        MainDAO mainDAO = new MainDAO();
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, login);
            cs.setString(2, year);
            cs.setString(3, title);
            cs.setInt(4, Integer.parseInt(course));
            cs.setString(5, faculty);
            cs.setString(6, special);
            cs.setString(7, subject);
            //byte bytegoal[] = new byte[(int)goal.length()];
            //java.sql.Blob bgoal = new SerialBlob(bytegoal);
            //cs.setBlob(8, bgoal);
            FileInputStream fis = new FileInputStream(goal);
            cs.setBinaryStream(8, fis, fis.available());
            cs.execute();
        } catch (SQLException e) {
            System.err.println("TeacherDAO createTask: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("TeacherDAO createTask: " + e.getMessage());
            return false;
        }
        return true;
    }

    public List<ProjectsAdmissions> getShedule(String login) {
        String runSP = "call GetAdmissionSchedule(?,?)";
        String user = "KP_USER_TEACHER";
        String pass = "kp_user_teacher";
        MainDAO mainDAO = new MainDAO();
        List<ProjectsAdmissions> projectsAdmissions = new LinkedList<>();
        ProjectsAdmissions project;
        int counter = 0;
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, login);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(2);
            while (resultSet.next()) {
                ++counter;
                project = new ProjectsAdmissions(
                        resultSet.getString("CourseID"),
                        resultSet.getString("DateDeadline")
                );
                projectsAdmissions.add(project);
            }
            if (counter == 0)
                return null;
        } catch (SQLException e) {
            System.err.println("TeacherDAO getShedule: SQL: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("TeacherDAO getShedule: " + e.getMessage());
            return null;
        }
        return projectsAdmissions;
    }

    public boolean changeCourseProjectEntity(String login, String datedeadline,
                                             String teacherID, String studentID, String projectID) {
        String runSP = "call ChangeCourseProjectTeacher(?,?,?,?,?)";
        String user = "KP_USER_TEACHER";
        String pass = "kp_user_teacher";
        MainDAO mainDAO = new MainDAO();
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, login);
            cs.setString(2, studentID);
            cs.setString(3, projectID);
            cs.setString(4, teacherID);
            cs.setString(5, datedeadline);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("TeacherDAO changeCourseProjectEntity: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("TeacherDAO changeCourseProjectEntity: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean markCourseProject(String login, String studentID, String projectID, Integer mark) {
        String runSP = "call MarkProjectTeacher(?,?,?,?)";
        String user = "KP_USER_TEACHER";
        String pass = "kp_user_teacher";
        MainDAO mainDAO = new MainDAO();
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, login);
            cs.setString(2, studentID);
            cs.setString(3, projectID);
            cs.setInt(4, mark);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("TeacherDAO markCourseProject: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("TeacherDAO markCourseProject: " + e.getMessage());
            return false;
        }
        return true;
    }
}
