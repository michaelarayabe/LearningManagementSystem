import java.util.HashMap;
import java.util.Map;

public class Assignment {
    private String title;
    private String courseId;
    private Map<String, Double> grades;

    public Assignment(String title, String courseId){
        this.title = title;
        this.courseId = courseId;
        this.grades = new HashMap<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Map<String, Double> getGrades() {
        return grades;
    }

    public void setGrades(String studentId, double grade) {
        grades.put(studentId, grade);
    }

    @Override
    public String toString(){
        return "Assignment{" +
                "title='" + title + '\'' +
                ", courseId='" + courseId + '\'' +
                ", grades=" + grades +
                '}';
    }
}
