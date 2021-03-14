package lab3;
import lab3.model.Course;
import lab3.model.Student;
import lab3.repository.CourseFileRepository;
import lab3.repository.StudentFileRepository;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationSystem {
    CourseFileRepository crepo; //course repo to get the list of courses from CourseRepository
    StudentFileRepository srepo;

    //Constructor
    public RegistrationSystem(CourseFileRepository crepo,StudentFileRepository srepo) {
        this.crepo = crepo;
        this.srepo = srepo;
    }

    /**
     * This method registers students in chosen courses.
     *
     * @param course - Course
     * @param student - Student
     * @return false if student is enrolled or can not be enrolled, otherwise we add him/her and it returns true
     */
    public boolean register(Course course, Student student) throws FileNotFoundException {
        // verify if student has already 30 credits
        if(student.getTotalCredits()+course.getCredits()>30)
        {
            System.out.println("Too many credits!!! The student has a limit of 30 credits for the courses!");
            return false;
        }

        // verify if there are no free places in the course
        if(course.getStudentsEnrolled().size()+1>course.getMaxEnrollment())
        {
            System.out.println("There are no places available for this course. Please choose another one!");
            return false;
        }

        //if the student is already enrolled in the course
        for(Course tempcourse: student.getEnrolledCourses()) {
            if (tempcourse.getCourseId()==course.getCourseId()) {
                System.out.println("Student is already enrolled!");
                return false;
            }
        }

        //if student is not enrolled, we add the course to him/her
        student.getEnrolledCourses().add(course);

        //if student is not enrolled, we add him/her to the course and modify the credits
        student.setTotalCredits(student.getTotalCredits() + course.getCredits());
        course.getStudentsEnrolled().add(student);

        srepo.writeStudentFile();
        crepo.writeCourseFile();

        System.out.println("Student registered successfully!");
        return true;
    }

    /**
     * This method gives all the courses available.
     * @return list with courses which have free places
     */
    public List<Course> retrieveCoursesWithFreePlaces(){
        //new list to store the courses which have free places
        List<Course> courses_with_free_places = new ArrayList<Course>();
        for(Course course:crepo.getcourseList())
        {
            //free place means that the max number of students enrolled<maxEnrollment from course
            if(course.getMaxEnrollment() > course.getStudentsEnrolled().size())
            {
                System.out.println("Course: " + course.getName() + ", Free places: " + (course.getMaxEnrollment() - course.getStudentsEnrolled().size()));
                courses_with_free_places.add(course);
            }
        }
        return courses_with_free_places;

    }

    /**
     * This method gives all students from a course.
     *
     * @param course - Course
     * @return list with students enrolled in a course
     */
    public List<Student> retrieveStudentsEnrolledForACourse(Course course){
        //student's list
        return course.getStudentsEnrolled();
    }

    /**
     * This method gives all the courses.
     *
     * @return list of courses
     */
    public List<Course> getAllCourses(){
        return crepo.getcourseList();
    }
}
