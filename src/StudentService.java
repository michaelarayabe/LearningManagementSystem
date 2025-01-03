import java.util.HashMap;
import java.util.Map;

public class StudentService {

    private Map<String, Student> students;
    private static StudentService instance;

    private StudentService(){
        this.students = new HashMap<>();
    }

    public static StudentService getInstance(){
        if(instance == null){
            instance = new StudentService();
        }
        return instance;
    }

    public void addStudent(String studentId, String name){
        if(students.containsKey(studentId)){
            System.out.println("Student with ID " + studentId + " already exists");
        } else {
            students.put(studentId, new Student(studentId, name));
            System.out.println("Student added successfully");
        }
    }

    public void listStudents(){
        if(students.isEmpty()){
            System.out.println("No students available");
        } else {
            students.values().forEach(System.out::println);
        }
    }

    public void enrollStudent(String studentId, String courseId, CourseService courseService){
        Student student = students.get(studentId);
        if(student == null){
            System.out.println("Student with ID: " + studentId + " not found");
            return;
        }

        Course course = courseService.getCourses().get(courseId);
        if(student.getEnrolledCourses().contains(courseId)){
            System.out.println("Student with ID " + studentId + " is already enrolled in the course " + courseId + " .");
        }

        if(!courseService.getCourses().containsKey(courseId)){
            System.out.println("Course with ID " + courseId + " does not exist.");
            return;
        }
        student.enrollCourse(courseId);
        course.addStudentToCourse(studentId);
        System.out.println("Student with ID " + studentId + " enrolled in course " + courseId + " .");
    }

    public Map<String, Student> getStudents(){
        return students;
    }
}
