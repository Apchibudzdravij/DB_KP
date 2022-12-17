package com.belstu.drevoten.DB_KP;

import com.belstu.drevoten.DB_KP.model.DAO.AuthDAO;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;
import com.belstu.drevoten.DB_KP.model.Executive_AdminNoPass;
import com.belstu.drevoten.DB_KP.model.StudentsNoPass;
import com.belstu.drevoten.DB_KP.model.TeachersNoPass;
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
        String adminType =  String.valueOf(mainDAO.getIsUserInDB(id));
        Executive_AdminNoPass admin = authDAO.getAdminIfPassword(id, pass);
        Assert.assertTrue(adminType.equals("a")&&(admin != null));
    }

    @Test
    public void teacherAuthorization() {
        MainDAO mainDAO = new MainDAO();
        AuthDAO authDAO = new AuthDAO();
        String id = "16868686";
        String pass = "16868686";
        String adminType =  String.valueOf(mainDAO.getIsUserInDB(id));
        TeachersNoPass teacher = authDAO.getTeacherIfPassword(id, pass);
        Assert.assertTrue(adminType.equals("t")&&(teacher != null));
    }
}
