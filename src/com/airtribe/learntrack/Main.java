package com.airtribe.learntrack;

import java.util.List;
import java.util.Scanner;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        StudentService studentService = new StudentService(studentRepository);
        CourseService courseService = new CourseService(courseRepository);
        EnrollmentService enrollmentService = new EnrollmentService(
                enrollmentRepository, studentRepository, courseRepository);

        while (true) {
            showMainMenu();
            try {
                int choice = InputValidator.validateChoice(scanner.nextLine(), 1, 4);

                switch (choice) {
                    case MenuOptions.STUDENT:
                        showStudentMenu(scanner, studentService);
                        break;
                    case MenuOptions.COURSE:
                        showCourseMenu(scanner, courseService);
                        break;
                    case MenuOptions.ENROLLMENT:
                        showEnrollmentMenu(scanner, enrollmentService);
                        break;
                    case MenuOptions.EXIT:
                        System.out.println("Thank you for using " + AppConstants.APP_NAME + "!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    private static void showMainMenu() {
        System.out.println();
        System.out.println("========== " + AppConstants.APP_NAME + " ==========");
        System.out.println(AppConstants.SEPARATOR);
        System.out.println(MenuOptions.STUDENT + ". Student");
        System.out.println(MenuOptions.COURSE + ". Course");
        System.out.println(MenuOptions.ENROLLMENT + ". Enrollment");
        System.out.println(MenuOptions.EXIT + ". Exit");
    }

    private static void showStudentMenu(Scanner scanner, StudentService studentService) {
        while (true) {
            System.out.println();
            System.out.println("========== Student ==========");
            System.out.println(AppConstants.SEPARATOR);
            System.out.println(MenuOptions.STUDENT_ADD + ". Add Student");
            System.out.println(MenuOptions.STUDENT_UPDATE + ". Update Student");
            System.out.println(MenuOptions.STUDENT_VIEW + ". View Student");
            System.out.println(MenuOptions.STUDENT_DEACTIVATE + ". Deactivate Student");
            System.out.println(MenuOptions.STUDENT_BACK + ". Back");

            try {
                int choice = InputValidator.validateChoice(scanner.nextLine(), 1, 5);

                switch (choice) {
                    case MenuOptions.STUDENT_ADD:
                        addStudent(scanner, studentService);
                        break;
                    case MenuOptions.STUDENT_UPDATE:
                        updateStudent(scanner, studentService);
                        break;
                    case MenuOptions.STUDENT_VIEW:
                        showStudentViewMenu(scanner, studentService);
                        break;
                    case MenuOptions.STUDENT_DEACTIVATE:
                        deactivateStudent(scanner, studentService);
                        break;
                    case MenuOptions.STUDENT_BACK:
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    private static void addStudent(Scanner scanner, StudentService studentService) {
        try {
            System.out.println();
            System.out.println("========== Add Student ==========");

            System.out.print("First Name: ");
            String firstName = InputValidator.validateNonEmptyString(scanner.nextLine());

            System.out.print("Last Name: ");
            String lastName = InputValidator.validateNonEmptyString(scanner.nextLine());

            System.out.print("Email: ");
            String email = InputValidator.validateEmail(scanner.nextLine());

            System.out.print("Batch: ");
            String batch = InputValidator.validateNonEmptyString(scanner.nextLine());

            studentService.addStudent(firstName, lastName, email, batch);
            System.out.println("Student added successfully.");
        } catch (InvalidInputException e) {
            System.out.println("Unable to add student: " + e.getMessage());
        }
    }

    private static void updateStudent(Scanner scanner, StudentService studentService) {
        try {
            System.out.println();
            System.out.println("========== Update Student ==========");

            System.out.print("Student ID: ");
            int id = InputValidator.validatePositiveInt(scanner.nextLine());

            System.out.print("New Batch: ");
            String batch = InputValidator.validateNonEmptyString(scanner.nextLine());

            System.out.print("Is Student Active? (true/false): ");
            boolean active = InputValidator.validateBoolean(scanner.nextLine());

            studentService.updateStudent(id, batch, active);
            System.out.println("Student updated successfully.");
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Student not found: " + e.getMessage());
        }
    }

    private static void showStudentViewMenu(Scanner scanner, StudentService studentService) {
        while (true) {
            System.out.println();
            System.out.println("========== View Student ==========");
            System.out.println(AppConstants.SEPARATOR);
            System.out.println(MenuOptions.STUDENT_VIEW_BY_ID + ". View by ID");
            System.out.println(MenuOptions.STUDENT_VIEW_ALL + ". View All");
            System.out.println(MenuOptions.STUDENT_VIEW_BACK + ". Back");

            try {
                int choice = InputValidator.validateChoice(scanner.nextLine(), 1, 3);

                switch (choice) {
                    case MenuOptions.STUDENT_VIEW_BY_ID:
                        viewStudentById(scanner, studentService);
                        break;
                    case MenuOptions.STUDENT_VIEW_ALL:
                        viewAllStudents(studentService);
                        break;
                    case MenuOptions.STUDENT_VIEW_BACK:
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    private static void viewStudentById(Scanner scanner, StudentService studentService) {
        try {
            System.out.print("Student ID: ");
            int id = InputValidator.validatePositiveInt(scanner.nextLine());
            Student student = studentService.getStudentById(id);
            printStudent(student);
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewAllStudents(StudentService studentService) {
        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println();
        System.out.println("========== All Students ==========");

        for (Student student : students) {
            printStudent(student);
        }
    }

    private static void printStudent(Student student) {
        System.out.println(AppConstants.SEPARATOR);
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getDisplayName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Batch: " + student.getBatch());
        System.out.println("Active: " + student.isActive());
    }

    private static void deactivateStudent(Scanner scanner, StudentService studentService) {
        try {
            System.out.println();
            System.out.println("========== Deactivate Student ==========");

            System.out.print("Student ID: ");
            int id = InputValidator.validatePositiveInt(scanner.nextLine());

            studentService.deleteStudent(id);
            System.out.println("Student deactivated successfully.");
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showCourseMenu(Scanner scanner, CourseService courseService) {
        while (true) {
            System.out.println();
            System.out.println("========== Course ==========");
            System.out.println(AppConstants.SEPARATOR);
            System.out.println(MenuOptions.COURSE_ADD + ". Add Course");
            System.out.println(MenuOptions.COURSE_UPDATE + ". Update Course");
            System.out.println(MenuOptions.COURSE_VIEW + ". View Course");
            System.out.println(MenuOptions.COURSE_DEACTIVATE + ". Deactivate Course");
            System.out.println(MenuOptions.COURSE_BACK + ". Back");

            try {
                int choice = InputValidator.validateChoice(scanner.nextLine(), 1, 5);

                switch (choice) {
                    case MenuOptions.COURSE_ADD:
                        addCourse(scanner, courseService);
                        break;
                    case MenuOptions.COURSE_UPDATE:
                        updateCourse(scanner, courseService);
                        break;
                    case MenuOptions.COURSE_VIEW:
                        showCourseViewMenu(scanner, courseService);
                        break;
                    case MenuOptions.COURSE_DEACTIVATE:
                        deactivateCourse(scanner, courseService);
                        break;
                    case MenuOptions.COURSE_BACK:
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    private static void addCourse(Scanner scanner, CourseService courseService) {
        try {
            System.out.println();
            System.out.println("========== Add Course ==========");

            System.out.print("Course Name: ");
            String name = InputValidator.validateNonEmptyString(scanner.nextLine());

            System.out.print("Description: ");
            String description = InputValidator.validateNonEmptyString(scanner.nextLine());

            System.out.print("Duration in Weeks: ");
            int duration = InputValidator.validatePositiveInt(scanner.nextLine());

            courseService.addCourse(name, description, duration);
            System.out.println("Course added successfully.");
        } catch (InvalidInputException e) {
            System.out.println("Unable to add course: " + e.getMessage());
        }
    }

    private static void updateCourse(Scanner scanner, CourseService courseService) {
        try {
            System.out.println();
            System.out.println("========== Update Course ==========");

            System.out.print("Course ID: ");
            int id = InputValidator.validatePositiveInt(scanner.nextLine());

            System.out.print("New Description: ");
            String description = InputValidator.validateNonEmptyString(scanner.nextLine());

            System.out.print("Is Course Active? (true/false): ");
            boolean active = InputValidator.validateBoolean(scanner.nextLine());

            courseService.updateCourse(id, description, active);
            System.out.println("Course updated successfully.");
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Course not found: " + e.getMessage());
        }
    }

    private static void showCourseViewMenu(Scanner scanner, CourseService courseService) {
        while (true) {
            System.out.println();
            System.out.println("========== View Course ==========");
            System.out.println(AppConstants.SEPARATOR);
            System.out.println(MenuOptions.COURSE_VIEW_BY_ID + ". View by ID");
            System.out.println(MenuOptions.COURSE_VIEW_ALL + ". View All");
            System.out.println(MenuOptions.COURSE_VIEW_BACK + ". Back");

            try {
                int choice = InputValidator.validateChoice(scanner.nextLine(), 1, 3);

                switch (choice) {
                    case MenuOptions.COURSE_VIEW_BY_ID:
                        viewCourseById(scanner, courseService);
                        break;
                    case MenuOptions.COURSE_VIEW_ALL:
                        viewAllCourses(courseService);
                        break;
                    case MenuOptions.COURSE_VIEW_BACK:
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    private static void viewCourseById(Scanner scanner, CourseService courseService) {
        try {
            System.out.print("Course ID: ");
            int id = InputValidator.validatePositiveInt(scanner.nextLine());
            Course course = courseService.getCourseById(id);
            printCourse(course);
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewAllCourses(CourseService courseService) {
        List<Course> courses = courseService.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println();
        System.out.println("========== All Courses ==========");

        for (Course course : courses) {
            printCourse(course);
        }
    }

    private static void printCourse(Course course) {
        System.out.println(AppConstants.SEPARATOR);
        System.out.println("ID: " + course.getId());
        System.out.println("Name: " + course.getCourseName());
        System.out.println("Description: " + course.getDescription());
        System.out.println("Duration: " + course.getDurationInWeeks() + " weeks");
        System.out.println("Active: " + course.isActive());
    }

    private static void deactivateCourse(Scanner scanner, CourseService courseService) {
        try {
            System.out.println();
            System.out.println("========== Deactivate Course ==========");

            System.out.print("Course ID: ");
            int id = InputValidator.validatePositiveInt(scanner.nextLine());

            courseService.deleteCourse(id);
            System.out.println("Course deactivated successfully.");
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showEnrollmentMenu(Scanner scanner, EnrollmentService enrollmentService) {
        while (true) {
            System.out.println();
            System.out.println("========== Enrollment ==========");
            System.out.println(AppConstants.SEPARATOR);
            System.out.println(MenuOptions.ENROLLMENT_CREATE + ". Create Enrollment");
            System.out.println(MenuOptions.ENROLLMENT_UPDATE_STATUS + ". Update Status");
            System.out.println(MenuOptions.ENROLLMENT_VIEW + ". View Enrollment");
            System.out.println(MenuOptions.ENROLLMENT_CANCEL + ". Cancel Enrollment");
            System.out.println(MenuOptions.ENROLLMENT_BACK + ". Back");

            try {
                int choice = InputValidator.validateChoice(scanner.nextLine(), 1, 5);

                switch (choice) {
                    case MenuOptions.ENROLLMENT_CREATE:
                        createEnrollment(scanner, enrollmentService);
                        break;
                    case MenuOptions.ENROLLMENT_UPDATE_STATUS:
                        updateEnrollmentStatus(scanner, enrollmentService);
                        break;
                    case MenuOptions.ENROLLMENT_VIEW:
                        showEnrollmentViewMenu(scanner, enrollmentService);
                        break;
                    case MenuOptions.ENROLLMENT_CANCEL:
                        cancelEnrollment(scanner, enrollmentService);
                        break;
                    case MenuOptions.ENROLLMENT_BACK:
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    private static void createEnrollment(Scanner scanner, EnrollmentService enrollmentService) {
        try {
            System.out.println();
            System.out.println("========== Create Enrollment ==========");

            System.out.print("Student ID: ");
            int studentId = InputValidator.validatePositiveInt(scanner.nextLine());

            System.out.print("Course ID: ");
            int courseId = InputValidator.validatePositiveInt(scanner.nextLine());

            enrollmentService.createEnrollment(studentId, courseId);
            System.out.println("Enrollment created successfully.");
        } catch (InvalidInputException e) {
            System.out.println("Unable to create enrollment: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateEnrollmentStatus(Scanner scanner, EnrollmentService enrollmentService) {
        try {
            System.out.println();
            System.out.println("========== Update Enrollment Status ==========");

            System.out.print("Enrollment ID: ");
            int id = InputValidator.validatePositiveInt(scanner.nextLine());

            System.out.println("1. ACTIVE");
            System.out.println("2. COMPLETED");
            System.out.println("3. CANCELLED");

            int statusChoice = InputValidator.validateChoice(scanner.nextLine(), 1, 3);
            EnrollmentStatus status;

            switch (statusChoice) {
                case 1:
                    status = EnrollmentStatus.ACTIVE;
                    break;
                case 2:
                    status = EnrollmentStatus.COMPLETED;
                    break;
                case 3:
                    status = EnrollmentStatus.CANCELLED;
                    break;
                default:
                    throw new InvalidInputException("Invalid enrollment status.");
            }

            enrollmentService.updateEnrollmentStatus(id, status);
            System.out.println("Enrollment status updated successfully.");
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showEnrollmentViewMenu(
            Scanner scanner,
            EnrollmentService enrollmentService) {

        while (true) {
            System.out.println();
            System.out.println("========== View Enrollment ==========");
            System.out.println(AppConstants.SEPARATOR);
            System.out.println(MenuOptions.ENROLLMENT_VIEW_BY_ID + ". View by ID");
            System.out.println(MenuOptions.ENROLLMENT_VIEW_ALL + ". View All");
            System.out.println(MenuOptions.ENROLLMENT_VIEW_BACK + ". Back");

            try {
                int choice = InputValidator.validateChoice(scanner.nextLine(), 1, 3);

                switch (choice) {
                    case MenuOptions.ENROLLMENT_VIEW_BY_ID:
                        viewEnrollmentById(scanner, enrollmentService);
                        break;
                    case MenuOptions.ENROLLMENT_VIEW_ALL:
                        viewAllEnrollments(enrollmentService);
                        break;
                    case MenuOptions.ENROLLMENT_VIEW_BACK:
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    private static void viewEnrollmentById(
            Scanner scanner,
            EnrollmentService enrollmentService) {

        try {
            System.out.print("Enrollment ID: ");
            int id = InputValidator.validatePositiveInt(scanner.nextLine());
            Enrollment enrollment = enrollmentService.getEnrollmentById(id);
            printEnrollment(enrollment);
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewAllEnrollments(EnrollmentService enrollmentService) {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }
        System.out.println();
        System.out.println("========== All Enrollments ==========");
        for (Enrollment enrollment : enrollments) {
            printEnrollment(enrollment);
        }
    }

    private static void printEnrollment(Enrollment enrollment) {
        System.out.println(AppConstants.SEPARATOR);
        System.out.println("ID: " + enrollment.getId());
        System.out.println("Student ID: " + enrollment.getStudentId());
        System.out.println("Course ID: " + enrollment.getCourseId());
        System.out.println("Enrollment Date: " + enrollment.getEnrollmentDate());
        System.out.println("Status: " + enrollment.getStatus());
    }

    private static void cancelEnrollment(Scanner scanner, EnrollmentService enrollmentService) {
        try {
            System.out.println();
            System.out.println("========== Cancel Enrollment ==========");
            System.out.print("Enrollment ID: ");
            int id = InputValidator.validatePositiveInt(scanner.nextLine());
            enrollmentService.disableEnrollment(id);
            System.out.println("Enrollment cancelled successfully.");
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}