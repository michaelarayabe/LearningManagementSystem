import java.util.HashMap;
import java.util.Map;

public class AssignmentService {
    private Map<String, Assignment> assignments;

    private static AssignmentService instance;

    private AssignmentService(){
        this.assignments = new HashMap<>();
    }

    public static AssignmentService getInstance(){
        if(instance == null){
            instance = new AssignmentService();
        }
        return instance;
    }

    public void addAssignment(String title, String courseId, CourseService courseService){
        if(!courseService.getCourses().containsKey(courseId)){
            System.out.println("Course with ID " + courseId + " does not exist. Cannot add assignment");
        }

        if(assignments.containsKey(title)){
            System.out.println("Assignment with title " + title + " already exists.");
        } else {
            assignments.put(title, new Assignment(title, courseId));
            System.out.println("Assignment added successfully");
        }
    }

    public void listAssignments(String courseId){
        System.out.println("Assignments for course " + courseId + ":");
        assignments.values().stream()
                .filter(a -> a.getCourseId().equals(courseId))
                .forEach(System.out::println);
    }

    public void recordGrade(String assignmentTitle, String studentId, double grade){
        Assignment assignment = assignments.get(assignmentTitle);
        if(assignment == null){
            System.out.println("Assignment with title " + assignmentTitle + " not found");
            return;
        }
        assignment.setGrades(studentId, grade);
        System.out.println("Grade recorded for student " + studentId + " in assignment " + assignmentTitle + " . ");
    }

    public Map<String, Assignment> getAssignments(){
        return assignments;
    }
}
