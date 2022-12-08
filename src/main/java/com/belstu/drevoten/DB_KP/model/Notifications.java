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
@Table(name = "NOTIFICATIONS")
@AllArgsConstructor
@NoArgsConstructor
public class Notifications {
    @Id
    @Column(name="ROWID")
    private String rowid;
    @Column(name="UserID")
    private String UserID;
    @Column(name="ContentText")
    private String ContentText;
    @Column(name="DateAppear")
    private String DateAppear;
}