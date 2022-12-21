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
}
