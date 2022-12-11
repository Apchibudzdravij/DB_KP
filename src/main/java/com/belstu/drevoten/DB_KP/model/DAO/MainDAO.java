package com.belstu.drevoten.DB_KP.model.DAO;

import com.belstu.drevoten.DB_KP.model.User_List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name="FindIsUserInDB",
        procedureName="DYV_admin.FindIsUserInDB",
        resultClasses = { String.class },
        parameters={
            @StoredProcedureParameter(name="suserid", type=String.class, mode=ParameterMode.IN),
            @StoredProcedureParameter(name="suserType", type=Integer.class, mode=ParameterMode.OUT)
        }
)})

@Repository
public class MainDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PersistenceContext
    private EntityManager manager;

    /*public User_List getIsUserInDB(String tempID) {
        final User_List[] tempUserList = new User_List[1];

        String sql = "SELECT * FROM DYV_admin.USER_LIST";

        List<User_List> userLists = getJdbcTemplate().query(sql,
                BeanPropertyRowMapper.newInstance(User_List.class));

        userLists.forEach((user) -> {
            if (user.getUserId().equals(tempID)) {
                tempUserList[0] = user;
                return;
            }
        });
        return tempUserList[0];
    }*/

    public String getIsUserInDB(String suserid, String suserType) {
        try
        {
            StoredProcedureQuery storedProcedure = manager
                    .createNamedStoredProcedureQuery("FindIsUserInDB");
            storedProcedure.setParameter(0, suserid)
                    .setParameter(1, suserType);
            storedProcedure.execute();
        }
        catch (Exception e) {
            System.err.println("-> getIsUserInDB (MainDAO): " + e.getMessage());
            return "e";
        }
        return "e";
    }
}
