package lab3.repository;
import lab3.model.Course;
import lab3.model.Student;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentRepoTests {
    List<Student> students = new ArrayList<Student>();
    StudentRepository studentRepo = new StudentRepository(students);
    List<Course> coursesStudent1 = new ArrayList<Course>();
    List<Course> coursesStudent2 = new ArrayList<Course>();
    Student student1 = new Student("Larisa", "Pargea", 1, 30, coursesStudent1);
    Student student2 = new Student("Maria", "Papuc", 2, 20, coursesStudent2);

    @Test
    public void getStudentListTestPositive() {
        studentRepo.getstudentList().add(student1);
        assertTrue(studentRepo.getstudentList().size()==1);
    }

    @Test
    public void getStudentListTestNegative() {
        studentRepo.getstudentList().add(student1);
        assertFalse(studentRepo.getstudentList().size()==0);
    }

    @Test
    public void setStudentListTestPositive() {
        List<Student> studentTemp = new ArrayList<Student>();
        studentTemp.add(student1);
        studentRepo.setstudentList(studentTemp);
        assertTrue(studentRepo.getstudentList().size()==1);
    }

    @Test
    public void setStudentListTestNegative() {
        List<Student> studentTemp = new ArrayList<Student>();
        studentTemp.add(student1);
        studentRepo.setstudentList(studentTemp);
        assertFalse(studentRepo.getstudentList().size()==0);
    }

    @Test
    public void findOneTestPositive(){
        studentRepo.getstudentList().add(student1);
        assertTrue(studentRepo.findOne(student1.getStudentId())==student1);
    }

    @Test
    public void findOneTestNegative(){
        studentRepo.getstudentList().add(student1);
        assertFalse(studentRepo.findOne(student1.getStudentId())==student2);
    }

    @Test
    public void findAllTestPositive(){
        int counter = 0; //count the elements (students)
        studentRepo.getstudentList().add(student1);
        for (Student s : studentRepo.findAll()) {
            counter++;
        }
        assertTrue(counter == 1);
    }

    @Test
    public void findAllTestNegative(){
        int counter = 0; //count the elements (students)
        studentRepo.getstudentList().add(student1);
        for (Student s : studentRepo.findAll()) {
            counter++;
        }
        assertFalse(counter == 0);
    }

    @Test
    public void saveTestPositive() {
        studentRepo.save(student1);
        assertTrue(studentRepo.getstudentList().get(0)==student1);
    }

    @Test
    public void saveTestNegative() {
        studentRepo.save(student1);
        assertFalse(studentRepo.getstudentList().size()==0);
    }

    @Test
    public void deleteTestPositive() {
        studentRepo.save(student1);
        studentRepo.delete(student1.getStudentId());
        assertTrue(studentRepo.getstudentList().size()==0);
    }

    @Test
    public void deleteTestNegative() {
        studentRepo.save(student1);
        studentRepo.delete(student1.getStudentId());
        assertFalse(studentRepo.getstudentList().size()==1);
    }

    @Test
    public void updateTestPositive() {
        List<Course> coursesStudentTemp = new ArrayList<Course>();
        Student tempStudent = new Student("Maria", "Pargea", 1, 20, coursesStudentTemp);
        studentRepo.save(student1);
        assertTrue(studentRepo.update(tempStudent)==null);
        studentRepo.update(tempStudent);
        assertTrue(studentRepo.getstudentList().get(0).getFirstName()=="Maria");
        assertTrue(studentRepo.getstudentList().get(0).getTotalCredits()==20);
    }

    @Test
    public void updateTestNegative() {
        List<Course> coursesStudentTemp = new ArrayList<Course>();
        Student tempStudent = new Student("Larisa", "Pargea", 2, 30, coursesStudentTemp);
        studentRepo.save(student1);
        assertFalse(studentRepo.update(tempStudent)==null);
    }

}
