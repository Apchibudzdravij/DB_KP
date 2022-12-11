package com.belstu.drevoten.DB_KP.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class CourseProjectsId implements Serializable{
    private String StudentID;
    private String CourseID;
}
