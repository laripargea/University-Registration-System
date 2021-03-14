package lab3.repository;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeacherRepoTests {
    List<Teacher> teachers = new ArrayList<Teacher>();
    TeacherRepository teacherRepo = new TeacherRepository(teachers);
    List<Course> coursesTeacher = new ArrayList<Course>();
    Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);

    @Test
    public void getTeacherListTestPositive() {
        teacherRepo.getteacherList().add(teacher);
        assertTrue(teacherRepo.getteacherList().size()==1);
    }

    @Test
    public void getTeacherListTestNegative() {
        teacherRepo.getteacherList().add(teacher);
        assertFalse(teacherRepo.getteacherList().size()==0);
    }

    @Test
    public void setTeacherListTestPositive() {
        List<Teacher> teacherTemp = new ArrayList<Teacher>();
        teacherTemp.add(teacher);
        teacherRepo.setteacherList(teacherTemp);
        assertTrue(teacherRepo.getteacherList().size()==1);
    }

    @Test
    public void setTeacherListTestNegative() {
        List<Teacher> teacherTemp = new ArrayList<Teacher>();
        teacherTemp.add(teacher);
        teacherRepo.setteacherList(teacherTemp);
        assertFalse(teacherRepo.getteacherList().size()==0);
    }

    @Test
    public void findOneTestPositive(){
        teacherRepo.getteacherList().add(teacher);
        assertTrue(teacherRepo.findOne(teacher.getTeacherId())==teacher);
    }

    @Test
    public void findOneTestNegative(){
        teacherRepo.getteacherList().add(teacher);
        List<Course> coursesTeacherTemp = new ArrayList<Course>();
        Teacher teacherTemp = new Teacher("Ovidiu","Oancea",2,coursesTeacherTemp);
        assertFalse(teacherRepo.findOne(teacher.getTeacherId())==teacherTemp);
    }

    @Test
    public void findAllTestPositive(){
        int counter = 0; //count the elements (teachers)
        teacherRepo.getteacherList().add(teacher);
        for (Teacher t : teacherRepo.findAll()) {
            counter++;
        }
        assertTrue(counter == 1);
    }

    @Test
    public void findAllTestNegative(){
        int counter = 0; //count the elements (teachers)
        teacherRepo.getteacherList().add(teacher);
        for (Teacher t : teacherRepo.findAll()) {
            counter++;
        }
        assertFalse(counter == 0);
    }

    @Test
    public void saveTestPositive() {
        teacherRepo.save(teacher);
        assertTrue(teacherRepo.getteacherList().get(0)==teacher);
    }

    @Test
    public void saveTestNegative() {
        teacherRepo.save(teacher);
        assertFalse(teacherRepo.getteacherList().size()==0);
    }

    @Test
    public void deleteTestPositive() {
        teacherRepo.save(teacher);
        teacherRepo.delete(teacher.getTeacherId());
        assertTrue(teacherRepo.getteacherList().size()==0);
    }

    @Test
    public void deleteTestNegative() {
        teacherRepo.save(teacher);
        teacherRepo.delete(teacher.getTeacherId());
        assertFalse(teacherRepo.getteacherList().size()==1);
    }

    @Test
    public void updateTestPositive() {
        List<Course> coursesTeacherTemp = new ArrayList<Course>();
        Teacher teacherTemp = new Teacher("Diana","Troanca",1,coursesTeacherTemp);
        teacherRepo.save(teacher);
        assertTrue(teacherRepo.update(teacherTemp)==null);
        teacherRepo.update(teacherTemp);
        assertTrue(teacherRepo.getteacherList().get(0).getLastName()=="Troanca");
    }

    @Test
    public void updateTestNegative() {
        List<Course> coursesTeacherTemp = new ArrayList<Course>();
        Teacher teacherTemp = new Teacher("Diana","Cristea",2,coursesTeacherTemp);
        teacherRepo.save(teacher);
        assertFalse(teacherRepo.update(teacherTemp)==null);
    }

    @Test
    public void deleteCourseTest() {
        List<Course> coursesStudent = new ArrayList<Course>();
        Student student = new Student("Maria", "Papuc", 2, 20, coursesStudent);
        List<Student> studentsForCourse = new ArrayList<Student>();
        Course course = new Course("BD",teacher,1,studentsForCourse,5,1);
        coursesTeacher.add(course);
        coursesStudent.add(course);
        studentsForCourse.add(student);

        List<Student> students = new ArrayList<Student>();
        StudentRepository studRepo = new StudentRepository(students);
        studRepo.save(student);

        teacherRepo.save(teacher);

        List<Course> courses = new ArrayList<Course>();
        CourseRepository courseRepo = new CourseRepository(courses);
        courseRepo.save(course);

        teacherRepo.deleteCourse(course,teacher);

        assertTrue(course.getStudentsEnrolled().size() == 0);
        assertTrue(teacher.getCourses().size() == 0);
        assertTrue(student.getEnrolledCourses().size() == 0);
        assertTrue(student.getTotalCredits() == 15);
    }
}
