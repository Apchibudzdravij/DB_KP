package com.belstu.drevoten.DB_KP;

import com.belstu.drevoten.DB_KP.model.DAO.AuthDAO;
import com.belstu.drevoten.DB_KP.model.DAO.MainDAO;
import com.belstu.drevoten.DB_KP.model.Localizations;
import com.belstu.drevoten.DB_KP.model.StudentsNoPass;
import com.belstu.drevoten.DB_KP.model.UserGender;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.concurrent.atomic.AtomicBoolean;


public class UnitTests {

    @Test
    public void findIsUserAdmin() {
        MainDAO mainDAO = new MainDAO();
        String userType = String.valueOf(mainDAO.getIsUserInDB("12345678"));
        Assert.assertTrue(userType.equals("a"));
    }

    @Test
    public void getStudentIfPasswordIsIncorrect(){
        AuthDAO dao = new AuthDAO();
        StudentsNoPass student = dao.getStudentIfPassword("71201091","789");
        //if password is incorrect, getStudentIfPassword returns null;
        Assert.assertTrue(student == null);
    }

    @Test
    public void getIncorrectGender() {
        Assert.expectThrows(Exception.class, () -> UserGender.valueOf("Biowoman"));
    }

    @Test
    public void localizationListHasNoRussianLanguage() {
        Localizations localization = new Localizations();
        AtomicBoolean hasRussianLanguage = new AtomicBoolean(false);
        localization.getLanguageList().forEach(lang -> { if (lang.getName().equals("Russian")) hasRussianLanguage.set(true); });
        Assert.assertTrue(!hasRussianLanguage.get());
    }
}
