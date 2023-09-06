import java.util.*;
import java.util.stream.IntStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class contains the data of the students roster and attendenca data. 
 * It also contain some helper functions to access the data 
 * 
 * @author Shiva Sai Ram Mummoju
 */
@SuppressWarnings("deprecation")
public class Data extends Observable {

    public static final int minimumHeaders = 4;
    public static List<String> rosterTableHeaders;

    public static List<StudentModel> studentRoster;
    public static HashMap<String, ArrayList<String>> attendance = new HashMap<>();
    public static HashMap<String, HashMap<String, Integer>> attendanceData = new HashMap<>();

    public List<StudentModel> getRoster() {
        return studentRoster;
    }

    public Data() {
        loadTableHeaders();
    }

    private void loadTableHeaders() {
        rosterTableHeaders = new ArrayList();
        rosterTableHeaders.add("ID");
        rosterTableHeaders.add("First Name");
        rosterTableHeaders.add("Last Name");
        rosterTableHeaders.add("ASURITE");
    }

    /**
     * This method loads the roster data structure of the class. Also calls to notify its observers
     * 
     * @param rosterData
     */
    public void loadRosterData(List<StudentModel> rosterData) {
        rosterTableHeaders = rosterTableHeaders.subList(0, minimumHeaders); // reset to default headers
        Data.studentRoster = rosterData;
        Data.attendance = new HashMap<>();
        Data.attendanceData = new HashMap<>();

        setChanged();
        notifyObservers();
    }

    /**
     * This method loads the attendance data structure of the class. Also calls to notify its observers. 
     * 
     * @param att The data which has to be loaded
     */
    public void loadAttendanceData(HashMap<String, ArrayList<String>> att) {
        AttendanceData attObject = new AttendanceData();
        Data.attendanceData = attObject.mergeMinutes(att);
        attObject.dialog(studentRoster, att);

        setChanged();
        notifyObservers();

    }

    public void saveStudentData(String selectedFilePath) {
        try {
            FileWriter fileWriter = new FileWriter(selectedFilePath);

            if (!rosterTableHeaders.isEmpty()) {
                fileWriter.append(String.join(",", rosterTableHeaders));
                for (String dateKey : attendanceData.keySet()) {
                    fileWriter.append(String.join(",", dateKey));
                }
            }

            String[][] csvData = getStudentRosterTableData();

            for (String[] csvRow : csvData) {
                fileWriter.append("\n");
                fileWriter.append(String.join(",", csvRow));
            }

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns all the students data in a List. 
     * It also merges all the attendance data 
     * 
     * @return String[][] where it is a list of students 
     */
    public String[][] getStudentRosterTableData() {
        String[][] tableData = new String[studentRoster.size()][];

        int numColumns = getNumColumns();

        IntStream.range(0, studentRoster.size()).forEach(i -> {

            HashMap<String, HashMap<String, Integer>> attendanceData = Data.attendanceData;

            String[] stuAttributes = new String[numColumns];
            stuAttributes[0] = studentRoster.get(i).getID();
            stuAttributes[1] = studentRoster.get(i).getFirstName();
            stuAttributes[2] = studentRoster.get(i).getLastName();
            String asurite = studentRoster.get(i).getAsurite();
            stuAttributes[3] = asurite;
            int colIndex = 4;

            for (Iterator<String> iterator = attendanceData.keySet().iterator(); iterator.hasNext(); ) {
                String dateKey = iterator.next();

                HashMap<String, Integer> t = attendanceData.get(dateKey);
                Integer att = t.get(asurite);
                stuAttributes[colIndex++] = att == null ? "0" : att.toString();
            }

            tableData[i] = stuAttributes;
        });

        return tableData;
    }

    private int getNumColumns() {
        return rosterTableHeaders.size() + attendanceData.size();
    }

    /**
     * This method returns the total headers which are to be rendered in the table 
     * 
     * @return String[] of headers
     */
    public String[] getRosterHeaders() {

        String[] headersArray = new String[getNumColumns()];
        int i = 0;
        int j = 0, tableHeadersSize = rosterTableHeaders.size();
        while (true) {
            if (j >= tableHeadersSize) break;
            String s = rosterTableHeaders.get(j);
            headersArray[i] = s;
            i++;
            j++;
        }

        for (Iterator<String> iterator = attendanceData.keySet().iterator(); iterator.hasNext(); ) {
            String dateKey = iterator.next();
            headersArray[i++] = dateKey;
        }
        return headersArray;
    }

}
