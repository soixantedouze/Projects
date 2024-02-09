package ui;

import model.Agenda;
import model.Deadline;
import model.EventLog;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static java.awt.Color.*;

//GUI for Agenda
public class AgendaGUI extends JFrame implements WindowListener {
    private Scanner input;
    private static final String DATA = "./data/myFile.json";
    private static final int WIDTH = 800;
    private static final String DEADLINE_MESSAGE = "Add Deadline";
    private static final int HEIGHT = 600;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;
    private Agenda agenda;
    private Color color;
    private Graphics graphics;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private EventLog eventlog = EventLog.getInstance();
    ImageIcon chickenImage = new ImageIcon("ChickenDinner.png");

    //EFFECTS: Initiates Agenda Gui
    public AgendaGUI() throws FileNotFoundException {
        input = new Scanner(System.in);
        agenda = new Agenda();
        jsonWriter = new JsonWriter(DATA);
        jsonReader = new JsonReader(DATA);
        agenda = new Agenda();

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());

        addButtonPanel();


        setContentPane(desktop);
        setTitle("My Agenda");
        setSize(WIDTH, HEIGHT);

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);
        addWindowListener(this);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);

    }

    @Override
    public void windowOpened(WindowEvent e) {
        //Nothing
    }

    //EFFECTS: When window is closing prints log to console
    @Override
    public void windowClosing(WindowEvent e) {
        for (Event vent : eventlog) {
            System.out.println(vent.toString());
        }
        EventLog.getInstance().clear();

    }

    public void windowClosed(WindowEvent e) {
        //Nothing
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //Nothing
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //Nothing
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //Nothing
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //Nothing
    }

    //action for finishing deadline in agenda
    private class FinishedAgendaAction extends AbstractAction {
        FinishedAgendaAction() {
            super("Finished Deadline");
        }

        //EFFECTS: Defines action
        @Override
        public void actionPerformed(ActionEvent evt) {
            String course = JOptionPane.showInputDialog(null,
                    "Enter Course of finished deadline (1 word)",
                    DEADLINE_MESSAGE, JOptionPane.QUESTION_MESSAGE);
            String task = JOptionPane.showInputDialog(null,
                    "Enter Task of finished deadline (1 word)",
                    DEADLINE_MESSAGE, JOptionPane.QUESTION_MESSAGE);
            int count = agenda.size();
            for (Deadline d : agenda.getDeadlines()) {
                if (course.equals(d.getCourse()) && task.equals(d.getTask())) {
                    agenda.remove(d);
                    JOptionPane.showMessageDialog(null, "Deadline Finished!",
                            "Congratulations", JOptionPane.QUESTION_MESSAGE);
                    makeImage();
                }
            }
            if (agenda.size() == count) {
                JOptionPane.showMessageDialog(null, "Deadline Doesn't Exist",
                        "Could Not Find", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void makeImage() {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(500, 500));
        frame.add(new JLabel(chickenImage));
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    //action for viewing agenda
    private class ViewAgendaAction extends AbstractAction {

        ViewAgendaAction() {
            super("View Agenda");
        }

        //EFFECTS: Defines action
        @Override
        public void actionPerformed(ActionEvent evt) {
            color = ORANGE;
            JFrame agendaFrame = new JFrame();
            int size = agenda.size();
            agendaFrame.getContentPane().setBackground(color);
            agendaFrame.setLayout(new GridLayout(size, 4));
            for (Deadline d : agenda.getDeadlines()) {
                agendaFrame.add(new JLabel("course: " + d.getCourse()));
                agendaFrame.add(new JLabel("task: " + d.getTask()));
                agendaFrame.add(new JLabel("month: " + Integer.toString(d.getMonth())));
                agendaFrame.add(new JLabel("day: " + Integer.toString(d.getMonth())));

            }
            agendaFrame.setTitle("My Agenda");
            agendaFrame.setSize(new Dimension(500, 500));
            agendaFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            agendaFrame.setVisible(true);
            agendaFrame.revalidate();
            agendaFrame.repaint();


        }
    }


    //Action for Adding Deadline to agenda
    private class AddDeadlineAction extends AbstractAction {

        AddDeadlineAction() {
            super("Add Deadline");
        }

        //EFFECTS: Defines action
        @Override
        public void actionPerformed(ActionEvent evt) {
            String course = JOptionPane.showInputDialog(null,
                    "Enter Course (1 word)",
                    DEADLINE_MESSAGE, JOptionPane.QUESTION_MESSAGE);
            String task = JOptionPane.showInputDialog(null,
                    "Enter Task (1 word)",
                    DEADLINE_MESSAGE, JOptionPane.QUESTION_MESSAGE);
            String month = JOptionPane.showInputDialog(null,
                    "Enter month (1 number)",
                    DEADLINE_MESSAGE, JOptionPane.QUESTION_MESSAGE);
            String day = JOptionPane.showInputDialog(null,
                    "Enter day (1 number)",
                    DEADLINE_MESSAGE, JOptionPane.QUESTION_MESSAGE);
            try {
                Integer.parseInt(month);
                Integer.parseInt(day);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Valid Date",
                        "Please enter a valid number", JOptionPane.QUESTION_MESSAGE);
            }
            agenda.add(new Deadline(course, task, Integer.parseInt(month), Integer.parseInt(day)));
        }
    }


    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            AgendaGUI.this.requestFocusInWindow();
        }
    }

    // starts the application
    public static void main(String[] args) {
        try {
            new AgendaGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }


    //EFFECTS: Centers main on screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }


    //EFFECTS: Adds buttons to Jframe
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton(new AddDeadlineAction()));
        buttonPanel.add(new JButton(new ViewAgendaAction()));
        buttonPanel.add(new JButton(new FinishedAgendaAction()));
        buttonPanel.add(new JButton(new EditDeadlineAction()));
        buttonPanel.add(new JButton(new SaveAgendaAction()));
        buttonPanel.add(new JButton(new LoadAgendaAction()));

        buttonPanel.setLayout(new GridLayout(2, 4));
        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    //Action for editing deadlines
    private class EditDeadlineAction extends AbstractAction {


        EditDeadlineAction() {
            super("Edit Deadline");
        }

        //EFFECTS: Defines action
        @Override
        public void actionPerformed(ActionEvent evt) {
            String courseEdit = editCourse();
            String taskEdit = editTask();
            String coursenew = JOptionPane.showInputDialog(null,
                    "Enter new course name (1 word)", DEADLINE_MESSAGE, JOptionPane.QUESTION_MESSAGE);
            String tasknew = JOptionPane.showInputDialog(null,
                    "Enter new task(1 word)", DEADLINE_MESSAGE, JOptionPane.QUESTION_MESSAGE);
            String newmonth = JOptionPane.showInputDialog(null,
                    "Enter new month (1 number)", DEADLINE_MESSAGE, JOptionPane.QUESTION_MESSAGE);
            String newday = JOptionPane.showInputDialog(null,
                    "Enter new day (1 number)", DEADLINE_MESSAGE, JOptionPane.QUESTION_MESSAGE);
            if (editCourse(courseEdit, taskEdit, coursenew, tasknew, newmonth, newday)) {
                JOptionPane.showMessageDialog(null, "Success",
                        "Deadline changed!", JOptionPane.QUESTION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Not Found",
                        "Deadline not found", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //EFFECTS: Allows user to input a task they want to edit
    public String editTask() {
        String taskEdit = JOptionPane.showInputDialog(null,
                "Enter Task of deadline you want to edit (1 word)",
                DEADLINE_MESSAGE, JOptionPane.QUESTION_MESSAGE);
        return taskEdit;
    }

    //EFFECTS: Allows user to input a course they want to edit
    public String editCourse() {
        String courseEdit = JOptionPane.showInputDialog(null,
                "Enter Course of deadline you want to edit (1 word)",
                DEADLINE_MESSAGE, JOptionPane.QUESTION_MESSAGE);
        return courseEdit;
    }

    //EFFECTS: returns true if inputted deadline is in agenda then edits deadline otherwise
    //returns false
    private boolean editCourse(String courseEdit, String taskEdit, String coursenew, String tasknew,
                               String newmonth, String newday) {
        boolean bool = true;

        for (Deadline d : agenda.getDeadlines()) {
            if (courseEdit.equals(d.getCourse()) && taskEdit.equals(d.getTask())) {
                d.editCourse(coursenew);
                d.editTask(tasknew);
                d.editDate(Integer.parseInt(newmonth), Integer.parseInt(newday));
                return true;
            }
        }
        return false;
    }

    //Action for saving Agenda
    private class SaveAgendaAction extends AbstractAction {

        SaveAgendaAction() {
            super("Save Agenda");
        }

        //Defines action
        //Reference: JSON DEMO
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(agenda);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null, "Success",
                        "Saved Agenda to " + DATA, JOptionPane.QUESTION_MESSAGE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error",
                        "Unable to write to file " + DATA, JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    //Action for loading agenda
    private class LoadAgendaAction extends AbstractAction {

        LoadAgendaAction() {
            super("Load Agenda");
        }

        //EFFECTS: defines action
        //Reference: JSON DEMO
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                agenda = jsonReader.read();
                JOptionPane.showMessageDialog(null, "Success",
                        "Loaded Agenda from " + DATA, JOptionPane.QUESTION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error",
                        "Unable to read file from " + DATA, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
