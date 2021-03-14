package lab3.controller;
import lab3.model.Course;
import lab3.model.Student;
import lab3.view.StudentView;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private Student model;
    private StudentView view;

    //Constructor
    public StudentController(Student model, StudentView view){
        this.model = model;
        this.view = view;
    }

    //Getters and Setters

    public String getStudentFirstName(){
        return model.getFirstName();
    }

    public void setStudentFirstName(String firstName){
        model.setFirstName(firstName);
    }

    public String getStudentLastName(){
        return model.getLastName();
    }

    public void setStudentLastName(String lastName){
        model.setLastName(lastName);
    }

    public long getStudentStudentId() {
        return model.getStudentId();
    }

    public void setStudentStudentId(long studentId) {
        model.setStudentId(studentId);
    }

    public int getStudentTotalCredits() {
        return model.getTotalCredits();
    }

    public void setStudentTotalCredits(int totalCredits) {
        model.setTotalCredits(totalCredits);
    }

    public List<Course> getStudentEnrolledCourses() {
        return model.getEnrolledCourses();
    }

    public void setStudentEnrolledCourses(List<Course> enrolledCourses) {
        model.setEnrolledCourses(enrolledCourses);
    }

    //Student's details
    public void updateView(){
        view.printStudentDetails(model);
    }

    /**
     * This method sorts the list of students in descending order by id.
     * @param students - List<Student>
     * @return sorted students
     */
    public List<Student> sortStudents(List<Student> students){
        List<Course> courseList = new ArrayList<Course>();
        Student auxstud = new Student("","",0,0,courseList);

        //bubble sort
        for(int i=0;i<students.size()-1;i++)
            for(int j=i+1;j<students.size();j++)
                if(students.get(i).getStudentId()<students.get(j).getStudentId()) //descending
                {
                    auxstud.setFirstName(students.get(i).getFirstName());
                    auxstud.setLastName(students.get(i).getLastName());
                    auxstud.setStudentId(students.get(i).getStudentId());
                    auxstud.setTotalCredits(students.get(i).getTotalCredits());
                    auxstud.setEnrolledCourses(students.get(i).getEnrolledCourses());

                    students.get(i).setFirstName(students.get(j).getFirstName());
                    students.get(i).setLastName(students.get(j).getLastName());
                    students.get(i).setStudentId(students.get(j).getStudentId());
                    students.get(i).setTotalCredits(students.get(j).getTotalCredits());
                    students.get(i).setEnrolledCourses(students.get(j).getEnrolledCourses());

                    students.get(j).setFirstName(auxstud.getFirstName());
                    students.get(j).setLastName(auxstud.getLastName());
                    students.get(j).setStudentId(auxstud.getStudentId());
                    students.get(j).setTotalCredits(auxstud.getTotalCredits());
                    students.get(j).setEnrolledCourses(auxstud.getEnrolledCourses());

                }

        return students;
    }
}
