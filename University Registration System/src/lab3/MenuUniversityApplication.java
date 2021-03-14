package lab3;
import lab3.controller.CourseController;
import lab3.controller.StudentController;
import lab3.model.Course;
import lab3.model.Student;
import lab3.repository.*;
import lab3.view.CourseView;
import lab3.view.StudentView;
import java.util.List;
import java.util.Scanner;

public class MenuUniversityApplication {
    CourseFileRepository courseRepository;
    StudentFileRepository studentRepository;
    TeacherFileRepository teacherRepository;
    private static RegistrationSystem registrationS;

    //Constructor
    public MenuUniversityApplication(CourseFileRepository courseRepository, TeacherFileRepository teacherRepository, StudentFileRepository studentRepository) {
        this.courseRepository =  courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        registrationS = new RegistrationSystem(courseRepository,studentRepository);
    }

    public void DisplayMenu(){
        int option = 1; //user's option
        do {
            Scanner myScanner = new Scanner(System.in); //Scanner class to get user input

            //Show menu
            System.out.println("1. Register student to course");
            System.out.println("2. Print all students enrolled in a course");
            System.out.println("3. Display all available courses and their free places");
            System.out.println("4. Show all courses");
            System.out.println("5. Exit");
            System.out.println("Enter your option:");

            try {
                option = myScanner.nextInt(); //Read an int value from the user

                switch (option) {
                    case 1:
                        //register a student for a course

                        //choose a course
                        System.out.println("Please choose a courseId:");
                        for (Course tempcourse : courseRepository.findAll()) //show all courses with ids
                            System.out.println(tempcourse.getCourseId() + " " + tempcourse.getName());
                        Scanner courseId = new Scanner(System.in);
                        Course course = courseRepository.findOne(courseId.nextLong());

                        //choose a student for the course
                        System.out.println("Please choose a studentId:");
                        for (Student tempstud : studentRepository.findAll()) //show all students with ids
                            System.out.println(tempstud.getStudentId() + " " + tempstud.getFirstName() + " " + tempstud.getLastName());
                        Scanner studentId = new Scanner(System.in);
                        Student student = studentRepository.findOne(studentId.nextLong());

                        try {
                            registrationS.register(course, student);
                        }
                        catch(Exception e){
                            ExceptionsClass.registerException();
                        }
                        break;
                    case 2:
                        //prints all students enrolled in a course

                        //choose a course
                        System.out.println("Please choose a courseId:");
                        for (Course tempcourse : courseRepository.findAll()) //show all courses with ids
                            System.out.println(tempcourse.getCourseId() + " " + tempcourse.getName());
                        Scanner courseId2 = new Scanner(System.in);
                        try {
                            Course course2 = courseRepository.findOne(courseId2.nextLong());
                            showStudents(registrationS.retrieveStudentsEnrolledForACourse(course2));
                        }
                        catch(Exception e){
                            ExceptionsClass.courseExistException();
                        }
                        break;
                    case 3:
                        //shows all available courses with their free places
                        registrationS.retrieveCoursesWithFreePlaces();
                        break;
                    case 4:
                        //shows all courses
                        showContentOfCourses(registrationS.getAllCourses());
                        break;
                    case 5:
                        break;
                }
            }
            catch(Exception e){
                ExceptionsClass.mustTypeIntInConsole();
            }

        } while(option != 5);
    }

    /**
     * This method uses the course's view to show the content of courses.
     * @param courses List<Course>
     */
    public void showContentOfCourses(List<Course> courses) {
        for (Course tempcourse: courses) {
            CourseView cview = new CourseView();
            CourseController c_controller = new CourseController(tempcourse, cview);
            c_controller.updateView();
        }
    }

    /**
     * This method uses the student's view to show the content of students.
     * @param studs List<Student>
     */
    public void showStudents(List<Student> studs) {
        for (Student tempstud: studs) {
            StudentView sview = new StudentView();
            StudentController scontroller = new StudentController(tempstud, sview);
            scontroller.updateView();
        }
    }
}
