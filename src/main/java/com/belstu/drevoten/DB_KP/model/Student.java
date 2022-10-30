package com.belstu.drevoten.DB_KP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String StudentID;
    private String FirstName;
    private String FamilyName;
    private String FatherName;
    private String Gender;
    private String Password;
    private Integer Kurs;
    private String Group;
    private String Faculty;
    private String Special;
    private Integer UnreadMessages;
}
