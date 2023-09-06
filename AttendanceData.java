import java.util.*;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * The class AttendanceData can merge minutes corresponding to the same id from
 * attendance and also display dialog box showing data found in attendance file
 * which is not present in roster file.
 * 
 * @author Tapaswi Reddy Busireddy
 */
public class AttendanceData {
    /**
     * This method merges the minutes belonging to the same id in an attendance
     * file.
     * 
     * @param attData
     * @return HashMap<String, HashMap<String, Integer>>
     */
    public HashMap<String, HashMap<String, Integer>> mergeMinutes(HashMap<String, ArrayList<String>> attData) {
        HashMap<String, HashMap<String, Integer>> attendanceMap = new HashMap<>();
        HashMap<String, Integer> hm;
        for (String date : attData.keySet()) {
            hm = new HashMap<>();
            if (!attendanceMap.containsKey(date)) {
                List<String> attendance = new ArrayList<>(attData.get(date));
                for (int i = 0; i < attendance.size(); i = i + 2) {
                    String key = attendance.get(i).replaceAll("[^A-Za-z0-9]+", "");
                    int value = hm.getOrDefault(key, 0) + Integer.parseInt(attendance.get(i + 1));
                    hm.put(key, value);
                }
                attendanceMap.put(date, hm);
            }
        }
        System.out.println(attendanceMap);
        return attendanceMap;
    }

    /**
     * This method will display the dialog box showing the attendance data of
     * ASURITE IDs that are not present in roster data file.
     * 
     * @param rosterData 
     * @param attData   
     */
    public void dialog(List<StudentModel> rosterData, HashMap<String, ArrayList<String>> attData) {

        List<String> rosID = new ArrayList<>();
        for (StudentModel student : rosterData) {
            rosID.add(student.getAsurite());

        }
        HashMap<String, HashMap<String, Integer>> hm = Data.attendanceData;

        for (String date : hm.keySet()) {
            HashMap<String, Integer> hm1 = hm.get(date);
            List<String> attID = new ArrayList<>();
            attID.clear();
            for (Map.Entry<String, Integer> entry : hm1.entrySet())
                attID.add(entry.getKey());
            attID.removeAll(rosID);
            String start = "<html>For date:" + date + "<br/>";
            String first = start + "Data loaded for " + rosID.size() + " users in the roster.<br/> " + attID.size()
                    + " addition attendee was found.<br/> ";

            for (int i = 0; i < attID.size(); i++) {
                if (hm1.containsKey(attID.get(i)))
                    first = first + attID.get(i) + ", connected for " + hm1.get(attID.get(i)) + " minutes.<br/>";

            }
            String final1 = first + "</html>";
            JDialog dialogBox = new JDialog();
            JLabel label = new JLabel(final1, SwingConstants.CENTER);
            dialogBox.add(label);
            dialogBox.setSize(400, 100);
            dialogBox.setVisible(true);

        }
    }

}
