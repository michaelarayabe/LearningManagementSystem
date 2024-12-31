import java.util.Scanner;

public class LMSApplication {
    public static void main(String[] args) {
        CourseService courseService = CourseService.getInstance();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. List Courses");
            System.out.println("3. Delete Course");
            System.out.println("4. Exist");
            System.out.println("Choose an option: ");
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
                case 2 -> {
                    courseService.listCourses();
                }
                case 3 -> {
                    System.out.println("Enter Course ID to delete: ");
                    String deleteId = scanner.nextLine();
                    courseService.deleteCourse(deleteId);
                }
                case 4 -> {
                    System.out.println("Exiting Course Management...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}