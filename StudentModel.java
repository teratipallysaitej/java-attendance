import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * This class holds all the values corresponding to a student in one place.  
 * It contains the fields and helper access functions for a student. 
 * 
 * @author Shiva Sai Ram Mummoju
 */
public class StudentModel {

    private String ID;
    private String firstName;
    private String lastName;
    private String asurite;
    private HashMap<String, Integer> attendance;

    public StudentModel(
            String ID,
            String firstName,
            String lastName,
            String asureite) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.asurite = asureite;
        this.attendance = new LinkedHashMap<>();
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAsurite() {
        return this.asurite;
    }

    public void setAsurite(String asurite) {
        this.asurite = asurite;
    }

    public void addAttendance(String date, int time) {
        attendance.put(date, attendance.getOrDefault(date, 0) + time);
    }

    public HashMap<String, Integer> getAttendance() {
        return attendance;
    }
}
