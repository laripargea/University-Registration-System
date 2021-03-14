package lab3.controller;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseFileRepository;
import lab3.repository.StudentFileRepository;
import lab3.view.StudentView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentControllerTests {
    private static Student model;
    private static StudentView view;
    private static StudentController studentctrl;
    private static List<Course> coursesStudent;
    private static List<Student> repoStudents;
    private static List<Course> repoCourses;
    StudentFileRepository srepo;
    CourseFileRepository crepo;

    @BeforeEach
    void setUp() throws IOException {
        coursesStudent = new ArrayList<Course>();
        model = new Student("Mihai", "Oancea", 3, 5, coursesStudent);
        view = new StudentView();
        studentctrl = new StudentController(model,view);
        List<Student> repoStudents = new ArrayList<Student>();
        List<Course> repoCourses = new ArrayList<Course>();
        crepo = new CourseFileRepository(repoCourses);
        srepo = new StudentFileRepository(repoStudents,crepo);
    }

    @Test
    void getStudentFirstNameTestPositive(){
        assertTrue(model.getFirstName().equals("Mihai"));
    }

    @Test
    void getStudentFirstNameTestNegative(){
        assertFalse(model.getFirstName().equals("Ovidiu"));
    }

    @Test
    void setStudentFirstNameTestPositive(){
        model.setFirstName("Ovidiu");
        assertTrue(model.getFirstName().equals("Ovidiu"));
    }

    @Test
    void setStudentFirstNameTestNegative(){
        model.setFirstName("Ovidiu");
        assertFalse(model.getFirstName().equals("Mihai"));
    }

    @Test
    void getStudentLastNameTestPositive(){
        assertTrue(model.getLastName().equals("Oancea"));
    }

    @Test
    void getStudentLastNameTestNegative(){
        assertFalse(model.getLastName().equals("Papuc"));
    }

    @Test
    void setStudentLastNameTestPositive(){
        model.setLastName("Papuc");
        assertTrue(model.getLastName().equals("Papuc"));
    }

    @Test
    void setStudentLastNameTestNegative(){
        model.setLastName("Papuc");
        assertFalse(model.getLastName().equals("Oancea"));
    }

    @Test
    void getStudentStudentIdTestPositive(){
        assertTrue(model.getStudentId()==3);
    }

    @Test
    void getStudentStudentIdTestNegative(){
        assertFalse(model.getStudentId()==1);
    }

    @Test
    void setStudentStudentIdTestPositive(){
        model.setStudentId(3);
        assertTrue(model.getStudentId()==3);
    }

    @Test
    void setStudentStudentIdTestNegative(){
        model.setStudentId(3);
        assertFalse(model.getStudentId()==2);
    }

    @Test
    void getStudentTotalCreditsTestPositive(){
        assertTrue(model.getTotalCredits()==5);
    }

    @Test
    void getStudentTotalCreditsTestNegative(){
        assertFalse(model.getTotalCredits()==2);
    }

    @Test
    void setStudentTotalCreditsTestPositive(){
        model.setTotalCredits(4);
        assertTrue(model.getTotalCredits()==4);
    }

    @Test
    void setStudentTotalCreditsTestNegative(){
        model.setTotalCredits(4);
        assertFalse(model.getTotalCredits()==20);
    }

    @Test
    void getStudentEnrolledCoursesTestPositive(){
        assertTrue(model.getEnrolledCourses().size()==0);
    }

    @Test
    void getStudentEnrolledCoursesTestNegative(){
        assertFalse(model.getEnrolledCourses().size()==4);
    }

    @Test
    void setStudentEnrolledCoursesTestPositive(){
        List<Student> studentsForCourse = new ArrayList<Student>();
        List<Course> coursesTeacher = new ArrayList<Course>();
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
        Course course = new Course("BD",teacher,1,studentsForCourse,5,1);
        List<Course> tempcourses = new ArrayList<Course>();
        tempcourses.add(course);
        model.setEnrolledCourses(tempcourses);
        assertTrue(model.getEnrolledCourses()==tempcourses);
    }

    @Test
    void setStudentEnrolledCoursesTestNegative(){
        List<Student> studentsForCourse = new ArrayList<Student>();
        List<Course> coursesTeacher = new ArrayList<Course>();
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
        Course course = new Course("BD",teacher,1,studentsForCourse,5,1);
        List<Course> tempcourses = new ArrayList<Course>();
        tempcourses.add(course);
        model.setEnrolledCourses(tempcourses);
        assertFalse(model.getEnrolledCourses().size()==0);
    }


    @Test
    public void sortTest(){
        studentctrl.sortStudents(srepo.getstudentList());
        assertTrue(srepo.getstudentList().get(0).getStudentId()==3);
        assertTrue(srepo.getstudentList().get(1).getStudentId()==2);
        assertTrue(srepo.getstudentList().get(2).getStudentId()==1);

    }
}

