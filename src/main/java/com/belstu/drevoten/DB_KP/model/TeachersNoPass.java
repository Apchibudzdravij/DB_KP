package com.belstu.drevoten.DB_KP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Data
@Entity
@Table(name = "TeachersNoPass")
@AllArgsConstructor
@NoArgsConstructor
public class TeachersNoPass {
    @Id
    @Column(name = "TeacherID")
    private String TeacherID;
    @Column(name = "FirstName")
    private String FirstName;
    @Column(name = "FamilyName")
    private String FamilyName;
    @Column(name = "FatherName")
    private String FatherName;
    @Column(name = "Department")
    private String Department;
    @Column(name = "UnreadMessages")
    private Integer UnreadMessages;
    @Column(name = "Gender")
    @Digits(integer=2, fraction=0, message = "No more than 2 digits")
    private Integer Gender;
    @Column(name="UserLanguage")
    private String UserLanguage;
    @Column(name="UserTheme")
    private String UserTheme;
}
