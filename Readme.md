# LearnTrack

LearnTrack is a console-based Student & Course Management System built using Core Java.

The application provides a menu-driven interface for managing students, courses, and course enrollments. The project demonstrates Core Java concepts such as object-oriented programming, collections, exception handling, enums, layered architecture, and dependency injection.

---

## Features

### Student Management

- Add student
- View student by ID
- View all students
- Update student batch and active status
- Deactivate student
- Validate student input

### Course Management

- Add course
- View course by ID
- View all courses
- Update course description and active status
- Deactivate course
- Validate course input

### Enrollment Management

- Create enrollment
- View enrollment by ID
- View all enrollments
- Update enrollment status
- Cancel enrollment
- Prevent enrollment of inactive students
- Prevent enrollment into inactive courses

### Input Validation

- Positive integer validation
- Boolean validation
- Non-empty string validation
- Email validation
- Menu choice validation

### Exception Handling

Custom exceptions are used for application-specific errors:

- `EntityNotFoundException`
- `InvalidInputException`

---

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 17 | Application development |
| ArrayList | In-memory data storage |
| Java Collections | Data management |
| LocalDate | Enrollment dates |
| Git | Version control |
| GitHub | Source code hosting |

---

## Architecture

LearnTrack follows a layered architecture:

```text
                    Main
                     |
          +----------+----------+
          |          |          |
       Student     Course    Enrollment
       Service     Service     Service
          |          |          |
       Student     Course    Enrollment
      Repository  Repository Repository
          |          |          |
       Student     Course    Enrollment
        Entity      Entity     Entity
```

### Entity Layer

Contains the domain models:

- `Person`
- `Student`
- `Course`
- `Enrollment`

`Student` extends `Person` to demonstrate inheritance.

### Repository Layer

Responsible for in-memory data storage and retrieval.

Repositories:

- `StudentRepository`
- `CourseRepository`
- `EnrollmentRepository`

Repository operations include:

- Save
- Find by ID
- Find all
- Update
- Delete/deactivate
- Find active entities where applicable

### Service Layer

Contains application and business logic.

Services:

- `StudentService`
- `CourseService`
- `EnrollmentService`

The service layer coordinates repositories and applies business rules.

For example, before creating an enrollment, `EnrollmentService` verifies that:

1. The student exists.
2. The student is active.
3. The course exists.
4. The course is active.

### Utility Layer

Contains reusable utilities:

- `IdGenerator`
- `InputValidator`

`IdGenerator` maintains independent sequential IDs for students, courses, and enrollments.

### Exception Layer

Contains custom runtime exceptions:

- `EntityNotFoundException`
- `InvalidInputException`

### Constants & Enums

Constants:

- `AppConstants`
- `MenuOptions`

Enums:

- `EntityType`
- `EnrollmentStatus`

### Application Entry Point

`Main.java`:

- Initializes repositories
- Initializes services
- Starts the application
- Displays menus
- Reads user input
- Invokes service operations
- Handles user-facing exceptions

---

## Project Structure

```text
LearnTrack/
│
├── src/
│   └── com/
│       └── airtribe/
│           └── learntrack/
│               │
│               ├── Main.java
│               │
│               ├── constants/
│               │   ├── AppConstants.java
│               │   └── MenuOptions.java
│               │
│               ├── entity/
│               │   ├── Person.java
│               │   ├── Student.java
│               │   ├── Course.java
│               │   └── Enrollment.java
│               │
│               ├── enums/
│               │   ├── EntityType.java
│               │   └── EnrollmentStatus.java
│               │
│               ├── exception/
│               │   ├── EntityNotFoundException.java
│               │   └── InvalidInputException.java
│               │
│               ├── repository/
│               │   ├── StudentRepository.java
│               │   ├── CourseRepository.java
│               │   └── EnrollmentRepository.java
│               │
│               ├── service/
│               │   ├── StudentService.java
│               │   ├── CourseService.java
│               │   └── EnrollmentService.java
│               │
│               └── util/
│                   ├── IdGenerator.java
│                   └── InputValidator.java
│
└── README.md
```

