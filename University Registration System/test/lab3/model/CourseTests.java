package lab3.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseTests {
    private static List<Course> coursesTeacher;
    private static Teacher teacher;
    private static List<Student> studentsForCourse;
    private static Course course;

    @BeforeEach
    void setUp() {
        coursesTeacher = new ArrayList<Course>();
        studentsForCourse = new ArrayList<Student>();
        teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
        course = new Course("BD",teacher,1,studentsForCourse,5,1);
        coursesTeacher.add(course);
    }

    @Test
    void getCourseIdTestPositive(){
        assertTrue(course.getCourseId()==1);
    }

    @Test
    void getCourseIdTestNegative(){
        assertFalse(course.getCourseId()==100);
    }

    @Test
    void setCourseIdTestPositive(){
        course.setCourseId(3);
        assertTrue(course.getCourseId()==3);
    }

    @Test
    void setCourseIdTestNegative(){
        course.setCourseId(3);
        assertFalse(course.getCourseId()==1);
    }

    @Test
    void getNameTestPositive(){
        assertTrue(course.getName().equals("BD"));
    }

    @Test
    void getNameTestNegative(){
        assertFalse(course.getName().equals("SDA"));
    }

    @Test
    void setNameTestPositive(){
        course.setName("SDA");
        assertTrue(course.getName().equals("SDA"));
    }

    @Test
    void setNameTestNegative(){
        course.setName("SDA");
        assertFalse(course.getName().equals("BD"));
    }

    @Test
    void getTeacherTestPositive(){
        assertTrue(course.getTeacher().equals(teacher));
    }

    @Test
    void getTeacherTestNegative(){
        Teacher tempteacher = new Teacher("Tudor","Chifor",2,coursesTeacher);
        assertFalse(course.getTeacher().equals(tempteacher));
    }

    @Test
    void setTeacherTestPositive(){
        Teacher tempteacher = new Teacher("Tudor","Chifor",2,coursesTeacher);
        course.setTeacher(tempteacher);
        assertTrue(course.getTeacher().equals(tempteacher));
    }

    @Test
    void setTeacherTestNegative(){
        Teacher tempteacher = new Teacher("Tudor","Chifor",2,coursesTeacher);
        course.setTeacher(tempteacher);
        assertFalse(course.getTeacher().equals(teacher));
    }

    @Test
    void getMaxEnrollmentTestPositive(){
        assertTrue(course.getMaxEnrollment()==1);
    }

    @Test
    void getMaxEnrollmentTestNegative(){
        assertFalse(course.getMaxEnrollment()==7);
    }

    @Test
    void setMaxEnrollmentTestPositive(){
        course.setMaxEnrollment(4);
        assertTrue(course.getMaxEnrollment()==4);
    }

    @Test
    void setMaxEnrollmentTestNegative(){
        course.setMaxEnrollment(4);
        assertFalse(course.getMaxEnrollment()==1);
    }

    @Test
    void getStudentsEnrolledTestPositive(){
        assertTrue(course.getStudentsEnrolled().size()==0);
    }

    @Test
    void getStudentsEnrolledTestNegative(){
        assertFalse(course.getStudentsEnrolled().size()==20);
    }

    @Test
    void setStudentsEnrolledTestPositive(){
        List<Course> coursesStudent = new ArrayList<Course>();
        Student student = new Student("Larisa", "Pargea", 1, 30, coursesStudent);
        List<Student> tempstudents = new ArrayList<Student>();
        tempstudents.add(student);
        course.setStudentsEnrolled(tempstudents);
        assertTrue(course.getStudentsEnrolled()==tempstudents);
    }

    @Test
    void setStudentsEnrolledTestNegative(){
        List<Course> coursesStudent = new ArrayList<Course>();
        Student student = new Student("Larisa", "Pargea", 1, 30, coursesStudent);
        List<Student> tempstudents = new ArrayList<Student>();
        tempstudents.add(student);
        course.setStudentsEnrolled(tempstudents);
        assertFalse(course.getStudentsEnrolled().size()==0);
    }

    @Test
    void getCreditsTestPositive(){
        assertTrue(course.getCredits()==5);
    }

    @Test
    void getCreditsTestNegative(){
        assertFalse(course.getCredits()==7);
    }

    @Test
    void setCreditsTestPositive(){
        course.setCredits(4);
        assertTrue(course.getCredits()==4);
    }

    @Test
    void setCreditsTestNegative(){
        course.setCredits(4);
        assertFalse(course.getCredits()==5);
    }

}