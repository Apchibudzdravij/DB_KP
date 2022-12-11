package com.belstu.drevoten.DB_KP.services;


import com.belstu.drevoten.DB_KP.model.DAO.MainRepository;
import com.belstu.drevoten.DB_KP.model.User_List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Data
@Service
@NoArgsConstructor
@AllArgsConstructor
public class MainService implements MainServiceInt {

    @Autowired
    MainRepository mainRepository;

    public User_List getUserIfIsInDB(String userid) {
        return mainRepository.FindIsUserInDB(userid);
    }
}
