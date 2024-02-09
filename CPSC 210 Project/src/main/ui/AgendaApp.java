package ui;

import model.Agenda;
import model.Deadline;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

//REFERENCE: JSON DEMO 2
public class AgendaApp {
    private static final String DATA = "./data/myFile.json";
    private Scanner input;
    private Agenda agenda;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: constructor for agenda interface
    public AgendaApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        agenda = new Agenda();
        jsonWriter = new JsonWriter(DATA);
        jsonReader = new JsonReader(DATA);
        runAgenda();
    }



    //EFFECTS: runs interface
    private void runAgenda() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                inputProcessing(command);
            }
        }

        System.out.println("\nGoodBye!");
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nAgenda:");
        System.out.println("\ta -> add deadline");
        System.out.println("\ts -> show agenda");
        System.out.println("\tf -> finished deadline");
        System.out.println("\te -> edit deadline course/task");
        System.out.println("\td -> edit deadline date");
        System.out.println("\tl -> look for course");
        System.out.println("\tsave -> save agenda");
        System.out.println("\tload -> load agenda");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void inputProcessing(String command) {
        if (command.equals("a")) {
            addDeadline();
        } else if (command.equals("s")) {
            showAgenda();
        } else if (command.equals("f")) {
            removeDeadline();
        } else if (command.equals("e")) {
            editDeadline();
        } else if (command.equals("d")) {
            editDeadlineDate();
        } else if (command.equals("l")) {
            lookFor();
        } else if (command.equals("save")) {
            saveAgenda();
        } else if (command.equals("load")) {
            loadAgenda();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: reads add deadline input
    private void addDeadline() {
        String course = null;
        String task = null;
        System.out.println("name of your course (1 word): ");
        course = input.next();
        System.out.println("task/homework to do (1 word): ");
        task = input.next();
        System.out.println("month it is due in digits: ");
        int month = input.nextInt();
        System.out.println("day it is due in digits: ");
        int day = input.nextInt();
        Deadline deadline = new Deadline(course, task, month, day);
        agenda.add(deadline);
    }


    //EFFECTS: prints agenda
    private void showAgenda() {
        LinkedList<Deadline> listAgenda = agenda.getDeadlines();
        for (Deadline d : listAgenda) {
            System.out.println("course: " + d.getCourse() + " ," + "task: " + d.getTask() + " ," + "month: "
                    + d.getMonth() + " ," + "day: " + d.getDay());
            ;
        }
    }


    //EFFECTS: removes deadline specified
    private void removeDeadline() {
        LinkedList<Deadline> listAgenda = agenda.getDeadlines();
        System.out.println("what task did you finish?");
        String finishedDeadline = input.next();
        System.out.println("for what course?");
        String finishedCourse = input.next();
        int count = listAgenda.size();
        for (Deadline d : listAgenda) {
            if (finishedDeadline.equals(d.getTask())
                    && finishedCourse.equals(d.getCourse())) {
                agenda.remove(d);
                System.out.println("Yay! You finished the task!");
            }
        }
        if (listAgenda.size() == count) {
            System.out.println("You do not have this deadline");
        }
    }


    //EFFECTS: edits the course and task to given values of the deadline specified
    private void editDeadline() {
        LinkedList<Deadline> listAgenda = agenda.getDeadlines();
        boolean t = true;
        String edit = "";
        String name = "";
        String task = "";
        System.out.println("what task do you want to edit?");
        edit = input.next();
        System.out.println("new course name (1 word): ");
        name = input.next();
        System.out.println("new task (1 word): ");
        task = input.next();
        for (Deadline d : listAgenda) {
            if (edit.equals(d.getTask())) {
                d.editCourse(name);
                d.editTask(task);
                t = false;
                System.out.println("changed!");
            }
        }
        if (t) {
            System.out.println("You do not have this task");
        }
    }

    //EFFECTS: edits the date to the given date of the deadline specified
    private void editDeadlineDate() {
        LinkedList<Deadline> listAgenda = agenda.getDeadlines();
        boolean t = true;
        String edit = "";
        System.out.println("what task do you want to edit?");
        edit = input.next();
        System.out.println("new month and day (space in between in that order): ");
        int month = input.nextInt();
        int day = input.nextInt();
        for (Deadline d : listAgenda) {
            if (edit.equals(d.getTask())) {
                d.editDate(month, day);
                t = false;
                System.out.println("changed!");
            }
        }
        if (t) {
            System.out.println("You do not have this task");
        }
    }

    //EFFECTS: reads look for deadline input
    public void lookFor() {
        System.out.println("what course are you looking for? (1 word)");
        String course = input.next();
        System.out.println("what task are you looking for? (1 word)");
        String task = input.next();
        System.out.println("What month is it due? (1 digit)");
        int month = input.nextInt();
        System.out.println("What day is it due? (1 digit)");
        int day = input.nextInt();
        searchCourse(course, task, month, day);
    }

    //EFFECTS: searches for the course with the given task in the agenda
    public void searchCourse(String course, String task, int month, int day) {
        LinkedList<Deadline> listAgenda = agenda.getDeadlines();
        boolean t = true;
        int i = 0;
        for (Deadline d : listAgenda) {
            if (course.equals(d.getCourse()) && task.equals(d.getTask())
                    && month == d.getMonth() && day == d.getDay()) {
                System.out.println("course: " + d.getCourse() + " ," + "task: " + d.getTask() + " ," + "month: "
                        + d.getMonth() + " ," + "day: " + d.getDay() + " number " + i + " on the list!");
                t = false;
            }
            i++;
        }
        if (t) {
            System.out.println("does not contain this deadline");
        }
    }

    // EFFECTS: saves the agenda to file
    private void saveAgenda() {
        try {
            jsonWriter.open();
            jsonWriter.write(agenda);
            jsonWriter.close();
            System.out.println("Saved  agenda to " + DATA);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + DATA);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads agenda from file
    private void loadAgenda() {
        try {
            agenda = jsonReader.read();
            System.out.println("Loaded agenda from " + DATA);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + DATA);
        }
    }

}

