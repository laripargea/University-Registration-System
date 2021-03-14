package lab3.controller;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseFileRepository;
import lab3.repository.StudentFileRepository;
import lab3.repository.TeacherFileRepository;
import lab3.view.TeacherView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeacherControllerTests {
    private static Teacher model;
    private static TeacherView view;
    private static TeacherController teacherctrl;
    private static List<Course> coursesTeacher;
    private static List<Course> repoCourses = new ArrayList<Course>();
    private static List<Teacher> repoTeachers = new ArrayList<Teacher>();
    CourseFileRepository crepo;
    TeacherFileRepository trepo;


    @BeforeEach
    void setUp() throws IOException {
        coursesTeacher = new ArrayList<Course>();
        model = new Teacher("Diana","Cristea",1,coursesTeacher);
        view = new TeacherView();
        teacherctrl = new TeacherController(model,view);
        List<Teacher> repoTeachers = new ArrayList<Teacher>();
        List<Course> repoCourses = new ArrayList<Course>();
        crepo = new CourseFileRepository(repoCourses);
        trepo = new TeacherFileRepository(repoTeachers,crepo);
    }

    @Test
    void getTeacherFirstNameTestPositive(){
        assertTrue(model.getFirstName().equals("Diana"));
    }

    @Test
    void getTeacherFirstNameTestNegative(){
        assertFalse(model.getFirstName().equals("Ovidiu"));
    }

    @Test
    void setTeacherFirstNameTestPositive(){
        model.setFirstName("Ovidiu");
        assertTrue(model.getFirstName().equals("Ovidiu"));
    }

    @Test
    void setTeacherFirstNameTestNegative(){
        model.setFirstName("Ovidiu");
        assertFalse(model.getFirstName().equals("Diana"));
    }

    @Test
    void getTeacherLastNameTestPositive(){
        assertTrue(model.getLastName().equals("Cristea"));
    }

    @Test
    void getTeacherLastNameTestNegative(){
        assertFalse(model.getLastName().equals("Papuc"));
    }

    @Test
    void setTeacherLastNameTestPositive(){
        model.setLastName("Papuc");
        assertTrue(model.getLastName().equals("Papuc"));
    }

    @Test
    void setTeacherLastNameTestNegative(){
        model.setLastName("Papuc");
        assertFalse(model.getLastName().equals("Cristea"));
    }

    @Test
    void getTeacherTeacherIdTestPositive(){
        assertTrue(model.getTeacherId()==1);
    }

    @Test
    void getTeacherTeacherIdTestNegative(){
        assertFalse(model.getTeacherId()==4);
    }

    @Test
    void setTeacherTeacherIdTestPositive(){
        model.setTeacherId(3);
        assertTrue(model.getTeacherId()==3);
    }

    @Test
    void setTeacherTeacherIdTestNegative(){
        model.setTeacherId(3);
        assertFalse(model.getTeacherId()==1);
    }

    @Test
    void getTeacherCoursesTestPositive(){
        assertTrue(model.getCourses().size()==0);
    }

    @Test
    void getTeacherCoursesTestNegative(){
        assertFalse(model.getCourses().size()==1);
    }

    @Test
    void setTeacherCoursesTestPositive(){
        List<Student> studentsForCourse = new ArrayList<Student>();
        List<Course> coursesTeacher = new ArrayList<Course>();
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
        Course course = new Course("BD",teacher,1,studentsForCourse,5,1);
        List<Course> tempcourses = new ArrayList<Course>();
        tempcourses.add(course);
        model.setCourses(tempcourses);
        assertTrue(model.getCourses()==tempcourses);
    }

    @Test
    void setTeacherCoursesTestNegative(){
        List<Student> studentsForCourse = new ArrayList<Student>();
        List<Course> coursesTeacher = new ArrayList<Course>();
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
        Course course = new Course("BD",teacher,1,studentsForCourse,5,1);
        List<Course> tempcourses = new ArrayList<Course>();
        tempcourses.add(course);
        model.setCourses(tempcourses);
        assertFalse(model.getCourses().size()==0);
    }


    @Test
    public void filterMethodTest(){
        List<Student> studentsForCourse1 = new ArrayList<Student>();
        List<Student> studentsForCourse2 = new ArrayList<Student>();
        List<Course> coursesNewTeacher = new ArrayList<Course>();

        Teacher newteacher = new Teacher("Tudor","Chifor",2,coursesNewTeacher);
        Course course1 = new Course("MAP",newteacher,1,studentsForCourse1,5,1);
        Course course2 = new Course("FP",newteacher,20,studentsForCourse2,5,2);

        coursesNewTeacher.add(course1);
        coursesNewTeacher.add(course2);
        trepo.save(newteacher);

        assertTrue(teacherctrl.filterMethod(trepo.getteacherList()).size()==2);

    }
}

