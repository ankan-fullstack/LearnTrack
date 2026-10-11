package com.airtribe.learntrack.repository;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;

public class EnrollmentRepository {
    private List<Enrollment> enrollments;

    public EnrollmentRepository() {
        this.enrollments = new ArrayList<>();
    }

    public void save(Enrollment enrollment) {
        for (Enrollment existingEnrollment : enrollments) {
            if (existingEnrollment.getId() == enrollment.getId()) {
                throw new InvalidInputException("Enrollment with ID " + enrollment.getId() + " already exists.");
            }
        }

        enrollments.add(enrollment);
    }

    public Enrollment findById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment not found with ID: " + id);
    }

    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments);
    }

    public void update(Enrollment enrollment) {
        Enrollment existingEnrollment = findById(enrollment.getId());
        existingEnrollment.setStatus(enrollment.getStatus());
        existingEnrollment.setEnrollmentDate(enrollment.getEnrollmentDate());
        existingEnrollment.setStudentId(enrollment.getStudentId());
        existingEnrollment.setCourseId(enrollment.getCourseId());
    }

    public void delete(int id) {
        Enrollment enrollment = findById(id);
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
    }

    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                studentEnrollments.add(enrollment);
            }
        }
        return studentEnrollments;
    }

}
