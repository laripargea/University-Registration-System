package lab3.repository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lab3.model.Course;
import lab3.model.Student;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentFileRepository implements ICrudRepository<Student>{
    private List<Student> studentList;
    CourseFileRepository courseRepo;

    //Constructor
    public StudentFileRepository(List<Student> studentList, CourseFileRepository courseRepo) throws IOException {
        this.studentList = studentList;
        this.courseRepo = courseRepo;
        parseStudentsFile();
    }

    //Getter for studentList
    public List<Student> getstudentList() {
        return studentList;
    }

    //Setter for studentList
    public void setstudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    /**
     * This method reads each student from the file and adds him/her to the repository list.
     * @throws IOException
     */
    public void parseStudentsFile() throws IOException {
        Reader reader = new BufferedReader(new FileReader("..\\Lari_Maria_L3\\src\\lab3\\JsonFiles\\Students.json")); //file

        //read from the json file
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode pm : parser) {
            List<Course> enrolledCoursesTemp = new ArrayList<Course>();
            Student s = new Student("", "", 0, 0, enrolledCoursesTemp);

            //set the attributes for each student
            s.setFirstName(pm.path("firstName").asText());
            s.setLastName(pm.path("lastName").asText());
            s.setStudentId(pm.path("studentId").asInt());
            s.setTotalCredits(pm.path("totalCredits").asInt());

            //in the json file it is stored only the id of the course
            List<Integer> enrolledCourses = new ArrayList<Integer>();
            for (JsonNode obj : pm.path("enrolledCourses")) {
                enrolledCourses.add(obj.asInt());
            }

            List<Course> enrolledCourses2 = new ArrayList<Course>();
            for (long elem : enrolledCourses)
                enrolledCourses2.add(courseRepo.findOne(elem));

            s.setEnrolledCourses(enrolledCourses2);

            //add to repository list
            studentList.add(s);

            addStudentToCourse(s);
        }
        reader.close();
    }

    /**
     * This method adds a student to a course.
     * @param student Student
     */
    public void addStudentToCourse(Student student){
        for (Course course: courseRepo.findAll()){
            if (student.getEnrolledCourses().contains(course)) {
                course.getStudentsEnrolled().add(student);
            }
        }
    }

    /**
     * This method overwrites Students.json with the latest version of the repository list.
     * @throws FileNotFoundException
     */
    public void writeStudentFile() throws FileNotFoundException {
        try {
            // create `ObjectMapper` instance
            ObjectMapper mapper = new ObjectMapper();

            // create `ArrayNode` object
            ArrayNode arrayNode = mapper.createArrayNode();

            // create JSON objects for student
            for (Student student: studentList) {
                ObjectNode obj1 = mapper.createObjectNode();
                obj1.put("firstName", student.getFirstName());
                obj1.put("lastName", student.getLastName());
                obj1.put("studentId", student.getStudentId());
                obj1.put("totalCredits", student.getTotalCredits());

                // create nested array
                ArrayNode courses = mapper.createArrayNode();
                for (Course course: student.getEnrolledCourses()){
                    courses.add(course.getCourseId());
                }

                // add nested array to JSON object
                obj1.set("enrolledCourses", courses);

                // add JSON objects to array
                arrayNode.add(obj1);
            }
            // convert `ArrayNode` to pretty-print JSON
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);

            // overwrites the json Students file
            FileWriter file = new FileWriter("..\\Lari_Maria_L3\\src\\lab3\\JsonFiles\\Students.json");
            file.write(json);
            file.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param id -the id of the student to be returned; id must not be null
     * @return the student with the specified id or null - if there is no student with the given id
     */
    @Override
    public Student findOne(Long id) {
        for(Student student: studentList) {
            if (student.getStudentId() == id)
                return student;
        }
        return null;
    }

    /**
     * @return all students
     */
    @Override
    public Iterable<Student> findAll() {
        return studentList;
    }

    /**
     * @param entity entity must be not null
     * @return null- if the given student is saved otherwise returns the student(id already exists)
     */
    @Override
    public Student save(Student entity) {
        if(findOne(entity.getStudentId())==null)
            studentList.add(entity);
        else
            return entity;

        return null;
    }

    /**
     * removes the student with the specified id
     *
     * @param id id must be not null
     * @return the removed student or null if there is no student with the given id
     */
    @Override
    public Student delete(Long id) {
        Student tempStudent; //temporary student for return after remove
        for(Student student: studentList) {
            if (student.getStudentId() == id)
            {
                tempStudent=student;
                studentList.remove(student);
                return tempStudent;
            }
        }
        return null;
    }

    /**
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity - (e.g id does not exist).
     */
    @Override
    public Student update(Student entity) {
        for(Student student: studentList) {
            if (student.getStudentId() == entity.getStudentId()) {
                student.setTotalCredits(entity.getTotalCredits());
                student.setEnrolledCourses(entity.getEnrolledCourses());
                student.setLastName(entity.getLastName());
                student.setFirstName(entity.getFirstName());
                return null;
            }
        }
        return entity;
    }
}