---

## Prerequisites

- Java 17 or later
- Git

Verify Java:

```bash
java --version
```

Verify the compiler:

```bash
javac --version
```

---

## Setup

Clone the repository:

```bash
git clone https://github.com/ankan-fullstack/LearnTrack.git
```

Navigate to the project:

```bash
cd LearnTrack
```

---

## Compile

From the project root:

```bash
javac -d out $(find src -name "*.java")
```

This compiles all Java source files and places the generated `.class` files inside the `out` directory.

---

## Run

```bash
java -cp out com.airtribe.learntrack.Main
```

The application starts with the main menu:

```text
========== LearnTrack ==========
------------------------------
1. Student
2. Course
3. Enrollment
4. Exit
```

---

# Application Usage

## 1. Student Management

Select:

```text
1. Student
```

Available operations:

```text
1. Add Student
2. Update Student
3. View Student
4. Deactivate Student
5. Back
```

### Add Student

The application asks for:

- First name
- Last name
- Email
- Batch

A unique student ID is generated automatically.

### Update Student

The application asks for:

- Student ID
- New batch
- Active status

### View Student

Options:

```text
1. View by ID
2. View All
3. Back
```

### Deactivate Student

The student is marked inactive rather than physically removed from the repository.

---

# 2. Course Management

Select:

```text
2. Course
```

Available operations:

```text
1. Add Course
2. Update Course
3. View Course
4. Deactivate Course
5. Back
```

### Add Course

The application asks for:

- Course name
- Description
- Duration in weeks

A unique course ID is generated automatically.

### Update Course

The application asks for:

- Course ID
- New description
- Active status

### View Course

Options:

```text
1. View by ID
2. View All
3. Back
```

### Deactivate Course

The course is marked inactive rather than physically removed.

---

# 3. Enrollment Management

Select:

```text
3. Enrollment
```

Available operations:

```text
1. Create Enrollment
2. Update Status
3. View Enrollment
4. Cancel Enrollment
5. Back
```

### Create Enrollment

The application asks for:

- Student ID
- Course ID

Before creating the enrollment, the application verifies that:

- The student exists.
- The student is active.
- The course exists.
- The course is active.

A unique enrollment ID is generated automatically.

The initial enrollment status is:

```text
ACTIVE
```

### Update Enrollment Status

Available statuses:

```text
1. ACTIVE
2. COMPLETED
3. CANCELLED
```

### View Enrollment

Options:

```text
1. View by ID
2. View All
3. Back
```

### Cancel Enrollment

Cancelling an enrollment changes its status to:

```text
CANCELLED
```

---

# Enrollment Lifecycle

An enrollment starts as:

```text
ACTIVE
```

It can be updated to:

```text
ACTIVE
COMPLETED
CANCELLED
```

Example:

```text
Create
  ↓
ACTIVE
  ↓
COMPLETED
```

or:

```text
Create
  ↓
ACTIVE
  ↓
CANCELLED
```

---

# Data Storage

LearnTrack currently uses **in-memory storage**.

Repositories use Java collections such as `ArrayList`.

Therefore:

- No database is required.
- Data exists only while the application is running.
- Restarting the application clears all application data.
- Repository objects are shared with the corresponding services.

---

# ID Generation

`IdGenerator` generates sequential IDs independently for each entity type.

Example:

```text
Students:
1
2
3

Courses:
1
2
3

Enrollments:
1
2
3
```

Student, course, and enrollment IDs therefore maintain separate sequences.

---

# Validation

Input validation is centralized in `InputValidator`.

### Positive Integer

Used for:

- IDs
- Duration
- Menu selections

### Boolean

Accepted values:

```text
true
false
```

The validation is case-insensitive.

### Non-empty String

Rejects:

- Empty strings
- Strings containing only whitespace

### Email

Validates that the supplied value follows a basic email format.

### Menu Choice

Validates that the selected option falls within the expected menu range.

