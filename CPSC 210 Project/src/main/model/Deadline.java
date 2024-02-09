package model;

import org.json.JSONObject;
import persistence.Writable;

//REFERENCE: JSON DEMO 2
//Represents a deadline you have to meet with
//the course, the task, the month and day, and that it is not done.
public class Deadline implements Writable {
    protected int day;
    protected int month;
    protected String task;
    protected String course;

    //EFFECTS: Deadline has a given course, task, month, day.
    public Deadline(String course, String task, int month, int day) {
        this.course = course;
        this.task = task;
        this.month = month;
        this.day = day;
    }


    //EFFECTS: changes task to given task
    public void editTask(String t) {
        EventLog.getInstance().logEvent(new Event("Changed Task " + task + " to " + t));
        this.task = t;
    }


    //REQUIRES: int > 0
    //EFFECTS: changes date(month and day) to given month
    //and day
    public void editDate(int month, int day) {
        EventLog.getInstance().logEvent(new Event("Changed Date " + this.month + ", " + this.day
                + " to " + month + ", " + day));
        this.month = month;
        this.day = day;
    }


    //EFFECTS: changes course to given course
    public void editCourse(String c) {
        EventLog.getInstance().logEvent(new Event("Changed Course " + course + " to " + c));
        this.course = c;
    }


    public String getCourse() {
        return course;
    }

    public String getTask() {
        return this.task;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    //EFFECTS: sends data to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("course", course);
        json.put("task", task);
        json.put("month", month);
        json.put("day", day);
        return json;
    }


}
