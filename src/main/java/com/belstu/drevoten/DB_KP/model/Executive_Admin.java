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
@Table(name = "EXECUTIVE_ADMIN")
@AllArgsConstructor
@NoArgsConstructor
public class Executive_Admin {
    @Id
    @Column(name = "AdminID")
    private String AdminID;
    @Column(name = "FirstName")
    private String FirstName;
    @Column(name = "FamilyName")
    private String FamilyName;
    @Column(name = "FatherName")
    private String FatherName;
    @Column(name = "Gender")
    private UserGender Gender;
    @Column(name = "Password")
    private String Password;
    @Column(name = "UnreadMessages")
    private Integer UnreadMessages;
}
