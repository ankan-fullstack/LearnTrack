package com.airtribe.learntrack.entity;
import java.time.LocalDate;
import com.airtribe.learntrack.enums.EnrollmentStatus;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment(int id, int studentId, int courseId, LocalDate enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public int getId() {return id;}
    public int getStudentId() {return studentId;}
    public int getCourseId() {return courseId;}
    public LocalDate getEnrollmentDate() {return enrollmentDate;}
    public EnrollmentStatus getStatus() {return status;}
    public void setStatus(EnrollmentStatus status) {this.status = status;}
}
