package com.belstu.drevoten.DB_KP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.sql.Clob;
import java.sql.NClob;

@Data
@Entity
@Table(name = "COURSE_PROJECTS")
@IdClass(CourseProjectsId.class)
@AllArgsConstructor
@NoArgsConstructor
public class Course_Projects {
    @Id
    @Column(name = "StudentID")
    private String StudentID;
    @Id
    @Column(name = "CourseID")
    private String CourseID;
    @Column(name = "TeacherID")
    private String TeacherID;
    @Column(name = "DateStart")
    private String DateStart;
    @Column(name = "TaskFile")
    private String TaskFile;
    @Column(name = "DateDeadline")
    private String DateDeadline;
    @Column(name = "ExplNoteFile")
    private String ExplNoteFile;
    @Lob
    @Column(name = "CourseArcFile")
    private NClob CourseArcFile;
    @Column(name = "DatePass")
    private String DatePass;
    @Column(name = "Grade")
    private Integer Grade;
    @Column(name = "Uniqueness")
    private Integer Uniqueness;
}
