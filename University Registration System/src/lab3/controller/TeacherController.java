package lab3.controller;
import lab3.model.Course;
import lab3.model.Teacher;
import lab3.view.TeacherView;
import java.util.ArrayList;
import java.util.List;

public class TeacherController {
    private Teacher model;
    private TeacherView view;

    //Constructor
    public TeacherController(Teacher model, TeacherView view){
        this.model = model;
        this.view = view;
    }

    //Getters and Setters

    public String getTeacherFirstName(){
        return model.getFirstName();
    }

    public void setTeacherFirstName(String firstName){
        model.setFirstName(firstName);
    }

    public String getTeacherLastName(){
        return model.getLastName();
    }

    public void setTeacherLastName(String lastName){
        model.setLastName(lastName);
    }

    public long getTeacherTeacherId() {
        return model.getTeacherId();
    }

    public void setTeacherTeacherId(long teacherId) {
        model.setTeacherId(teacherId);
    }

    public List<Course> getTeacherCourses() {
        return model.getCourses();
    }

    public void setTeacherCourses(List<Course> courses) {
        model.setCourses(courses);
    }

    //Teacher's details
    public void updateView(){
        view.printTeacherDetails(model);
    }

    /**
     * This method gives the teachers with 2 or more than 2 courses.
     * @param teachers - List<Teacher>
     * @return filtered list of teachers
     */
    public List<Teacher> filterMethod(List<Teacher> teachers){
        List<Teacher> tempteachers = new ArrayList<Teacher>();
        for(Teacher t:teachers)
            if(t.getCourses().size()>=2)
                tempteachers.add(t);

        return tempteachers;
    }
}
