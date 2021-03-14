package lab3.controller;
import lab3.model.Course;
import lab3.model.Person;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.view.CourseView;
import java.util.ArrayList;
import java.util.List;

public class CourseController {
    private Course model;
    private CourseView view;

    //Constructor
    public CourseController(Course model, CourseView view){
        this.model = model;
        this.view = view;
    }

    //Getters and Setters

    public long getCourseCourseId(){
        return model.getCourseId();
    }

    public void setCourseCourseId(long courseId){
        model.setCourseId(courseId);
    }

    public String getCourseName(){
        return model.getName();
    }

    public void setCourseName(String name){
        model.setName(name);
    }

    public Person getCourseTeacher() {
        return model.getTeacher();
    }

    public void setCourseTeacher(Person teacher) {
        model.setTeacher(teacher);
    }

    public int getCourseMaxEnrollment() {
        return model.getMaxEnrollment();
    }

    public void setCourseMaxEnrollment(int maxEnrollment) {
        model.setMaxEnrollment(maxEnrollment);
    }

    public List<Student> getCourseStudentsEnrolled() {
        return model.getStudentsEnrolled();
    }

    public void setCourseStudentsEnrolled(List<Student> studentsEnrolled) {
        model.setStudentsEnrolled(studentsEnrolled);
    }

    public int getCourseCredits() {
        return model.getCredits();
    }

    public void setCourseCredits(int credits) {
        model.setCredits(credits);
    }

    //Course's details
    public void updateView(){
        view.printCourseDetails(model);
    }

    /**
     * This method sorts the list of courses in ascending order by credits.
     * @param courses - List<Course>
     * @return sorted courses
     */
    public List<Course> sortCourses(List<Course> courses){
        List<Course> courseList = new ArrayList<Course>();
        Teacher teacher = new Teacher("", "",0,courseList);
        List<Student> studList = new ArrayList<Student>();
        Course auxcourse = new Course("",teacher,0,studList,0,0);

        //bubble sort
        for(int i=0;i<courses.size()-1;i++)
            for(int j=i+1;j<courses.size();j++)
                if(courses.get(i).getCredits()>courses.get(j).getCredits()) //ascending
                {
                    auxcourse.setName(courses.get(i).getName());
                    auxcourse.setTeacher(courses.get(i).getTeacher());
                    auxcourse.setCourseId(courses.get(i).getCourseId());
                    auxcourse.setCredits(courses.get(i).getCredits());
                    auxcourse.setMaxEnrollment(courses.get(i).getMaxEnrollment());
                    auxcourse.setStudentsEnrolled(courses.get(i).getStudentsEnrolled());

                    courses.get(i).setName(courses.get(j).getName());
                    courses.get(i).setTeacher(courses.get(j).getTeacher());
                    courses.get(i).setCourseId(courses.get(j).getCourseId());
                    courses.get(i).setCredits(courses.get(j).getCredits());
                    courses.get(i).setMaxEnrollment(courses.get(j).getMaxEnrollment());
                    courses.get(i).setStudentsEnrolled(courses.get(j).getStudentsEnrolled());

                    courses.get(j).setName(auxcourse.getName());
                    courses.get(j).setTeacher(auxcourse.getTeacher());
                    courses.get(j).setCourseId(auxcourse.getCourseId());
                    courses.get(j).setCredits(auxcourse.getCredits());
                    courses.get(j).setMaxEnrollment(auxcourse.getMaxEnrollment());
                    courses.get(j).setStudentsEnrolled(auxcourse.getStudentsEnrolled());

                }

        return courses;
    }
}
