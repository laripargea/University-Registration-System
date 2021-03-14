package lab3.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeacherTests {
    private static List<Course> coursesTeacher;
    private static Teacher teacher;

    @BeforeEach
    void setUp() {
        coursesTeacher = new ArrayList<Course>();
        teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
    }

    @Test
    void getTeacherIdTestPositive(){
        assertTrue(teacher.getTeacherId()==1);
    }

    @Test
    void getTeacherIdTestNegative(){
        assertFalse(teacher.getTeacherId()==4);
    }

    @Test
    void setTeacherIdTestPositive(){
        teacher.setTeacherId(3);
        assertTrue(teacher.getTeacherId()==3);
    }

    @Test
    void setTeacherIdTestNegative(){
        teacher.setTeacherId(3);
        assertFalse(teacher.getTeacherId()==1);
    }

    @Test
    void getCoursesTestPositive(){
        assertTrue(teacher.getCourses().size()==0);
    }

    @Test
    void getCoursesTestNegative(){
        assertFalse(teacher.getCourses().size()==1);
    }

    @Test
    void setCoursesTestPositive(){
        List<Student> studentsForCourse = new ArrayList<Student>();
        List<Course> coursesTeacher = new ArrayList<Course>();
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
        Course course = new Course("BD",teacher,1,studentsForCourse,5,1);
        List<Course> tempcourses = new ArrayList<Course>();
        tempcourses.add(course);
        teacher.setCourses(tempcourses);
        assertTrue(teacher.getCourses()==tempcourses);
    }

    @Test
    void setCoursesTestNegative(){
        List<Student> studentsForCourse = new ArrayList<Student>();
        List<Course> coursesTeacher = new ArrayList<Course>();
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
        Course course = new Course("BD",teacher,1,studentsForCourse,5,1);
        List<Course> tempcourses = new ArrayList<Course>();
        tempcourses.add(course);
        teacher.setCourses(tempcourses);
        assertFalse(teacher.getCourses().size()==0);
    }
}