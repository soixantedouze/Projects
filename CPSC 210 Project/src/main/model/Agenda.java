package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

//REFERENCE: JSON DEMO 2
//Agenda is a list of deadlines
public class Agenda implements Writable {
    private LinkedList<Deadline> agenda;

    //EFFECTS: creates a list of deadlines with 0 deadlines
    //MODIFIES: this
    public Agenda() {
        agenda = new LinkedList<Deadline>();
    }

    //EFFECTS: adds deadline to agenda
    public void add(Deadline deadline) {
        agenda.add(deadline);
        EventLog.getInstance().logEvent(new Event("Added new Deadline: " + deadline.getCourse()
                + " " + deadline.getTask() + " " + deadline.getMonth() + " " + deadline.getDay()));
    }

    //EFFECTS: removes deadline from agenda
    public void remove(Deadline deadline) {
        agenda.remove(deadline);
        EventLog.getInstance().logEvent(new Event("Removed Deadline: " + deadline.getCourse() + " "
                + deadline.getTask() + " " + deadline.getMonth() + " " + deadline.getDay()));
    }

    //EFFECTS: returns true if course exists and false otherwise
    public boolean contains(Deadline deadline) {
        if (agenda.contains(deadline)) {
            return true;
        }
        return false;
    }

    public int size() {
        return agenda.size();
    }

    public LinkedList<Deadline> getDeadlines() {
        return this.agenda;
    }

    public Deadline get(int i) {
        return agenda.get(i);
    }


    //EFFECTS: makes jsonObj with agenda
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("agenda", agendaToJson());
        return json;
    }

    // EFFECTS: returns deadlines in this agenda as a JSON array
    private JSONArray agendaToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Deadline d : agenda) {
            jsonArray.put(d.toJson());
        }
        return jsonArray;
    }


}


