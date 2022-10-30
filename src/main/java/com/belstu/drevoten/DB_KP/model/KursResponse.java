package com.belstu.drevoten.DB_KP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KursResponse {
    private String Student;
    private String KursName;
    private String Teacher;
    private String DateStart;
    private String TaskFile;
    private String DateSend;
    private String NoteFile;
    private String ArcFile;
    private String DatePassing;
    private Integer Mark;
}
