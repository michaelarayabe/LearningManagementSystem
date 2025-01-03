import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {

    private Map<String, Course> courses;

    private static CourseService instance;

    public CourseService(){
        this.courses = (Map<String, Course>) DataPersistenceService.loadData("course.dat");
        if(this.courses == null){
            this.courses = new HashMap<>();
        }
    }

    public void saveCourses(){
        DataPersistenceService.saveData("courses.dat", courses);
    }

    //Course search functionality
    public Course searchCourse(String keyword){
        return courses.values().stream()
                .filter(course -> course.getName().contains(keyword) || course.getCourseId().equals(keyword))
                .findFirst()
                .orElse(null);
    }

    public static CourseService getInstance(){
        if(instance == null){
            instance = new CourseService();
        }
        return instance;
    }

    public Map<String, Course> getCourses(){
        return courses;
    }

    public void addCourse(String courseId, String name){
        if(courses.containsKey(courseId)){
            System.out.println("Course with ID " + courseId + " already exists.");
        } else {
            courses.put(courseId, new Course(courseId, name));
            System.out.println("Course added successfully");
        }
    }

    public void listCourses(){
        if(courses.isEmpty()){
            System.out.println("No courses available.");
        } else {
            courses.values().forEach(System.out::println);
        }
    }

    public void deleteCourse(String courseId){
        if(courses.remove(courseId) != null){
            System.out.println("Course with ID " + courseId + " removed successfully");
        } else {
            System.out.println("Course with ID " + courseId + " not found");
        }
    }
}
