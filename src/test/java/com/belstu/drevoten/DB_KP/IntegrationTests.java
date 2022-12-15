package com.belstu.drevoten.DB_KP;

import com.belstu.drevoten.DB_KP.model.DAO.AuthDAO;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;
import com.belstu.drevoten.DB_KP.model.StudentsNoPass;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class IntegrationTests {

    @Test
    public void studentAuthorization(){
        MainDAO mainDAO = new MainDAO();
        AuthDAO authDAO = new AuthDAO();
        String id = "71201091";
        String pass = "123";
        String studentType =  String.valueOf(mainDAO.getIsUserInDB(id));
        StudentsNoPass student = authDAO.getStudentIfPassword(id, pass);
        Assert.assertTrue(studentType.equals("s")&&(student != null));
    }

    @Test
    public void administratorAuthorization() {
        MainDAO mainDAO = new MainDAO();
        AuthDAO authDAO = new AuthDAO();
        String id = "12345678";
        String pass = "12345678";
        String studentType =  String.valueOf(mainDAO.getIsUserInDB(id));
        StudentsNoPass student = authDAO.getStudentIfPassword(id, pass);
        Assert.assertTrue(studentType.equals("a")&&(student != null));
    }
}
