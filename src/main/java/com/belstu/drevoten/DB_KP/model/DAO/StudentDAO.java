package com.belstu.drevoten.DB_KP.model.DAO;

import com.belstu.drevoten.DB_KP.model.Course_Projects;
import com.belstu.drevoten.DB_KP.model.ProjectsAdmissions;
import oracle.jdbc.OracleTypes;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StudentDAO {

    public boolean sendTask(String login, File task) {
        String runSP = "call SendTaskFile(?,?)";
        String user = "KP_USER_STUDENT";
        String pass = "kp_user_student";
        MainDAO mainDAO = new MainDAO();
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, login);
            FileInputStream fis = new FileInputStream(task);
            cs.setBinaryStream(2, fis, fis.available());
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("StudentDAO sendTask: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("TeacherDAO sendTask: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean sendArc(String login, File arc) {
        String runSP = "UPDATE COURSE_PROJECTS" +
                "        SET CourseArcFile = ?" +
                "        WHERE StudentId = ?" +
                "            AND DateDeadline > SYSDATE";
        String user = "KP_USER_STUDENT";
        String pass = "kp_user_student";
        MainDAO mainDAO = new MainDAO();
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(2, login);
            FileInputStream fis = new FileInputStream(arc);
            cs.setBinaryStream(1, fis, fis.available());
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("StudentDAO sendArc: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("StudentDAO sendArc: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean sendNote(String login, File arc) {
        String runSP = "call SendExplNoteFile(?,?)";
        String user = "KP_USER_STUDENT";
        String pass = "kp_user_student";
        MainDAO mainDAO = new MainDAO();
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, login);
            FileInputStream fis = new FileInputStream(arc);
            cs.setBinaryStream(2, fis, fis.available());
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("StudentDAO sendNote: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("StudentDAO sendNote: " + e.getMessage());
            return false;
        }
        return true;
    }

    public List<Course_Projects> getStudentProjectWithFiles(String login) {
        String runSP = "call GetProjectAllFilesForStudentProc(?,?)";
        String user = "KP_USER_STUDENT";
        String pass = "kp_user_student";
        MainDAO mainDAO = new MainDAO();
        List<Course_Projects> projectsAdmissions = new LinkedList<>();
        Course_Projects project;
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
                byte[] buff;
                File fileTask;
                File fileExplNote;
                File fileCourseArc;
                String student = resultSet.getString("StudentID");
                Blob blobTask = resultSet.getBlob("TaskFile");
                Blob blobExplNote = resultSet.getBlob("ExplNoteFile");
                Blob blobCourseArc = resultSet.getBlob("CourseArcFile");
                if (blobTask != null) {
                    fileTask = new File("src\\main\\resources\\files\\task_"
                            + String.valueOf(counter) + String.valueOf(counter + 2) + student + ".docx");
                    InputStream inTask = blobTask.getBinaryStream();
                    OutputStream outTask = new FileOutputStream(fileTask);
                    buff = blobTask.getBytes(1, (int) blobTask.length());
                    outTask.write(buff);
                    outTask.close();
                } else { fileTask = null; }
                if (blobExplNote != null) {
                    fileExplNote = new File("src\\main\\resources\\files\\explnote_"
                            + String.valueOf(counter) + String.valueOf(counter + 2) + student + ".docx");
                    InputStream inExplNote = blobExplNote.getBinaryStream();
                    OutputStream outExplNote = new FileOutputStream(fileExplNote);
                    buff = blobExplNote.getBytes(1, (int) blobExplNote.length());
                    outExplNote.write(buff);
                    outExplNote.close();
                } else { fileExplNote = null; }
                if (blobCourseArc != null) {
                    fileCourseArc = new File("src\\main\\resources\\files\\coursearc_"
                            + String.valueOf(counter) + String.valueOf(counter + 2) + student + ".zip");
                    InputStream inCourseArc = blobCourseArc.getBinaryStream();
                    OutputStream outCourseArc = new FileOutputStream(fileCourseArc);
                    buff = blobCourseArc.getBytes(1, (int) blobCourseArc.length());
                    outCourseArc.write(buff);
                    outCourseArc.close();
                } else { fileCourseArc = null; }
                project = new Course_Projects(
                        student,
                        resultSet.getString("CourseID"),
                        resultSet.getString("TeacherID"),
                        resultSet.getString("DateStart"),
                        fileTask,
                        resultSet.getString("DateDeadline"),
                        fileExplNote,
                        fileCourseArc,
                        resultSet.getString("DatePass"),
                        resultSet.getInt("Grade"),
                        resultSet.getInt("Uniqueness")
                );
                projectsAdmissions.add(project);
            }
            if (counter == 0)
                return null;
        } catch (SQLException e) {
            System.err.println("StudentDAO getStudentProjectWithFiles: SQL: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("StudentDAO getStudentProjectWithFiles: " + e.getMessage());
            return null;
        }
        return projectsAdmissions;
    }
}
