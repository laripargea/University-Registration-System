package lab3.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTests {
    private static Person person;

    @BeforeEach
    void setUp() {
        person = new Person("Mihai","Oancea");
    }

    @Test
    void getFirstNameTestPositive(){
        assertTrue(person.getFirstName().equals("Mihai"));
    }

    @Test
    void getFirstNameTestNegative(){
        assertFalse(person.getFirstName().equals("Ovidiu"));
    }

    @Test
    void setFirstNameTestPositive(){
        person.setFirstName("Ovidiu");
        assertTrue(person.getFirstName().equals("Ovidiu"));
    }

    @Test
    void setFirstNameTestNegative(){
        person.setFirstName("Ovidiu");
        assertFalse(person.getFirstName().equals("Mihai"));
    }

    @Test
    void getLastNameTestPositive(){
        assertTrue(person.getLastName().equals("Oancea"));
    }

    @Test
    void getLastNameTestNegative(){
        assertFalse(person.getLastName().equals("Papuc"));
    }

    @Test
    void setLastNameTestPositive(){
        person.setLastName("Papuc");
        assertTrue(person.getLastName().equals("Papuc"));
    }

    @Test
    void setLastNameTestNegative(){
        person.setLastName("Papuc");
        assertFalse(person.getLastName().equals("Oancea"));
    }
}
