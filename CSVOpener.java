import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

/**
 * This class handles the function of a file chooser. This is used to load
 * csvFile
 * 
 * @author Saitej Teratipally
 */
public class CSVOpener extends JFileChooser {
    private JFileChooser fileChooser = new JFileChooser();
    private FileNameExtensionFilter fileNameFilter = new FileNameExtensionFilter("CSV Files", "csv");

    final Dimension preferedDimensions = new Dimension();

    public CSVOpener() {
        fileChooser.setFileFilter(fileNameFilter);

        preferedDimensions.setSize(
                Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2.25,
                Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2.25);
        fileChooser.setPreferredSize(preferedDimensions);
    }

    /**
     * This method is used to select CSV files using the system dialog box
     * 
     * @return - selected File Object
     */
    public File getUserSelectedFile() {
        File file;
        try {

            int dialogRet = fileChooser.showSaveDialog(getParent());
            file = fileChooser.getSelectedFile();

            if (dialogRet != JFileChooser.APPROVE_OPTION) {
                file = null;
            } else {
                String fileName = file.getName();
                if (fileName.isEmpty()) {
                    file = null;
                } else {
                    String[] subStrings = fileName.split("/.");
                    if ((subStrings.length == 0) || !subStrings[subStrings.length - 1].equals(".csv")) {
                        String filePath = file.getAbsolutePath();
                        String newFilePath = filePath + ".csv";
                        file = new File(newFilePath);
                    }
                }
            }
        } catch (java.lang.NullPointerException e) {
            file = null;
        }

        return file;
    }

}