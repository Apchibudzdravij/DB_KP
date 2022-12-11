package com.belstu.drevoten.DB_KP.model.DAO;

import com.belstu.drevoten.DB_KP.model.User_List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import java.util.List;
import java.util.Map;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name="FindIsUserInDB",
                procedureName="FindIsUserInDB",
                resultClasses = { String.class },
                parameters={
                        @StoredProcedureParameter(name="suserid", type=String.class, mode= ParameterMode.IN),
                        @StoredProcedureParameter(name="suserType", type=Character.class, mode=ParameterMode.OUT)
                }
        )})

@Repository
public interface MainRepository extends JpaRepository<User_List, String> {
    @Procedure(name = "FindIsUserInDB")
    User_List FindIsUserInDB(String suserid);
}
