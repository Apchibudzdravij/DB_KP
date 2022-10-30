package com.belstu.drevoten.DB_KP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KursTask {
    private String Name;
    private Integer Kurs;
    private String Faculty;
    private String Special;
    private String Goal;
    private String Subject;
}
