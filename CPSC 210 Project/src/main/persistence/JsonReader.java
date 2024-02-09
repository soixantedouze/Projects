package persistence;

import model.Agenda;
import model.Deadline;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//REFERENCE: JSON DEMO 2
// Represents a reader that reads agenda from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads agenda from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Agenda read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAgenda(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses agenda from JSON object and returns it
    private Agenda parseAgenda(JSONObject jsonObject) {
        Agenda a = new Agenda();
        addAgenda(a, jsonObject);
        return a;
    }


    // MODIFIES: agenda
    // EFFECTS: parses deadline from JSON object and adds it to agenda
    private void addDeadline(Agenda a, JSONObject jsonObject) {
        String course = jsonObject.getString("course");
        String task = jsonObject.getString("task");
        int month = jsonObject.getInt("month");
        int day = jsonObject.getInt("day");
        Deadline deadline = new Deadline(course, task, month, day);
        a.add(deadline);
    }

    // MODIFIES: agenda
    // EFFECTS: parses deadlines from JSON object and adds them to agenda
    private void addAgenda(Agenda a, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("agenda");
        for (Object json : jsonArray) {
            JSONObject nextDeadline = (JSONObject) json;
            addDeadline(a, nextDeadline);
        }
    }

}