package com.belstu.drevoten.DB_KP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String TeacherID;
    private String FirstName;
    private String FamilyName;
    private String FatherName;
    private String Password;
    private String Department;
    private Integer UnreadMessages;
}
