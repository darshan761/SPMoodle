package com.example.hp.moodle;

/**
 * Created by HP on 13-09-2018.
 */

public class Course {
    String name;
    String prof;
    int exp_no;
    String desp;

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    Course(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public int getExp_no() {
        return exp_no;
    }

    public void setExp_no(int exp_no) {
        this.exp_no = exp_no;
    }
}
