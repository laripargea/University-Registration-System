package lab3.repository;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseRepoTests {
    List<Course> courses = new ArrayList<Course>();
    CourseRepository courseRepo = new CourseRepository(courses);
    List<Course> coursesTeacher = new ArrayList<Course>();
    Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
    List<Student> studentsForCourse1 = new ArrayList<Student>();
    List<Student> studentsForCourse2 = new ArrayList<Student>();
    Course course1 = new Course("BD",teacher,1,studentsForCourse1,5,1);
    Course course2 = new Course("SGBD",teacher,20,studentsForCourse2,5,2);

    @BeforeEach
    void setUp() {
        coursesTeacher.add(course1);
        coursesTeacher.add(course2);
    }

    @Test
    public void getCourseListTestPositive() {
        courseRepo.getcourseList().add(course1);
        assertTrue(courseRepo.getcourseList().size()==1);
    }

    @Test
    public void getCourseListTestNegative() {
        courseRepo.getcourseList().add(course1);
        assertFalse(courseRepo.getcourseList().size()==0);
    }

    @Test
    public void setCourseListTestPositive() {
        List<Course> courseTemp = new ArrayList<Course>();
        courseTemp.add(course1);
        courseRepo.setcourseList(courseTemp);
        assertTrue(courseRepo.getcourseList().size()==1);
    }

    @Test
    public void setCourseListTestNegative() {
        List<Course> courseTemp = new ArrayList<Course>();
        courseTemp.add(course1);
        courseRepo.setcourseList(courseTemp);
        assertFalse(courseRepo.getcourseList().size()==0);
    }

    @Test
    public void findOneTestPositive(){
        courseRepo.getcourseList().add(course1);
        assertTrue(courseRepo.findOne(course1.getCourseId())==course1);
    }

    @Test
    public void findOneTestNegative(){
        courseRepo.getcourseList().add(course1);
        assertFalse(courseRepo.findOne(course1.getCourseId())==course2);
    }

    @Test
    public void findAllTestPositive(){
        int counter = 0; //count the elements (courses)
        courseRepo.getcourseList().add(course1);
        for (Course c : courseRepo.findAll()) {
            counter++;
        }
        assertTrue(counter == 1);
    }

    @Test
    public void findAllTestNegative(){
        int counter = 0; //count the elements (courses)
        courseRepo.getcourseList().add(course1);
        for (Course c : courseRepo.findAll()) {
            counter++;
        }
        assertFalse(counter == 0);
    }

    @Test
    public void saveTestPositive() {
        courseRepo.save(course1);
        assertTrue(courseRepo.getcourseList().get(0)==course1);
    }

    @Test
    public void saveTestNegative() {
        courseRepo.save(course1);
        assertFalse(courseRepo.getcourseList().size()==0);
    }

    @Test
    public void deleteTestPositive() {
        courseRepo.save(course1);
        courseRepo.delete(course1.getCourseId());
        assertTrue(courseRepo.getcourseList().size()==0);
    }

    @Test
    public void deleteTestNegative() {
        courseRepo.save(course1);
        courseRepo.delete(course1.getCourseId());
        assertFalse(courseRepo.getcourseList().size()==1);
    }

    @Test
    public void updateTestPositive() {
        List<Student> studentsForCourseTemp = new ArrayList<Student>();
        Course tempCourse = new Course("BD",teacher,10,studentsForCourseTemp,6,1);
        courseRepo.save(course1);
        assertTrue(courseRepo.update(tempCourse)==null);
        courseRepo.update(tempCourse);
        assertTrue(courseRepo.getcourseList().get(0).getMaxEnrollment()==10);
        assertTrue(courseRepo.getcourseList().get(0).getCredits()==6);
    }

    @Test
    public void updateTestNegative() {
        List<Student> studentsForCourseTemp = new ArrayList<Student>();
        Course tempCourse = new Course("BD",teacher,10,studentsForCourseTemp,6,2);
        courseRepo.save(course1);
        assertFalse(courseRepo.update(tempCourse)==null);
    }

}
