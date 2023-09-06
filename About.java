
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class handles the About tab information dialog
 * 
 * @author Saitej Teratipally
 */

public class About {

    /**
     * This method is used to display information data of team members
     * It uses Jpanel to display the information and Jlabel to display the team members
     */
    
    public void displayTeamInfo() {
        JFrame frame = new JFrame();
        JDialog dialog = new JDialog(frame, "About");
        JPanel panel = new JPanel();


        String tf = "<html> <style>html {text-align: center} html {word-wrap: break-word} </style>" +
                "<h1>CSE563 Assignment<br></h1>"
                + "<h2>Members:<br></h2>"
                + "<p style = `font-size:25px;`>Namrata,Prachi,Tapaswi,shreya,shiva,saitej  </p>"
                + "</html>";

        JLabel labelforteam = new JLabel(tf);
        panel.add(labelforteam);
        dialog.add(panel);
        dialog.setSize(800, 350);
        dialog.setVisible(true);
    }
}
