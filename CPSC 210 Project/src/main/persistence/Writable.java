package persistence;

import model.Deadline;
import org.json.JSONObject;

//REFERENCE: JSON DEMO 2
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
