package com.belstu.drevoten.DB_KP.model.DAO;

import com.belstu.drevoten.DB_KP.model.StudentsNoPass;
import com.belstu.drevoten.DB_KP.model.User_List;
import com.belstu.drevoten.DB_KP.services.MainService;
import com.belstu.drevoten.DB_KP.services.MainServiceInt;
import org.hibernate.type.CharacterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.*;
import java.util.List;

public class MainDAO {

    @Autowired
    MainServiceInt mainService;

    public Character getIsUserInDB(String suserid) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.201.154:1521/DB_KP_PDB", "THE_LOST_ONE","the_lost_one");
            CallableStatement cstmt = conn.prepareCall("begin ? := FindIsUserInDB(?,?); end;");
            String userType = "Q";
            cstmt.registerOutParameter(1, Types.CHAR);
            cstmt.setString(2, suserid);
            cstmt.setString(3, userType);
            cstmt.execute();
            String result = cstmt.getString(1);
            System.out.print("getIsUserInDB Result: " + result);
            cstmt.close();
            conn.close();
            return result.charAt(0);
        } catch (SQLException e) {
            System.err.println("MainDAO getIsUserInDB: " + e.getMessage());
            return 'e';
        } catch (ClassNotFoundException e) {
            System.err.println("MainDAO getIsUserInDB: " + e.getMessage());
            return 'e';
        }
    }
    public StudentsNoPass getStudentIfPassword(String id, String pass) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.201.154:1521/DB_KP_PDB", "KP_USER_STUDENT","kp_user_student");
            CallableStatement cstmt = conn.prepareCall("begin ? := StudentAuth(?,?); end;");
            cstmt.registerOutParameter(1, Types.ARRAY);
            cstmt.setString(2, id);
            cstmt.setString(3, pass);
            cstmt.execute();
            StudentsNoPass result = (StudentsNoPass) cstmt.getObject(1);
            System.out.print("getStudentIfPassword: " + result);
            cstmt.close();
            conn.close();
            return result;
        } catch (SQLException e) {
            System.err.println("MainDAO getStudentIfPassword: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("MainDAO getStudentIfPassword: " + e.getMessage());
            return null;
        }
    }
}
