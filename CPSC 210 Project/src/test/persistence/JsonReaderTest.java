package persistence;

import model.Deadline;
import model.Agenda;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

//REFERENCE: JSON DEMO 2
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Agenda agenda = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            System.out.println("pass");
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAgenda.json");
        try {
            Agenda agenda = reader.read();
            assertEquals(0, agenda.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderNormalAgenda.json");
        try {
            Agenda agenda = reader.read();
            LinkedList<Deadline> listDeadline = agenda.getDeadlines();
            assertEquals(2, listDeadline.size());
            checkThingy("course", "task", 2, 3, listDeadline.get(0));
            checkThingy("course2", "task2", 5, 6, listDeadline.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}