---

# Exception Handling

## EntityNotFoundException

Thrown when an entity cannot be found using the supplied ID.

Example:

```text
Student not found with ID: 10
```

## InvalidInputException

Thrown when user input does not satisfy the expected validation rules.

Example:

```text
Input must be a positive integer.
```

The `Main` class catches these exceptions and displays user-friendly messages.

---

# Soft Delete / Deactivation

Students and courses are not physically removed from the in-memory repository.

Instead:

```text
Student
active = false
```

and:

```text
Course
active = false
```

For enrollments, cancellation changes the status to:

```text
CANCELLED
```

This allows the original entity to remain available in the repository.

---

# Object-Oriented Concepts Demonstrated

The project demonstrates the following Core Java concepts.

### Encapsulation

Entity fields are private and accessed through methods.

### Inheritance

```text
Person
  ↑
Student
```

`Student` inherits common person information from `Person`.

### Method Overriding

`Student` overrides `getDisplayName()` from `Person`.

### Constructors

Entities use constructors to initialize their state.

### Static Members

`IdGenerator` uses static state to maintain ID counters.

### Enums

Enums provide controlled values for:

- Entity types
- Enrollment statuses

### Collections

`ArrayList` is used for in-memory repository storage.

### Exception Handling

Custom runtime exceptions are used to represent application-specific failures.

### Dependency Injection

Services receive repositories through constructors.

For example:

```java
new StudentService(studentRepository)
```

This allows the same repository instance to be shared and keeps the service independent of repository creation.

---

# Testing

The application was manually tested across the main functional flows.

## Student Testing

- Add student
- Retrieve student by ID
- Retrieve all students
- Update student
- Deactivate student
- Retrieve non-existent student
- Validate invalid input

## Course Testing

- Add course
- Retrieve course by ID
- Retrieve all courses
- Update course
- Deactivate course
- Retrieve non-existent course
- Validate invalid input

## Enrollment Testing

- Create enrollment
- Retrieve enrollment
- Retrieve all enrollments
- Update enrollment status
- Cancel enrollment
- Attempt enrollment with inactive student
- Attempt enrollment with inactive course
- Attempt enrollment with non-existent student
- Attempt enrollment with non-existent course

## Input Validation Testing

Tested cases include:

```text
Valid positive integer
Zero
Negative integer
Invalid numeric input
Valid boolean
Invalid boolean
Valid string
Empty string
Whitespace-only string
Valid email
Invalid email
Valid menu choice
Out-of-range menu choice
```

---

# Design Decisions

### In-memory repositories

The project focuses on Core Java concepts, so persistence is intentionally kept out of scope.

### Service layer

Business rules are placed in services rather than repositories.

For example, enrollment validation belongs to `EnrollmentService`.

### Shared repositories

`Main` creates the repositories once and injects them into the services.

This ensures that different services operate on the same in-memory data.

### Soft deletion

Students, courses, and enrollments are retained while their active/status state changes.

### Centralized validation

Input validation is handled through `InputValidator` instead of duplicating validation logic throughout `Main`.

### Constants

Menu options and application-level constants are centralized in dedicated classes.

---

# Limitations

The current version intentionally has some limitations:

- No persistent database
- No authentication
- No REST API
- No web interface
- No concurrent access support
- Data is lost when the application exits
- No automated test framework is currently included

---

# Future Improvements

Possible future enhancements include:

- PostgreSQL/MySQL persistence
- JDBC or JPA integration
- REST API using Spring Boot
- Authentication and authorization
- Search and filtering
- Pagination
- Automated unit tests using JUnit
- Logging
- Configuration management
- Reporting and analytics
- Web-based frontend

---

# Git Workflow

Development was performed using a feature-branch workflow.

```text
main
  ↑
  │ Pull Request
  │
feature/learntrack
```

Implementation was developed on `feature/learntrack` and merged into `main` through a Pull Request.

---

# Author

**Ankan Biswas**

GitHub: https://github.com/ankan-fullstack
