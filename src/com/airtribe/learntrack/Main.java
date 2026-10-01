package com.airtribe.learntrack;
import com.airtribe.learntrack.entity.Person;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EntityType;
import com.airtribe.learntrack.util.IdGenerator;

public class Main {
    public static void main(String[] args) {
        // Create a new Student object
        // Person student = new Student(
        //     1, 
        //     "Ankan", 
        //     "Biswas", 
        //     "ankan.biswas@example.com", 
        //     "BEL-16-Java", 
        //     true
        // );

        // // Print the student's details
        // // System.out.println("First Name: " + student.getFirstName());
        // // System.out.println("Last Name: " + student.getLastName());
        // // System.out.println("Batch: " + student.getBatch());
        // // System.out.println("Active: " + student.isActive());
        // System.out.println("Display Name: " + student.getDisplayName());
        System.out.println("Next Student ID: " + IdGenerator.getNextId(EntityType.STUDENT));
        System.out.println("Next Student ID: " + IdGenerator.getNextId(EntityType.STUDENT));
        System.out.println("Next Course ID: " + IdGenerator.getNextId(EntityType.COURSE));
        System.out.println("Next Enrollment ID: " + IdGenerator.getNextId(EntityType.ENROLLMENT));
    }
}
