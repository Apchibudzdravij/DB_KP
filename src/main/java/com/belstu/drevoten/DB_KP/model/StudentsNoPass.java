package com.belstu.drevoten.DB_KP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "StudentsNoPass")
@AllArgsConstructor
@NoArgsConstructor
public class StudentsNoPass {
    @Id
    @Column(name = "StudentID")
    private String StudentID;
    @Column(name = "FirstName")
    private String FirstName;
    @Column(name = "FamilyName")
    private String FamilyName;
    @Column(name = "FatherName")
    private String FatherName;
    @Column(name = "Gender")
    private Integer Gender;
    @Column(name = "Kurs")
    private Integer Kurs;
    @Column(name = "Group")
    private String Group;
    @Column(name = "Faculty")
    private String Faculty;
    @Column(name = "Special")
    private String Special;
    @Column(name = "UnreadMessages")
    private Integer UnreadMessages;
    @Column(name="UserLanguage")
    private String UserLanguage;
    @Column(name="UserTheme")
    private String UserTheme;
}
