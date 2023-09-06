import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

/**
 * This class plots a graph on the JPanel where y axis denotes
 * the number of students and x-axis denotes the dates.
 * @author namratagaur
 *
 */
public class PlotData extends JPanel{
	List<String> dates;
	List<Integer> numberOfStudents;
	final int HEIGHT = 10;
	final int WIDTH = 20;
	
	PlotData() {
		dates = new ArrayList<>();
		numberOfStudents = new ArrayList<>();
	}
	
	/**
	 * The function creates a new data structure for the student number and updates
	 * the data structure with the data.
	 * @param dateToStudent is the attendance data we get from the calling function.
	 */
	public void mapData(Map<String, ArrayList<String>> dateToStudent) {
		dates = new ArrayList<>();
		numberOfStudents = new ArrayList<>();
		transformData(dateToStudent);
	}
	
	/**
	 * This function transforms the map of dates and list of asurites to
	 * a list containing the dates and the number of students list.
	 * @param dateToStudent denotes the map to be transformed.
	 */
	private void transformData(Map<String, ArrayList<String>> dateToStudent) {
		for(String date: dateToStudent.keySet()) {
			dates.add(date);
			numberOfStudents.add(dateToStudent.get(date).size());
		}
	}
	
	/**
	 * This map paints the bar graph as per the data on the JPanel.
	 * @param g denotes the graphics on which the components lile rectangle
	 * and texts are drawn in the panel.
	 */
	public void paintComponent (Graphics g)
	   {            
	      super.paintComponent (g);          
	      int max = Integer.MIN_VALUE;
	      for (Integer value : numberOfStudents) {
	    	  max = Math.max(max, value);
	      }
	      int width = (getWidth() / (4 * dates.size()));
	      
	      g.drawLine(width, getHeight() - 30, getWidth(), getHeight() - 30);
	      
	      
	      g.drawLine(width, 30, width, getHeight() - 30);
	      Graphics2D g2 = ( Graphics2D )g;
	      for(int i = 0; i < 5; i++) {
	    	  g2.drawString((max/(i+1)) + "" , width - 12, (int)
	    		      (((getHeight()-30)) * ((double)(i+1)/5))-30);
	      }
	      
	      g2.rotate(-1 * Math.toRadians(90));
	      g2.drawString("Number Of Students", - 1 * getHeight() + 150, width - 15);
	      g2.rotate( Math.toRadians(90));
	      
	      int x = 2*width;
	      for (int i = 0; i < dates.size(); i++) {
		      String value = dates.get(i);
		      int height = (int)
		      ((getHeight()-30) * ((double)numberOfStudents.get(i) / max));
		      g.setColor(Color.BLUE);
		      g.fillRect(x, getHeight() - height, width, height-30);
		      g.setColor(Color.black);
		      g.drawRect(x, getHeight() - height, width, height-30);
		      g.drawString(dates.get(i), x, getHeight() - 15);
		      x += 2 * (width);

	      }
	      
	   }
}
