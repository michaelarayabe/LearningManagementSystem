import java.util.List;

public class ReportingService {
    private static ReportingService instance;

    private ReportingService(){}

    public static ReportingService getInstance(){
        if(instance == null){
            instance = new ReportingService();
        }
        return instance;
    }

    public void viewStudentGrades(String studentId, StudentService studentService, AssignmentService assignmentService){
        Student student = studentService.getStudents().get(studentId);
        if(student == null){
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }
        System.out.println("Grades for student: " + student.getName());
        List<String> enrolledCourses = student.getEnrolledCourses();

        for(String courseId : enrolledCourses){
            System.out.println("Course: " + courseId);
            assignmentService.getAssignments().values().stream()
                    .filter(a -> a.getCourseId().equals(courseId))
                    .forEach(a -> {
                        Double grade = a.getGrades().get(studentId);
                        if(grade != null){
                            System.out.println(" Assignment: " + a.getTitle() + " - Grade: " + grade);
                        } else {
                            System.out.println(" Assignment: " + a.getTitle() + " -No grade recorded");
                        }
                    });
        }
    }

    public void viewCourseGrades(String courseId, CourseService courseService, AssignmentService assignmentService){
        Course course = courseService.getCourses().get(courseId);
        if(course == null){
            System.out.println("Course with ID " + courseId + " not found");
            return;
        }

        System.out.println("Grades for Course: " + course.getName());
        assignmentService.getAssignments().values().stream()
                .filter(a -> a.getCourseId().equals(courseId))
                .forEach(a -> {
                    System.out.println("Assignment: " + a.getTitle());
                    a.getGrades().forEach((studentId, grade) -> {
                        System.out.println(" Student ID: " + studentId + " - Grade: " + grade);
                    });
                });
    }
}
