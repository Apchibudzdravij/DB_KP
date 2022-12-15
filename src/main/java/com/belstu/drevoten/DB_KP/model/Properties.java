package com.belstu.drevoten.DB_KP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PROPERTIES")
@AllArgsConstructor
@NoArgsConstructor
public class Properties {
    @Id
    @Column(name="ROWID")
    private String rowid;
    @Column(name="UserID")
    private String UserID;
    @Column(name="UserLanguage")
    private String UserLanguage;
    @Column(name="UserTheme")
    private String UserTheme;
}
