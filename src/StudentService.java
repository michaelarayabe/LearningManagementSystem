import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    //Student search funtionality
    public Student searchStudent(String keyword){
        return students.values().stream()
                .filter(student -> student.getName().contains(keyword) || student.getStudentId().equals(keyword))
                .findFirst()
                .orElse(null);
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

    public void bulkEnroll(String filename, CourseService courseService){
        try(BufferedReader br = new BufferedReader(new FileReader())){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 2){
                    enrollStudent(parts[0], parts[1], courseService);
                }
            }
            System.out.println("Bulk enrollment completed.");
        } catch (IOException e){
            System.out.println("Error during bulk enrollment: " + e.getMessage());
        }
    }

    public Map<String, Student> getStudents(){
        return students;
    }
}
