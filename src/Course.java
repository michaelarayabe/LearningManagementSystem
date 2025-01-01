import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseId;
    private String name;
    private List<String> students;

    public Course(String courseId, String name){
        this.courseId = courseId;
        this.name = name;
        this.students = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public void addStudentToCourse(String studentId){
        if(!students.contains(studentId)){
            students.add(studentId);
        }
    }

    @Override
    public String toString(){
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
