package lab3;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class where program starts.
 */
public class StartApp {

    /**
     * Start point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("UNIVERSITY APPLICATION");

        List<Course> courses = new ArrayList<Course>();
        CourseFileRepository courseRepo = new CourseFileRepository(courses);

        List<Student> students = new ArrayList<Student>();
        StudentFileRepository studRepo = new StudentFileRepository(students,courseRepo);

        List<Teacher> teachers = new ArrayList<Teacher>();
        TeacherFileRepository teacherRepo = new TeacherFileRepository(teachers,courseRepo);

        MenuUniversityApplication menu = new MenuUniversityApplication(courseRepo, teacherRepo, studRepo);
        menu.DisplayMenu();
    }
}
