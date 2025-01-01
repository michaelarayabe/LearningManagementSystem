import java.util.ArrayList;
import java.util.List;

public class Student {

    private String studentId;
    private String name;
    private List<String> enrolledCourses;

    public Student(String studentId, String name){
        this.studentId = studentId;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(String courseId){
        enrolledCourses.add(courseId);
    }

    public String toString(){
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", enrolledCourses=" + enrolledCourses +
                '}';
    }
}
