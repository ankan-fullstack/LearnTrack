package com.airtribe.learntrack.repository;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

public class StudentRepository {
    private ArrayList<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
    }

    public void save(Student student) {
        students.add(student);
    }

    public void update(Student student) {
        Student existingStudent = findById(student.getId());
        existingStudent.setActive(student.isActive());
        existingStudent.setBatch(student.getBatch());
    }

    public void delete(int id) {
        Student student = findById(id);
        student.setActive(false);
    }

    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student not found with ID: " + id);
    }

    public List<Student> findAllActive() {
        List<Student> activeStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.isActive()) {
                activeStudents.add(student);
            }
        }
        return activeStudents;
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }
}
