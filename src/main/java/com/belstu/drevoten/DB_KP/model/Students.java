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
@Table(name = "STUDENTS")
@AllArgsConstructor
@NoArgsConstructor
public class Students {
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
    private String Gender;
    @Column(name = "Password")
    private String Password;
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

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String familyName) {
        FamilyName = familyName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getKurs() {
        return Kurs;
    }

    public void setKurs(Integer kurs) {
        Kurs = kurs;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }

    public String getSpecial() {
        return Special;
    }

    public void setSpecial(String special) {
        Special = special;
    }

    public Integer getUnreadMessages() {
        return UnreadMessages;
    }

    public void setUnreadMessages(Integer unreadMessages) {
        UnreadMessages = unreadMessages;
    }
}
