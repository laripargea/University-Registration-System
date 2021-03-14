package lab3.repository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TeacherFileRepository implements ICrudRepository<Teacher>{
    private List<Teacher> teacherList;
    CourseFileRepository courseRepo;

    //Constructor
    public TeacherFileRepository(List<Teacher> teacherList,CourseFileRepository courseRepo) throws IOException {
        this.teacherList = teacherList;
        this.courseRepo = courseRepo;
        parseTeachersFile();
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
     * This method reads each teacher from the file and adds him/her to the repository list.
     * @throws IOException
     */
    public void parseTeachersFile() throws IOException {
        Reader reader2 = new BufferedReader(new FileReader("..\\Lari_Maria_L3\\src\\lab3\\JsonFiles\\Teachers.json")); //file

        //read from the json file
        ObjectMapper objectMapper2 = new ObjectMapper();
        JsonNode parser2 = objectMapper2.readTree(reader2);

        for (JsonNode pm : parser2) {
            List<Course> coursesTemp = new ArrayList<Course>();
            Teacher t = new Teacher("", "", 0, coursesTemp);

            //set the attributes for each teacher
            t.setFirstName(pm.path("firstName").asText());
            t.setLastName(pm.path("lastName").asText());
            t.setTeacherId(pm.path("studentId").asInt());

            //in the json file it is stored only the id of the course
            List<Integer> courses = new ArrayList<Integer>();
            for (JsonNode obj : pm.path("courses")) {
                courses.add(obj.asInt());
            }

            List<Course> courses2 = new ArrayList<Course>();
            for (long elem : courses)
                courses2.add(courseRepo.findOne(elem));

            t.setCourses(courses2);

            //add to repository list
            teacherList.add(t);

            addTeacherToCourse(t);
        }
        reader2.close();
    }

    /**
     * This method sets the teacher for the courses.
     * @param teacher Teacher
     */
    public void addTeacherToCourse(Teacher teacher){
        for (Course course: courseRepo.findAll()){
            if (teacher.getCourses().contains(course)) {
                course.setTeacher(teacher);
            }
        }
    }

    /**
     * This method overwrites Teachers.json with the latest version of the repository list.
     * @throws FileNotFoundException
     */
    public void writeTeacherFile() throws FileNotFoundException {
        try {
            // create `ObjectMapper` instance
            ObjectMapper mapper = new ObjectMapper();

            // create `ArrayNode` object
            ArrayNode arrayNode = mapper.createArrayNode();

            // create JSON objects for teacher
            for (Teacher teacher: teacherList) {
                ObjectNode obj1 = mapper.createObjectNode();
                obj1.put("firstName", teacher.getFirstName());
                obj1.put("lastName", teacher.getLastName());
                obj1.put("teacherId", teacher.getTeacherId());

                // create nested array
                ArrayNode courses = mapper.createArrayNode();
                for (Course course: teacher.getCourses()){
                    courses.add(course.getCourseId());
                }

                // add nested array to JSON object
                obj1.set("courses", courses);

                // add JSON objects to array
                arrayNode.add(obj1);
            }
            // convert `ArrayNode` to pretty-print JSON
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);

            // overwrites the json Teachers file
            FileWriter file = new FileWriter("..\\Lari_Maria_L3\\src\\lab3\\JsonFiles\\Teachers.json");
            file.write(json);
            file.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
