import java.util.Observable;
import java.util.Observer;
import java.awt.*;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * This class contains the code to display the roster and student data in a tabular format. 
 * It reads data from the data.java class and renders it in the table in the panel 
 * 
 * @author Shiva Sai Ram Mummoju
 */
public class RosterTable extends JPanel implements Observer {

    protected JTable rosterTable;

    public RosterTable() {

        setLayout(new BorderLayout());

        Dimension screenDimensions = getDimension();
        setDimensions();
        setRosterTablePane(screenDimensions);

    }

    private Dimension getDimension() {
        Dimension screenDimensions = new Dimension();
        screenDimensions.setSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        setSize(screenDimensions);
        return screenDimensions;
    }

    private void setDimensions() {
        Dimension prefDimensions = new Dimension();
        prefDimensions.setSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
                Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
        setPreferredSize(prefDimensions);
    }

    private void setRosterTablePane(Dimension screenDimensions) {
        this.rosterTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(rosterTable);
        scrollPane.setSize(screenDimensions);
        add(scrollPane);
    }

    /**
     * This method takes the data from the Data class and renders it to the table 
     * This method is called whenever the Data class calls notify 
     * 
     * @param Observable o - The class instance it is observing
     */
    @Override
    public void update(Observable o, Object arg) {
        
        String[][] rosterData = getStudentRosterTableData((Data) o);
        String[] tableHeaderData = getRosterHeaders((Data) o);

        setTableModel(rosterData, tableHeaderData);
        rosterTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }

    private void setTableModel(String[][] rosterData, String[] tableHeaderData) {
        this.rosterTable.setModel(new DefaultTableModel(rosterData, tableHeaderData));
    }

    private String[][] getStudentRosterTableData(Data o) {
        return o.getStudentRosterTableData();
    }

    private String[] getRosterHeaders(Data o) {
        return o.getRosterHeaders();
    }

}
