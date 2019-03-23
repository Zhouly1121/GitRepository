import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


/**
 * @author hongbo
 *
 */
public class Directory {
    /**
     * Instance variable.
     */
    private int size;
    /**
     * Instance variable.
     */
    private Map<String, Student> mapAndrewId = new HashMap<String, Student>();
    /**
     * Instance variable.
     */
    private Map<String, List<Student>> mapFirst = new HashMap<String, List<Student>>();
    /**
     * Instance variable.
     */
    private Map<String, List<Student>> mapLast = new HashMap<String, List<Student>>();
    /**
     * Constructor of Circle with side parameter.
     */
    public Directory() {
        size = 0;
    }
    /**
     * Constructor of Circle with side parameter.
     * @param stu value
     */
    public void addStudent(Student stu) {
        if (stu != null) {
            if (!mapAndrewId.containsKey(stu.getAndrewId())) {
                Student s = new Student(stu);
                mapAndrewId.put(s.getAndrewId(), s);
                //Add to mapFirst.
                if (mapFirst.containsKey(s.getFirstName())) {
                    mapFirst.get(s.getFirstName()).add(s);
                } else {
                    List<Student> list = new ArrayList<Student>();
                    list.add(s);
                    mapFirst.put(s.getFirstName(), list);
                }
                //Add to mapLast.
                if (mapLast.containsKey(s.getLastName())) {
                    mapLast.get(s.getLastName()).add(s);
                } else {
                    List<Student> list = new ArrayList<Student>();
                    list.add(s);
                    mapLast.put(s.getLastName(), list);
                }
                size++;
            } else {
                throw new IllegalArgumentException();
            }
        } else {
                throw new IllegalArgumentException();
            }

    }
    /**
     * Constructor of Circle with side parameter.
     * @param andrewId value
     */
    public void deleteStudent(String andrewId) {
        if (mapAndrewId.containsKey(andrewId)) {
            Student s = mapAndrewId.get(andrewId);
            mapAndrewId.remove(andrewId);
            //Delete from mapFirst.
            mapFirst.get(s.getFirstName()).remove(s);
            //Delete from mapLast.
            mapLast.get(s.getLastName()).remove(s);
            size--;
        } else {
            throw new IllegalArgumentException();
        }

    }
    /**
     * Constructor of Circle with side parameter.
     * @param andrewId value
     * @return student
     */
    public Student searchByAndrewId(String andrewId) {
        if (andrewId != null) {
            if (mapAndrewId.containsKey(andrewId)) {
                return new Student(mapAndrewId.get(andrewId));
            } else {
                return null;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
    /**
     * Constructor of Circle with side parameter.
     * @param firstName value
     * @return list
     */
    public List<Student> searchByFirstName(String firstName) {
        if (firstName != null) {
            if (mapFirst.containsKey(firstName)) {
                List<Student> list = new ArrayList<Student>(mapFirst.get(firstName));
                List<Student> newlist = new ArrayList<Student>();
                for (Student stu : list) {
                    newlist.add(new Student(stu));
                }
                return newlist;
            } else {
                return new ArrayList<Student>(0);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
    /**
     * Constructor of Circle with side parameter.
     * @param lastName value
     * @return list
     */
    public List<Student> searchByLastName(String lastName) {
        if (lastName != null) {
            if (mapLast.containsKey(lastName)) {
                List<Student> list = new ArrayList<Student>(mapLast.get(lastName));
                List<Student> newlist = new ArrayList<Student>();
                for (Student stu : list) {
                    newlist.add(new Student(stu));
                }
                return newlist;
            } else {
                return new ArrayList<Student>(0);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
    /**
     * Constructor of Circle with side parameter.
     * @return mapAndrewId
     */
    public Map<String, Student> getMapAndrewId() {
        return mapAndrewId;
    }
    /**
     * Constructor of Circle with side parameter.
     * @return size
     */
    public int size() {
        return size;
    }
}
