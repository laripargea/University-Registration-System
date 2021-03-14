package lab3;

public class ExceptionsClass extends Exception {
    /**
     * This method indicates the exception that what should be entered in console is not an integer.
     */
    public static void mustTypeIntInConsole(){
        System.out.println("You should enter an integer!");
    }

    /**
     * This method indicates the exception that course/student doesn't exist
     * and can not be chosen for registration.
     */
    public static void registerException(){
        System.out.println("Student can not be registered, because Course/Student doesn't exist!");
    }

    /**
     * This method indicates the exception that course doesn't exist and can not be chosen.
     */
    public static void courseExistException(){
        System.out.println("Course not found! Enter an existing one!");
    }
}
