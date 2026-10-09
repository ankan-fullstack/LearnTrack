# LearnTrack - Design Notes

## 1. Why did we use ArrayList instead of an array?

LearnTrack uses `ArrayList` in the repository layer to store students, courses, and enrollments.

For example:

```java
private ArrayList<Student> students;
```

An `ArrayList` was chosen instead of a traditional array because the number of entities is not known in advance.

### Advantages of ArrayList

- Dynamic size: elements can be added without defining a fixed size beforehand.
- Easy insertion: `add()` can be used to add new entities.
- Easy iteration: it works naturally with enhanced `for` loops.
- Convenient collection operations: it provides methods such as `add()`, `isEmpty()`, and `size()`.
- Better suited for an application where students, courses, and enrollments can be added during runtime.

With a normal array, a fixed size would have to be defined when the array is created.

For example:

```java
Student[] students = new Student[100];
```

This would impose an arbitrary limit and would require additional handling when the capacity is reached.

Therefore, `ArrayList` is a better fit for the in-memory repository implementation used by LearnTrack.

---

## 2. Where did we use static members and why?

The main use of static members is in the `IdGenerator` utility.

```java
public class IdGenerator {
    private static Map<EntityType, Integer> idCounters = new HashMap<>();
}
```

The `getNextId()` method is also static:

```java
public static int getNextId(EntityType entityType) {
    int currentId = idCounters.getOrDefault(entityType, 0);
    int updatedId = currentId + 1;
    idCounters.put(entityType, updatedId);
    return updatedId;
}
```

### Why static?

`IdGenerator` does not represent an individual entity. It is a utility responsible for maintaining ID counters for the entire application.

A static counter map allows all callers to share the same ID state.

For example:

```text
Student IDs:
1
2
3

Course IDs:
1
2
3

Enrollment IDs:
1
2
3
```

The counters are maintained independently using `EntityType`.

Using static members avoids creating an `IdGenerator` object every time an ID is required.

---

## 3. Where did we use inheritance and what did we gain from it?

Inheritance is used between `Person` and `Student`.

```text
Person
  ↑
Student
```

`Person` contains common information:

```java
private int id;
private String firstName;
private String lastName;
private String email;
```

`Student` extends `Person`:

```java
public class Student extends Person {
    private String batch;
    private boolean active;
}
```

The `Student` constructor uses `super()` to initialize the properties inherited from `Person`:

```java
public Student(int id, String firstName, String lastName, String email,
               String batch, boolean active) {
    super(id, firstName, lastName, email);
    this.batch = batch;
    this.active = active;
}
```

### What did we gain?

#### Code reuse

Common person-related fields and methods are defined once in `Person` instead of being duplicated in `Student`.

For example:

```text
getId()
getFirstName()
getLastName()
getEmail()
```

are inherited by `Student`.

#### Better organization

`Person` represents information common to people, while `Student` contains student-specific information such as:

```text
batch
active
```

This keeps the entity models organized around their responsibilities.

#### Method overriding

`Student` overrides `getDisplayName()`:

```java
@Override
public String getDisplayName() {
    return super.getDisplayName() + "-" + this.batch;
}
```

The parent implementation provides the basic name, while `Student` extends it with the batch information.

This demonstrates inheritance together with method overriding.

---

## Clean Code Practices

The project follows simple Clean Code principles.

### Meaningful Names

Methods and variables use names that describe their purpose.

Examples:

```text
addStudent()
updateStudent()
getStudentById()
deleteStudent()

addCourse()
updateCourse()
getCourseById()
deleteCourse()

createEnrollment()
updateEnrollmentStatus()
getEnrollmentById()
```

These names communicate what the methods do instead of using vague names such as:

```text
fun1()
doWork()
process()
```

### Short, Focused Methods

Methods are kept focused on a specific responsibility.

For example:

```text
addStudent()
```

handles student input and creation, while:

```text
viewStudentById()
```

handles retrieving and displaying a student.

Similarly, menu methods such as:

```text
showStudentMenu()
showCourseMenu()
showEnrollmentMenu()
```

handle their respective menu flows.

### Separation of Responsibilities

Responsibilities are separated across layers:

```text
Main
  ↓
Service
  ↓
Repository
  ↓
Entity
```

`Main` handles the CLI interaction.

Services handle business logic.

Repositories handle in-memory data access.

Entities represent application data.

This prevents the entire application from being implemented inside `Main.java`.

### Centralized Validation

Input validation is handled by `InputValidator` rather than duplicating validation logic throughout the application.

Examples include:

```text
validatePositiveInt()
validateBoolean()
validateNonEmptyString()
validateEmail()
validateChoice()
```

This makes the validation logic reusable and easier to maintain.
