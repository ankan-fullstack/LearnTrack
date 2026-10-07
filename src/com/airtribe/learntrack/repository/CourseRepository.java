package com.airtribe.learntrack.repository;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;

public class CourseRepository {
    private ArrayList<Course> courses;

    public CourseRepository() {
        this.courses = new ArrayList<>();
    }

    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course not found with ID: " + id);
    }

    public void save(Course course) {
        courses.add(course);
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public List<Course> findAllActive() {
        List<Course> activeCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.isActive()) {
                activeCourses.add(course);
            }
        }
        return activeCourses;
    }

    public void update(Course course) {
        Course existingCourse = findById(course.getId());
        existingCourse.setActive(course.isActive());
        existingCourse.setDescription(course.getDescription());
    }

    public void delete(int id) {
        Course course = findById(id);
        course.setActive(false);
    }
}