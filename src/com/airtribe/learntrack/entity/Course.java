package com.airtribe.learntrack.entity;

public class Course {
    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    public Course(int id, String courseName, String description, int durationInWeeks, boolean active) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = active;
    }   

    public int getId() {return id;}
    public String getCourseName() {return courseName;}
    public String getDescription() {return description;}
    public int getDurationInWeeks() {return durationInWeeks;}
    public boolean isActive() {return active;}
    public void setActive(boolean active) {this.active = active;}
    public void setDescription(String description) {this.description = description;}
}
