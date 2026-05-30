package com.school.roller_speed.dto;

import java.util.List;

public class StudentGroupDto {
    private String studentName;
    private String groupName;
    private String groupLevel;
    private String teacherName;
    private String schedule;
    private List<String> classmates;

    public StudentGroupDto() {
    }

    public StudentGroupDto(String studentName, String groupName,String groupLevel,
                           String teacherName, String schedule,
                           List<String> classmates) {
        this.studentName = studentName;
        this.groupName = groupName;
        this.groupLevel = groupLevel;
        this.teacherName = teacherName;
        this.schedule = schedule;
        this.classmates = classmates;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(String groupLevel) {
        this.groupLevel = groupLevel;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<String> getClassmates() {
        return classmates;
    }

    public void setClassmates(List<String> classmates) {
        this.classmates = classmates;
    }
}