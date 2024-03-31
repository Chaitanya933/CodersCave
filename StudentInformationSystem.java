import java.util.*;

// Class to represent a Student
class Student {
    private String id;
    private String name;
    private int age;
    private String email;
    //Method for initializing the student details
    public Student(String id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
    //Using Get methods 
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getEmail() {
        return email;
    }
    // Override toString() for easy printing in furthur program
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}

//Defining a Class to represent a Course
class Course {
    private String courseId;
    private String courseName;
    //Initializations for course details
    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }
    // Get method for class course
    public String getCourseId() {
        return courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    // Override toString() for easy printing in further cases
    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
//Defining a class to represent an Enrollment (relationship between Student and Course)
class Enrollment {
    private Student student;
    private Course course;
    private int grade;
    //Initializations for enrolling a course by student
    public Enrollment(Student student, Course course, int grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }
    // Get methods for class enrollment
    public Student getStudent() {
        return student;
    }
    public Course getCourse() {
        return course;
    }
    public int getGrade() {
        return grade;
    }
    // Override toString() for easy printing
    @Override
    public String toString() {
        return "Enrollment{" +
                "student=" + student +
                ", course=" + course +
                ", grade=" + grade +
                '}';
    }
}
// Main class to working and display info of our Student Information System(SIS)
public class StudentInformationSystem {
    private List<Student> students;
    private List<Course> courses;
    private List<Enrollment> enrollments;
    //Declaring lists for storing student,course and enrollments details
    public StudentInformationSystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        enrollments = new ArrayList<>();
    }
    //Defining Method to add a new student
    public void addStudent(Student student) {
        students.add(student);
    }
    //Defining Method to add a new course
    public void addCourse(Course course) {
        courses.add(course);
    }
    //Defining Method to enroll a student in a course
    public void enrollStudent(Student student, Course course, int grade) {
        Enrollment enrollment = new Enrollment(student, course, grade);
        enrollments.add(enrollment);
    }
    //Defining Method to print all students
    public void printAllStudents() {
        System.out.println("All Students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
    //Defining Method to print all courses
    public void printAllCourses() {
        System.out.println("All Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }
    //Defining Method to print all enrollments
    public void printAllEnrollments() {
        System.out.println("All Enrollments:");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }
    //Defining Main method to interact with the main class
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentInformationSystem sis = new StudentInformationSystem();
        //Adding some initial students and courses for knowledge purpose
        sis.addStudent(new Student("1", "Chaitanya", 20, "chaitu@gmail.com"));
        sis.addStudent(new Student("2", "Nani", 21, "nani@gmail.com"));
        sis.addCourse(new Course("C1", "Mathematics"));
        sis.addCourse(new Course("C2", "Problem-Solving(C)"));
        //Runs until its true
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student");
            System.out.println("4. Print All Students");
            System.out.println("5. Print All Courses");
            System.out.println("6. Print All Enrollments");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            //Switch case for all our requirements for SIS
            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter student age: ");
                    int studentAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter student email: ");
                    String studentEmail = scanner.nextLine();
                    sis.addStudent(new Student(studentId, studentName, studentAge, studentEmail));
                    break;
                case 2:
                    System.out.print("Enter course ID: ");
                    String courseId = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    sis.addCourse(new Course(courseId, courseName));
                    break;
                case 3:
                    System.out.print("Enter student ID to enroll: ");
                    String enrollStudentId = scanner.nextLine();
                    System.out.print("Enter course ID to enroll: ");
                    String enrollCourseId = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    int grade = scanner.nextInt();
                    scanner.nextLine(); 
                    Student studentToEnroll = sis.students.stream().filter(s -> s.getId().equals(enrollStudentId)).findFirst().orElse(null);
                    Course courseToEnroll = sis.courses.stream().filter(c -> c.getCourseId().equals(enrollCourseId)).findFirst().orElse(null);
                    if (studentToEnroll != null && courseToEnroll != null) {
                        sis.enrollStudent(studentToEnroll, courseToEnroll, grade);
                        System.out.println("Student enrolled successfully.");
                    } else {
                        System.out.println("Student or course not found.");
                    }
                    break;
                case 4:
                    sis.printAllStudents();
                    break;
                case 5:
                    sis.printAllCourses();
                    break;
                case 6:
                    sis.printAllEnrollments();
                    break;
		        case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option as shown!!");
            }
        }
    }
}
