package lab3.repository;
import lab3.model.Teacher;
import lab3.model.Course;
import lab3.model.Student;
import java.util.List;

public class TeacherRepository implements ICrudRepository<Teacher>{

    private List<Teacher> teacherList;

    //Constructor
    public TeacherRepository(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    //Getter for teacherList
    public List<Teacher> getteacherList() {
        return teacherList;
    }

    //Setter for teacherList
    public void setteacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    /**
     * @param id -the id of the teacher to be returned; id must not be null
     * @return the teacher with the specified id or null - if there is no teacher with the given id
     */
    @Override
    public Teacher findOne(Long id) {
        for(Teacher teacher: teacherList) {
            if (teacher.getTeacherId() == id)
                return teacher;
        }
        return null;
    }

    /**
     * @return all teachers
     */
    @Override
    public Iterable<Teacher> findAll() {
        return teacherList;
    }

    /**
     * @param entity entity must be not null
     * @return null- if the given teacher is saved otherwise returns the teacher(id already exists)
     */
    @Override
    public Teacher save(Teacher entity) {
        if(findOne(entity.getTeacherId())==null)
            teacherList.add(entity);
        else
            return entity;

        return null;
    }

    /**
     * removes the teacher with the specified id
     *
     * @param id id must be not null
     * @return the removed teacher or null if there is no teacher with the given id
     */
    @Override
    public Teacher delete(Long id) {
        Teacher tempTeacher; //temporary teacher for return after remove
        for(Teacher teacher: teacherList) {
            if (teacher.getTeacherId() == id)
            {
                tempTeacher = teacher;
                teacherList.remove(teacher);
                return tempTeacher;
            }
        }
        return null;
    }

    /**
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity - (e.g id does not exist).
     */
    @Override
    public Teacher update(Teacher entity) {
        for(Teacher teacher: teacherList) {
            if (teacher.getTeacherId() == entity.getTeacherId()) {
                teacher.setFirstName(entity.getFirstName());
                teacher.setLastName(entity.getLastName());
                teacher.setCourses(entity.getCourses());
                return null;
            }
        }
        return entity;
    }

    /**
     * This method allows the teacher to remove one of his courses.
     *
     * @param course - Course
     * @param teacher - Teacher
     */
    public void deleteCourse(Course course, Teacher teacher){
        teacher.getCourses().remove(course); // teacher deletes a course

        // deletes the course for all the students who have it and sets the number of credits
        for (Student stud: course.getStudentsEnrolled()){
                if (stud.getEnrolledCourses().contains(course)) {
                    stud.getEnrolledCourses().remove(course);
                    stud.setTotalCredits(stud.getTotalCredits() - course.getCredits());
                }
        }
        course.getStudentsEnrolled().clear(); // it clears all students enrolled in the course
    }
}
