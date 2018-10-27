package com.example.hp.moodle;

/**
 * Created by HP on 13-09-2018.
 */

public class Experiment {
    String aim;
    String desc;
    double[] Marks;
    double Total;

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double[] getMarks() {
        return Marks;
    }

    public void setMarks(double[] marks) {
        Marks = marks;
    }

    public void setTotal(double total) {
        Total = total;
    }

    void addMarks() {

    }
    void getTotal() {

    }
}
