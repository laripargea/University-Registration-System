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

public class CourseFileRepository implements ICrudRepository<Course>{
    private List<Course> courseList;

    //Constructor
    public CourseFileRepository(List<Course> courseList) throws IOException {
        this.courseList = courseList;
        parseCoursesFile();
    }

    //Getter for courseList
    public List<Course> getcourseList() {
        return courseList;
    }

    //Setter for teacherList
    public void setcourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    /**
     * This method reads each course from the file and adds him/her to the repository list.
     * @throws IOException
     */
    public void parseCoursesFile() throws IOException {
        Reader reader3 = new BufferedReader(new FileReader("..\\Lari_Maria_L3\\src\\lab3\\JsonFiles\\Courses.json")); //file

        //read from the json file
        ObjectMapper objectMapper3 = new ObjectMapper();
        JsonNode parser3 = objectMapper3.readTree(reader3);

        for (JsonNode pm : parser3) {
            List<Course> coursesTempTeacher = new ArrayList<Course>();
            Teacher tTemp = new Teacher("","",0,coursesTempTeacher);
            List<Student> studsTemp = new ArrayList<Student>();
            Course c = new Course("",tTemp,0,studsTemp,0,0);

            //set the attributes for each course
            c.setName(pm.path("name").asText());
            c.setMaxEnrollment(pm.path("maxEnrollment").asInt());
            c.setCredits(pm.path("credits").asInt());
            c.setCourseId(pm.path("courseId").asInt());

            //add to the repository list
            courseList.add(c);
        }
        reader3.close();
    }

    /**
     * This method overwrites Courses.json with the latest version of the repository list.
     * @throws FileNotFoundException
     */
    public void writeCourseFile() throws FileNotFoundException {
        try {
            // create `ObjectMapper` instance
            ObjectMapper mapper = new ObjectMapper();

            // create `ArrayNode` object
            ArrayNode arrayNode = mapper.createArrayNode();

            // create JSON objects for course
            for (Course course: courseList) {
                ObjectNode obj1 = mapper.createObjectNode();
                obj1.put("name", course.getName());
                obj1.put("maxEnrollment", course.getMaxEnrollment());
                obj1.put("credits", course.getCredits());
                obj1.put("courseId", course.getCourseId());

                // add JSON objects to array
                arrayNode.add(obj1);
            }
            // convert `ArrayNode` to pretty-print JSON
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);

            // overwrites the json Courses file
            FileWriter file = new FileWriter("..\\Lari_Maria_L3\\src\\lab3\\JsonFiles\\Courses.json");
            file.write(json);
            file.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param id -the id of the course to be returned; id must not be null
     * @return the course with the specified id or null - if there is no course with the given id
     */
    @Override
    public Course findOne(Long id) {
        for(Course course: courseList) {
            if (course.getCourseId() == id)
                return course;
        }
        return null;
    }

    /**
     * @return all courses
     */
    @Override
    public Iterable<Course> findAll() {
        return courseList;
    }

    /**
     * @param entity entity must be not null
     * @return null- if the given course is saved otherwise returns the course(id already exists)
     */
    @Override
    public Course save(Course entity) {
        if(findOne(entity.getCourseId())==null)
            courseList.add(entity);
        else
            return entity;

        return null;
    }

    /**
     * removes the course with the specified id
     *
     * @param id id must be not null
     * @return the removed course or null if there is no course with the given id
     */
    @Override
    public Course delete(Long id) {
        Course tempCourse; //temporary teacher for return after remove
        for(Course course: courseList) {
            if (course.getCourseId() == id)
            {
                tempCourse = course;
                courseList.remove(course);
                return tempCourse;
            }
        }
        return null;
    }

    /**
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity - (e.g id does not exist).
     */
    @Override
    public Course update(Course entity) {
        for(Course course: courseList) {
            if (course.getCourseId() == entity.getCourseId()) {
                course.setName(entity.getName());
                course.setStudentsEnrolled(entity.getStudentsEnrolled());
                course.setCredits(entity.getCredits());
                course.setMaxEnrollment(entity.getMaxEnrollment());
                course.setTeacher(entity.getTeacher());
                return null;
            }
        }
        return entity;
    }
}
