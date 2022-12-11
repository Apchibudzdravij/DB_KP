package com.belstu.drevoten.DB_KP.services;

import com.belstu.drevoten.DB_KP.model.DAO.MainRepository;
import com.belstu.drevoten.DB_KP.model.User_List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface MainServiceInt {

    public User_List getUserIfIsInDB(String userid);
}
