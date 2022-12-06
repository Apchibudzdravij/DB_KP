package com.belstu.drevoten.DB_KP.forms;

import com.belstu.drevoten.DB_KP.model.UserGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KursTaskForm {
    private String Name;
    private Integer Kurs;
    private String Faculty;
    private String Special;
    private String Goal;
    private String Subject;
}
