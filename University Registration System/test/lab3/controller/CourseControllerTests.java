package lab3.controller;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseFileRepository;
import lab3.view.CourseView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseControllerTests {
    private static Course model;
    private static CourseView view;
    private static CourseController coursectrl;
    private static Teacher teacher;
    private static List<Course> coursesTeacher;
    private static List<Student> studentsForCourse;
    private static List<Course> repoCourses;
    CourseFileRepository crepo;

    @BeforeEach
    void setUp() throws IOException {
        coursesTeacher = new ArrayList<Course>();
        studentsForCourse = new ArrayList<Student>();
        teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
        model = new Course("BD",teacher,1,studentsForCourse,5,1);
        view = new CourseView();
        repoCourses = new ArrayList<Course>();
        crepo = new CourseFileRepository(repoCourses);
        coursectrl = new CourseController(model,view);
    }

    @Test
    void getCourseCourseIdTestPositive(){
        assertTrue(model.getCourseId()==1);
    }

    @Test
    void getCourseCourseIdTestNegative(){
        assertFalse(model.getCourseId()==100);
    }

    @Test
    void setCourseCourseIdTestPositive(){
        model.setCourseId(3);
        assertTrue(model.getCourseId()==3);
    }

    @Test
    void setCourseCourseIdTestNegative(){
        model.setCourseId(3);
        assertFalse(model.getCourseId()==1);
    }

    @Test
    void getCourseNameTestPositive(){
        assertTrue(model.getName().equals("BD"));
    }

    @Test
    void getCourseNameTestNegative(){
        assertFalse(model.getName().equals("SDA"));
    }

    @Test
    void setCourseNameTestPositive(){
        model.setName("SDA");
        assertTrue(model.getName().equals("SDA"));
    }

    @Test
    void setCourseNameTestNegative(){
        model.setName("SDA");
        assertFalse(model.getName().equals("BD"));
    }

    @Test
    void getCourseTeacherTestPositive(){
        assertTrue(model.getTeacher().equals(teacher));
    }

    @Test
    void getCourseTeacherTestNegative(){
        Teacher tempteacher = new Teacher("Tudor","Chifor",2,coursesTeacher);
        assertFalse(model.getTeacher().equals(tempteacher));
    }

    @Test
    void setCourseTeacherTestPositive(){
        Teacher tempteacher = new Teacher("Tudor","Chifor",2,coursesTeacher);
        model.setTeacher(tempteacher);
        assertTrue(model.getTeacher().equals(tempteacher));
    }

    @Test
    void setCourseTeacherTestNegative(){
        Teacher tempteacher = new Teacher("Tudor","Chifor",2,coursesTeacher);
        model.setTeacher(tempteacher);
        assertFalse(model.getTeacher().equals(teacher));
    }

    @Test
    void getCourseMaxEnrollmentTestPositive(){
        assertTrue(model.getMaxEnrollment()==1);
    }

    @Test
    void getCourseMaxEnrollmentTestNegative(){
        assertFalse(model.getMaxEnrollment()==7);
    }

    @Test
    void setCourseMaxEnrollmentTestPositive(){
        model.setMaxEnrollment(4);
        assertTrue(model.getMaxEnrollment()==4);
    }

    @Test
    void setCourseMaxEnrollmentTestNegative(){
        model.setMaxEnrollment(4);
        assertFalse(model.getMaxEnrollment()==1);
    }

    @Test
    void getCourseStudentsEnrolledTestPositive(){
        assertTrue(model.getStudentsEnrolled().size()==0);
    }

    @Test
    void getCourseStudentsEnrolledTestNegative(){
        assertFalse(model.getStudentsEnrolled().size()==20);
    }

    @Test
    void setCourseStudentsEnrolledTestPositive(){
        List<Course> coursesStudent = new ArrayList<Course>();
        Student student = new Student("Larisa", "Pargea", 1, 30, coursesStudent);
        List<Student> tempstudents = new ArrayList<Student>();
        tempstudents.add(student);
        model.setStudentsEnrolled(tempstudents);
        assertTrue(model.getStudentsEnrolled()==tempstudents);
    }

    @Test
    void setCourseStudentsEnrolledTestNegative(){
        List<Course> coursesStudent = new ArrayList<Course>();
        Student student = new Student("Larisa", "Pargea", 1, 30, coursesStudent);
        List<Student> tempstudents = new ArrayList<Student>();
        tempstudents.add(student);
        model.setStudentsEnrolled(tempstudents);
        assertFalse(model.getStudentsEnrolled().size()==0);
    }

    @Test
    void getCourseCreditsTestPositive(){
        assertTrue(model.getCredits()==5);
    }

    @Test
    void getCourseCreditsTestNegative(){
        assertFalse(model.getCredits()==7);
    }

    @Test
    void setCourseCreditsTestPositive(){
        model.setCredits(4);
        assertTrue(model.getCredits()==4);
    }

    @Test
    void setCourseCreditsTestNegative(){
        model.setCredits(4);
        assertFalse(model.getCredits()==5);
    }

    @Test
    public void sortTest(){
        List<Student> studentsForCourse3 = new ArrayList<Student>();
        List<Student> studentsForCourse4 = new ArrayList<Student>();
        Course course3 = new Course("MAP",teacher,1,studentsForCourse3,6,3);
        Course course4 = new Course("RETELE",teacher,20,studentsForCourse4,4,4);

        crepo.save(course3);
        crepo.save(course4);

        coursectrl.sortCourses(crepo.getcourseList());
        assertTrue(crepo.getcourseList().get(0).getName()=="RETELE");
        assertTrue(crepo.getcourseList().get(3).getName()=="MAP");

    }
}
