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
@Table(name = "DB_KP_LOGS")
@AllArgsConstructor
@NoArgsConstructor
public class DB_KP_Logs {
    @Id
    @Column(name="ROWID")
    private String rowid;
    @Column(name="lWho")
    private String lWho;
    @Column(name="lWhat")
    private String lWhat;
    @Column(name="lWhen")
    private String lWhen;
}
