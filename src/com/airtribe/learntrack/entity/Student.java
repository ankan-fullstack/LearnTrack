package com.airtribe.learntrack.entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    //Constructor
    public Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    public Student(int id, String firstName, String lastName, String batch, boolean active) {
        this(id, firstName, lastName, null, batch, active);
    }

    //Getters and Setters
    public String getBatch() {return batch;}
    public boolean isActive() {return active;}
    public void setBatch(String batch) {this.batch = batch;}
    public void setActive(boolean active) {this.active = active;}

    //methods
    @Override 
    public String getDisplayName() {
        return super.getDisplayName() + "-" + this.batch;
    }
}
