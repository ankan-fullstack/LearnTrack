package com.airtribe.learntrack.service;

import java.time.LocalDate;
import java.util.List;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.enums.EntityType;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;

public class EnrollmentService {
    private EnrollmentRepository enrollmentRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public void createEnrollment(int studentId, int courseId) {
        Student student = studentRepository.findById(studentId);
        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll an inactive student with ID: " + studentId);
        }
        Course course = courseRepository.findById(courseId);
        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in an inactive course with ID: " + courseId);
        }

        int id = IdGenerator.getNextId(EntityType.ENROLLMENT);
        LocalDate enrollmentDate = LocalDate.now();
        enrollmentRepository.save(new Enrollment(id, studentId, courseId, enrollmentDate, EnrollmentStatus.ACTIVE));
    }

    public Enrollment getEnrollmentById(int id) {
        return enrollmentRepository.findById(id);
    }

    public void updateEnrollmentStatus(int id, EnrollmentStatus status) {
        Enrollment enrollment = enrollmentRepository.findById(id);
        enrollment.setStatus(status);
        enrollmentRepository.update(enrollment);
    }

    public void disableEnrollment(int id) {
        enrollmentRepository.delete(id);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}
