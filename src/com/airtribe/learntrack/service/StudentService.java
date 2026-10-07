package com.airtribe.learntrack.service;

import java.util.List;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EntityType;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;

public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(String firstName, String lastName, String email, String batch) {
        int id = IdGenerator.getNextId(EntityType.STUDENT);
        Student student = new Student(id, firstName, lastName, email, batch, true);
        studentRepository.save(student);
    }

    public void updateStudent(int id, String batch, boolean isActive) {
        Student existingStudent = studentRepository.findById(id);
        existingStudent.setBatch(batch);
        existingStudent.setActive(isActive);
        studentRepository.update(existingStudent);
        
    }

    public void deleteStudent(int id) {
        studentRepository.delete(id);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getAllActiveStudents() {
        return studentRepository.findAllActive();
    }
}
