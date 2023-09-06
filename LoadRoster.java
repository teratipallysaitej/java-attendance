import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class helps to load the roster file and read the contents of the data
 * from the file into the data Structure.
 * 
 * @author Shreya Reddy Bolla
 */
public class LoadRoster {
    public static final String delimiter = ",";
    JFileChooser fc = new JFileChooser();

    /**
     * This method is used to choose the CSV files from the JFileChooser and also
     * checks for the NullPointerException
     * 
     * @return List<Student>
     */
    public List<StudentModel> fileChooser() {
        List<StudentModel> result = new ArrayList<>();
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fc.addChoosableFileFilter(filter);
        fc.showOpenDialog(null);
        File csvFile = fc.getSelectedFile();
        try {
            result = openFile(csvFile);
        } catch (java.lang.NullPointerException e) {
            result = null;
        }
        System.out.println(result);
        return result;
    }

    /**
     * This method is used to create the student object which is used to return the list 
     * after reading the contents
     * 
     * @param details
     * @return Student
     */
    public StudentModel newStudent(String[] details) {
        String ID = details[0];
        String firstName = details[1];
        String lastName = details[2];
        String asurite = details[3];

        StudentModel student = new StudentModel(ID, firstName, lastName, asurite.strip().trim());
        System.out.println("Created student with ID: " + ID);
        return student;
    }

    /**
     * This method stores the selected file data which is choosed from the
     * JFileChooser into the a List DataStructure
     * 
     * @param csvFile
     * @return List<String>
     */
    public List<StudentModel> openFile(File csvFile) {
        List<StudentModel> readData = new ArrayList<>();
        
        try {
            FileReader fr = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                //readData.add(tempArr);
                readData.add(newStudent(tempArr));
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return readData;
    }
}
