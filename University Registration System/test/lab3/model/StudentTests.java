package lab3.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentTests {
    private static List<Course> coursesStudent;
    private static Student student;

    @BeforeEach
    void setUp() {
        coursesStudent = new ArrayList<Course>();
        student = new Student("Maria", "Papuc", 2, 20, coursesStudent);
    }

    @Test
    void getStudentIdTestPositive(){
        assertTrue(student.getStudentId()==2);
    }

    @Test
    void getStudentIdTestNegative(){
        assertFalse(student.getStudentId()==1);
    }

    @Test
    void setStudentIdTestPositive(){
        student.setStudentId(3);
        assertTrue(student.getStudentId()==3);
    }

    @Test
    void setStudentIdTestNegative(){
        student.setStudentId(3);
        assertFalse(student.getStudentId()==2);
    }

    @Test
    void getTotalCreditsTestPositive(){
        assertTrue(student.getTotalCredits()==20);
    }

    @Test
    void getTotalCreditsTestNegative(){
        assertFalse(student.getTotalCredits()==2);
    }

    @Test
    void setTotalCreditsTestPositive(){
        student.setTotalCredits(4);
        assertTrue(student.getTotalCredits()==4);
    }

    @Test
    void setTotalCreditsTestNegative(){
        student.setTotalCredits(4);
        assertFalse(student.getTotalCredits()==20);
    }

    @Test
    void getEnrolledCoursesTestPositive(){
        assertTrue(student.getEnrolledCourses().size()==0);
    }

    @Test
    void getEnrolledCoursesTestNegative(){
        assertFalse(student.getEnrolledCourses().size()==4);
    }

    @Test
    void setEnrolledCoursesTestPositive(){
        List<Student> studentsForCourse = new ArrayList<Student>();
        List<Course> coursesTeacher = new ArrayList<Course>();
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
        Course course = new Course("BD",teacher,1,studentsForCourse,5,1);
        List<Course> tempcourses = new ArrayList<Course>();
        tempcourses.add(course);
        student.setEnrolledCourses(tempcourses);
        assertTrue(student.getEnrolledCourses()==tempcourses);
    }

    @Test
    void setEnrolledCoursesTestNegative(){
        List<Student> studentsForCourse = new ArrayList<Student>();
        List<Course> coursesTeacher = new ArrayList<Course>();
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
        Course course = new Course("BD",teacher,1,studentsForCourse,5,1);
        List<Course> tempcourses = new ArrayList<Course>();
        tempcourses.add(course);
        student.setEnrolledCourses(tempcourses);
        assertFalse(student.getEnrolledCourses().size()==0);
    }
}