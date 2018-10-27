package com.example.hp.moodle;

/**
 * Created by HP on 13-09-2018.
 */

public class Professor {
    private int ProfID;
    private String Name;
    private String Dept;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public String getDept() {
        return Dept;
    }

    public void setDept(String dept) {
        Dept = dept;
    }

    private  Course course;

    public int getProfID() {
        return ProfID;
    }

    public void setProfID(int profID) {
        ProfID = profID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    void gradeStudent() {

    }
    void createCourse() {

    }
    void generateReport() {

    }
    void deleteCourse(){

    }
}
