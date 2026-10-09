package com.airtribe.learntrack.repository;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;

public class EnrollmentRepository {
    private List<Enrollment> enrollments;

    public EnrollmentRepository() {
        this.enrollments = new ArrayList<>();
    }

    public void save(Enrollment enrollment) {
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
    }

    public void delete(int id) {
        Enrollment enrollment = findById(id);
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
    }

}
