/**
 * @author hongbo
 *
 */
public class Student {
    /**
     * Instance variable.
     */
    private String firstName;
    /**
     * Instance variable.
     */
    private String lastName;
    /**
     * Instance variable.
     */
    private String andrewId;
    /**
     * Instance variable.
     */
    private String phoneNumber;

    /**
     * Constructor of Circle with side parameter.
     * @param newAndrewId value
     */
    public Student(String newAndrewId) {
        andrewId = newAndrewId;
    }
    /**
     * Constructor of Circle with side parameter.
     * @param s value
     */
    public Student(Student s) {
        andrewId = s.andrewId;
        firstName = s.firstName;
        lastName = s.lastName;
        phoneNumber = s.phoneNumber;
    }
    /**
     * Returns radius value of a Circle object.
     * @return double radius value
     */
    public String getAndrewId() {
        return andrewId;
    }
    /**
     * Returns radius value of a Circle object.
     * @return double radius value
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Returns radius value of a Circle object.
     * @return double radius value
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Returns radius value of a Circle object.
     * @return double radius value
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Returns radius value of a Circle object.
     * @param s value
     */
    public void setFirstName(String s) {
        firstName = s;
    }
    /**
     * Returns radius value of a Circle object.
     * @param s value
     */
    public void setLastName(String s) {
        lastName = s;
    }
    /**
     * Returns radius value of a Circle object.
     * @param s value
     */
    public void setPhoneNumber(String s) {
        phoneNumber = s;
    }
    /**
     * Returns radius value of a Circle object.
     * @return double radius value
     */
    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " (Andrew ID: " + getAndrewId() + ", Phone Number: " + getPhoneNumber() + ")";
    }
}
