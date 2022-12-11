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
@Table(name = "USER_LIST")
@AllArgsConstructor
@NoArgsConstructor
public class User_List {
    @Id
    @Column(name="ROWID")
    private String rowid;
    @Column(name = "UserID")
    private String UserId;
    @Column(name = "UserType")
    private String UserType;
}
