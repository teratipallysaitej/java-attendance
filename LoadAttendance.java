import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class helps to load the attendance file and read the contents of the
 * data from the file into the data Structure.
 * 
 * @author Prachi Gupta
 */
public class LoadAttendance {
    public static final String delimiter = ",";
    JFileChooser fc = new JFileChooser();

    /**
     * This method is used to choose the CSV files from the JFileChooser, transform
     * data to a List, and checks for the NullPointerException
     * 
     * @return HashMap containing the date and contents of the Attendance file for
     *         all attendance files uploaded
     */
    public HashMap<String, ArrayList<String>> getAttendanceFileData() {
        
        HashMap<String, ArrayList<String>> att = Data.attendance;
        
        ArrayList<String> result = new ArrayList<String>();
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fc.addChoosableFileFilter(filter);
        fc.showOpenDialog(null);
        File csvFile = fc.getSelectedFile();

        try {
            result = transformDataToList(csvFile);
        } catch (java.lang.NullPointerException e) {
            result = null;
        }
        String date = getAttendanceDate(csvFile);
        att.put(date, result);

        return att;
    }

    public StudentModel getStudentWithAsurite(String asurite){
        System.out.println("Searching" + asurite + "for");
        for(StudentModel s: Main.model.getRoster()){
            
            System.out.println("K"+s.getAsurite()+"k");
            if(asurite.equals(s.getAsurite())){
                System.out.println("Found " + s.getAsurite());
                return s;
            }
        }

        return null;
    }

    /**
     * This method stores the selected file data which is chosen from the
     * JFileChooser into the a List DataStructure
     * 
     * @param Attendance file
     * @return List containing the date and contents of the Attendance file
     */
    public ArrayList<String> transformDataToList(File csvFile) {
        ArrayList<String> readData = new ArrayList<String>();

        try {
            FileReader fr = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;


            while ((line = br.readLine()) != null) {
                
                tempArr = line.split(delimiter);
                
                for (String tempStr : tempArr) {
                    readData.add(tempStr);
                }
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return readData;
    }

    /**
     * This method extracts the date part from the file name of the uploaded file
     * and returns this date
     * 
     * @param Attendance file
     * @return date of Attendance file
     */
    public String getAttendanceDate(File csvFile) {
        String fileName = csvFile.getName();
        String arr[] = fileName.split(" ", 2);
        return arr[0];
    }
}
