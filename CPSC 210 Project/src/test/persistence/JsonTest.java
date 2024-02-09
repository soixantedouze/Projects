package persistence;


import model.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

//REFERENCE: JSON DEMO 2
public class JsonTest {
    protected void checkThingy(String course, String task, int month, int day, Deadline deadline) {
        assertEquals(course, deadline.getCourse());
        assertEquals(task, deadline.getTask());
        assertEquals(month, deadline.getMonth());
        assertEquals(day, deadline.getDay());
    }
}
