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
@Table(name = "MESSAGES")
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    @Id
    @Column(name="ROWID")
    private String rowid;
    @Column(name = "Sender")
    private String Sender;
    @Column(name = "Receiver")
    private String Receiver;
    @Column(name = "Subject")
    private String Subject;
    @Column(name = "MessageBody")
    private String MessageBody;
    @Column(name = "DateAndTime")
    private String DateAndTime;
}
