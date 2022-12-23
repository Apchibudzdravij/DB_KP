package com.belstu.drevoten.DB_KP.model.DAO;

import com.belstu.drevoten.DB_KP.model.DB_KP_Logs;
import com.belstu.drevoten.DB_KP.model.StudentsNoPass;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AdminInfoDAO {

    public List<DB_KP_Logs> getLogs(String uid) {
        String runSP = "call GetLogsProc(?,?)";
        List<DB_KP_Logs> logsList = new LinkedList<>();
        DB_KP_Logs log;
        String user = "KP_USER_ADMIN";
        String pass = "kp_user_admin";
        MainDAO mainDAO = new MainDAO();
        int counter = 0;
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, uid);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(2);
            while (resultSet.next()) {
                ++counter;
                log = new DB_KP_Logs(
                        counter,
                        resultSet.getString("lWho"),
                        resultSet.getString("lWhat"),
                        resultSet.getString("lWhen")
                );
                logsList.add(log);
            }
            if (counter == 0)
                return null;
        } catch (SQLException e) {
            System.err.println("AdminInfoDAO getLogs: SQL: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("AdminInfoDAO getLogs: " + e.getMessage());
            return null;
        }
        return logsList;
    }

    public boolean registerUsers(String uid, byte[] res) {
        String runSP = "call GetLogsProc(?,?)";
        List<DB_KP_Logs> logsList = new LinkedList<>();
        DB_KP_Logs log;
        String user = "KP_USER_ADMIN";
        String pass = "kp_user_admin";
        MainDAO mainDAO = new MainDAO();
        int counter = 0;
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, uid);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(2);
            while (resultSet.next()) {
                ++counter;
                log = new DB_KP_Logs(
                        counter,
                        resultSet.getString("lWho"),
                        resultSet.getString("lWhat"),
                        resultSet.getString("lWhen")
                );
                logsList.add(log);
            }
            if (counter == 0)
                return false;
        } catch (SQLException e) {
            System.err.println("AdminInfoDAO getLogs: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("AdminInfoDAO getLogs: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean changeStudent(String login, String course, String subgroup, String faculty, String speciality) {
        String runSP = "call ChangeUserStudent(?,?,?,?,?)";
        String user = "KP_USER_ADMIN";
        String pass = "kp_user_admin";
        MainDAO mainDAO = new MainDAO();
        int counter = 0;
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, login);
            cs.setInt(2, Integer.parseInt(course));
            cs.setString(3, subgroup);
            cs.setString(4, faculty);
            cs.setString(5, speciality);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("AdminInfoDAO changeStudent: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("AdminInfoDAO changeStudent: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean changeTeacher(String login, String department) {
        String runSP = "call ChangeUserTeacher(?,?)";
        String user = "KP_USER_ADMIN";
        String pass = "kp_user_admin";
        MainDAO mainDAO = new MainDAO();
        int counter = 0;
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, login);
            cs.setString(2, department);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("AdminInfoDAO changeTeacher: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("AdminInfoDAO changeTeacher: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean upStudents(String login) {
        String runSP = "call UpStudentByOne(?)";
        String user = "KP_USER_ADMIN";
        String pass = "kp_user_admin";
        MainDAO mainDAO = new MainDAO();
        int counter = 0;
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, login);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("AdminInfoDAO upStudents: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("AdminInfoDAO upStudents: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean registerStudent(String login, String first, String second, String third,
                                   String speciality, String subgroup, String course) {
        String runSP = "call RegStudent(?,?,?,?,?,?,?)";
        String user = "KP_USER_ADMIN";
        String pass = "kp_user_admin";
        MainDAO mainDAO = new MainDAO();
        int counter = 0;
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, login);
            cs.setString(2, first);
            cs.setString(3, second);
            cs.setString(4, third);
            cs.setString(5, speciality);
            cs.setString(6, subgroup);
            cs.setInt(7, Integer.parseInt(course));
            cs.execute();
        } catch (SQLException e) {
            System.err.println("AdminInfoDAO registerStudent: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("AdminInfoDAO registerStudent: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean registerTeacher(String login, String first, String second, String third,
                                   String department) {
        String runSP = "call RegTeacher(?,?,?,?,?)";
        String user = "KP_USER_ADMIN";
        String pass = "kp_user_admin";
        MainDAO mainDAO = new MainDAO();
        int counter = 0;
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, login);
            cs.setString(2, first);
            cs.setString(3, second);
            cs.setString(4, third);
            cs.setString(5, department);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("AdminInfoDAO registerTeacher: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("AdminInfoDAO registerTeacher: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean registerAdmin(String login, String first, String second, String third) {
        String runSP = "call RegAdmin(?,?,?,?)";
        String user = "KP_USER_ADMIN";
        String pass = "kp_user_admin";
        MainDAO mainDAO = new MainDAO();
        int counter = 0;
        try (Connection conn = DriverManager.getConnection(
                mainDAO.serverURI, user, pass);
             CallableStatement cs = conn.prepareCall(runSP);
        ) {
            cs.setString(1, login);
            cs.setString(2, first);
            cs.setString(3, second);
            cs.setString(4, third);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("AdminInfoDAO registerAdmin: SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("AdminInfoDAO registerAdmin: " + e.getMessage());
            return false;
        }
        return true;
    }
}
