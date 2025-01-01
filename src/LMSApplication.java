import java.util.Scanner;

public class LMSApplication {
    public static void main(String[] args) {
        CourseService courseService = CourseService.getInstance();
        StudentService studentService = StudentService.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Course Management");
            System.out.println("2. Student Management");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int mainChoice = scanner.nextInt();
            scanner.nextLine();

            switch (mainChoice){
                case 1 -> manageCourses(courseService, scanner);
                case 2 -> manageStudents(studentService,scanner);
                case 3 -> {
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
}