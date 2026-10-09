package com.airtribe.learntrack.service;

import java.util.List;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.EntityType;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    public void addCourse(String name, String description, int duration) {
        int id = IdGenerator.getNextId(EntityType.COURSE);
        courseRepository.save(new Course(id, name, description, duration, true));
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getActiveCourses() {
        return courseRepository.findAllActive();
    }

    public void updateCourse(int id, String description, boolean isActive) {
        Course course = courseRepository.findById(id);
        course.setDescription(description);
        course.setActive(isActive);
        courseRepository.update(course);
    }

    public void deleteCourse(int id) {
        courseRepository.delete(id);
    }
}
