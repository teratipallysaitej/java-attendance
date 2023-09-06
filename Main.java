import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Main extends JFrame {

    public static Data model;
    PlotData graph = new PlotData();
    private boolean isGraphPlotted = false;
    RosterTable rosterPanel = new RosterTable();

    public Main() {
        setLayout(new BorderLayout());
        setTitle("CSE563 Assignment 6");

        JMenu file_menu_item = new JMenu("File");

        JMenuItem load_roaster_menu_item = new JMenuItem("Load a Roster");
        JMenuItem attendance_menu_item = new JMenuItem("Add Attendance");
        JMenuItem save_menu_item = new JMenuItem("Save");
        JMenuItem plot_data_menu_item = new JMenuItem("Plot Data");

        file_menu_item.add(load_roaster_menu_item);
        file_menu_item.add(attendance_menu_item);
        file_menu_item.add(save_menu_item);
        file_menu_item.add(plot_data_menu_item);

        JMenuItem about_menu_item = new JMenuItem("About");
        JMenuBar menu_bar = new JMenuBar();
        menu_bar.add(file_menu_item);
        menu_bar.add(about_menu_item);

        add(menu_bar);
        setJMenuBar(menu_bar);


        
        add(rosterPanel, BorderLayout.CENTER);

        model = new Data();
        model.addObserver(rosterPanel);

        about_menu_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.displayAboutTab();
            }
        });

        load_roaster_menu_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(rosterPanel, BorderLayout.CENTER);
                List<StudentModel> ros = Controller.openFile();

                if (ros != null) {
                    Main.model.loadRosterData(ros);
                }
            }
        });

        attendance_menu_item.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {

                Main.model.loadAttendanceData(Controller.readAttendanceFile());
                if (isGraphPlotted) {
                    remove(graph);
                    isGraphPlotted = false;
                } else {
                    remove(rosterPanel);
                }
                if (rosterPanel != null)
                    remove(rosterPanel);

                add(rosterPanel);
                revalidate();
                repaint();
                validate();

            }
        });

        save_menu_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Data.studentRoster != null) {
                    String selectedPath = Controller.getUserChosenPath();
                    if (selectedPath != null) {
                        model.saveStudentData(selectedPath);
                    } else {
                        System.out.println("No path selected");
                    }
                } else {
                    System.out.println("No student data is present");
                }
            }
        });

        plot_data_menu_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isGraphPlotted) {
                    remove(graph);
                } else {
                    remove(rosterPanel);
                }
                isGraphPlotted = true;

                graph.mapData(Data.attendance);

                add(graph);
                revalidate();
                repaint();
                validate();
            }
        });

    }

    public static void main(String[] args) {

        Main entry = new Main();
        entry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        entry.setSize(new Dimension(400, 400));
        entry.setVisible(true);
    }
}