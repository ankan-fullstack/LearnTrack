package com.airtribe.learntrack;
import java.time.LocalDate;
import java.util.List;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Person;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.enums.EntityType;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;

public class Main {
    public static void main(String[] args) {

        // --------------------------------
        // Create shared repositories
        // --------------------------------
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        // --------------------------------
        // Create services using
        // the same repositories
        // --------------------------------
        StudentService studentService =
                new StudentService(studentRepository);

        CourseService courseService =
                new CourseService(courseRepository);

        EnrollmentService enrollmentService =
                new EnrollmentService(
                        enrollmentRepository,
                        studentRepository,
                        courseRepository
                );

        // --------------------------------
        // Create Student through Service
        // --------------------------------
        studentService.addStudent(
                "John",
                "Doe",
                "john.doe@example.com",
                "BEL13-JAVA"
        );

        // --------------------------------
        // Create Course through Service
        // --------------------------------
        courseService.addCourse(
                "Java",
                "Core Java Programming",
                8
        );

        // --------------------------------
        // Verify Student
        // --------------------------------
        System.out.println("Student:");

        Student student = studentService.getStudentById(1);

        System.out.println(
                student.getId()
                        + " - "
                        + student.getDisplayName()
                        + " - "
                        + student.getBatch()
                        + " - Active: "
                        + student.isActive()
        );

        // --------------------------------
        // Verify Course
        // --------------------------------
        System.out.println("\nCourse:");

        Course course = courseService.getCourseById(1);

        System.out.println(
                course.getId()
                        + " - "
                        + course.getCourseName()
                        + " - "
                        + course.getDescription()
                        + " - Active: "
                        + course.isActive()
        );

        // --------------------------------
        // Create Enrollment
        // --------------------------------
        enrollmentService.createEnrollment(
                student.getId(),
                course.getId()
        );

        // --------------------------------
        // Verify Enrollment
        // --------------------------------
        System.out.println("\nEnrollment:");

        Enrollment enrollment = enrollmentService.getEnrollmentById(1);

        System.out.println(
                "Enrollment ID: " + enrollment.getId()
                        + " - Student ID: " + enrollment.getStudentId()
                        + " - Course ID: " + enrollment.getCourseId()
                        + " - Date: " + enrollment.getEnrollmentDate()
                        + " - Status: " + enrollment.getStatus()
        );
    }
}
