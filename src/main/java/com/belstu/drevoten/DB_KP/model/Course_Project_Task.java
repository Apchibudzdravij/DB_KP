package com.belstu.drevoten.DB_KP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.NClob;

@Data
@Entity
@Table(name = "COURSE_PROJECT_TASK")
@IdClass(CourseProjectTaskId.class)
@AllArgsConstructor
@NoArgsConstructor
public class Course_Project_Task {
    @Id
    @Column(name = "ProjectYear")
    private String ProjectYear;
    @Id
    @Column(name = "ProjectTitle")
    private String ProjectTitle;
    @Column(name = "Course")
    private Integer Course;
    @Column(name = "Faculty")
    private String Faculty;
    @Column(name = "Special")
    private String Special;
    @Lob
    @Column(name = "Goal")
    private NClob Goal;
    @Column(name = "Subject")
    private String Subject;
}
