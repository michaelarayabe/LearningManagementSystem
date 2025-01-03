import java.util.Scanner;

public class LMSApplication {
    public static void main(String[] args) {
        CourseService courseService = CourseService.getInstance();
        StudentService studentService = StudentService.getInstance();
        AssignmentService assignmentService = AssignmentService.getInstance();
        ReportingService reportingService = ReportingService.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Assignment Management");
            System.out.println("4. Report Management");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int mainChoice = scanner.nextInt();
            scanner.nextLine();

            switch (mainChoice){
                case 1 -> manageStudents(studentService,scanner);
                case 2 -> manageCourses(courseService, scanner);
                case 3 -> manageAssignments(assignmentService, courseService, scanner);
                case 4 -> manageReports(reportingService,studentService,courseService,assignmentService,scanner);
                case 5 -> {
                    System.out.println("Exiting LMS...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again");
            }
        }

    }

    private static void manageCourses(CourseService courseService, Scanner scanner){
        while(true){
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. List Courses");
            System.out.println("3. Delete Course");
            System.out.println("4. Back to Main Manu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter Course ID: ");
                    String courseId = scanner.nextLine();
                    System.out.println("Enter Course Name: ");
                    String courseName = scanner.nextLine();
                    courseService.addCourse(courseId, courseName);
                }
                case 2 -> courseService.listCourses();
                case 3 -> {
                    System.out.println("Enter Course ID to delete: ");
                    String deleteId = scanner.nextLine();
                    courseService.deleteCourse(deleteId);
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageStudents(StudentService studentService, Scanner scanner){
        CourseService courseService = CourseService.getInstance();

        while(true){
            System.out.println("\n --- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> {
                    System.out.println("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.println("Enter student name: ");
                    String studentName = scanner.nextLine();
                    studentService.addStudent(studentId, studentName);
                }
                case 2 -> studentService.listStudents();
                case 3 -> {
                    System.out.println("Enter student ID: ");
                    String studentIdToEnrol = scanner.nextLine();
                    System.out.println("Enter course ID: ");
                    String courseIdToEnroll = scanner.nextLine();
                    studentService.enrollStudent(studentIdToEnrol, courseIdToEnroll, courseService);
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageAssignments(AssignmentService assignmentService, CourseService courseService, Scanner scanner){
        while (true){
            System.out.println("\n--- Assignment Management ---");
            System.out.println("1. Add Assignment");
            System.out.println("2. List Assignment for a course");
            System.out.println("3. Record grade for an assignment");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 -> {
                    System.out.println("Enter assignment title: ");
                    String title = scanner.nextLine();
                    System.out.println("Enter course ID: ");
                    String courseId = scanner.nextLine();
                    assignmentService.addAssignment(title, courseId, courseService);
                }
                case 2 -> {
                    System.out.println("Enter course ID: ");
                    String listCourseId = scanner.nextLine();
                    assignmentService.listAssignments(listCourseId);
                }
                case 3 -> {
                    System.out.println("Enter assignment title: ");
                    String assignmentTitle = scanner.nextLine();
                    System.out.println("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.println("Enter grade");
                    double grade = scanner.nextDouble();
                    assignmentService.recordGrade(assignmentTitle, studentId, grade);
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void manageReports(ReportingService reportingService, StudentService studentService, CourseService courseService, AssignmentService assignmentService, Scanner scanner){
        while(true){
            System.out.println("\n--- Reporting--- ");
            System.out.println("1. View Grades for a Student");
            System.out.println("2. View Grades for a Course");
            System.out.println("3. Back to Main Menu");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter student ID: ");
                    String studentId = scanner.nextLine();

                    reportingService.viewStudentGrades(studentId, studentService, assignmentService);
                }
                case 2 -> {
                    System.out.println("Enter Course ID: ");
                    String courseId = scanner.nextLine();
                    reportingService.viewCourseGrades(courseId, courseService, assignmentService);
                }
                case 3 -> {return;}
                default -> System.out.println("Invalid choice. Please tyr again.");
            }
        }
    }
}