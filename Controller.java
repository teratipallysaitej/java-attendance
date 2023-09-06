import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {
    public static void displayAboutTab() {
        About dis = new About();
        dis.displayTeamInfo();
    }

     /**
     * This method is used to perform the action events after the Load Roster is clicked
     * 
     * @return List<Student>
     */
    public static List<StudentModel> openFile() {
        LoadRoster file = new LoadRoster();
        return file.fileChooser();
    }

    public static HashMap<String, ArrayList<String>> readAttendanceFile() {
        LoadAttendance file = new LoadAttendance();
        return file.getAttendanceFileData();
    }

    public static String getUserChosenPath() {
        return new CSVOpener().getUserSelectedFile().getAbsolutePath().toString();
    }
}
