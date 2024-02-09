package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DeadlineTest {
    Deadline deadline1;
    Deadline deadline2;
    Deadline deadline3;
    Agenda testListEmpty;
    Agenda testList1;
    Agenda testList2;


    @BeforeEach

    public void setUp() {
        deadline1 = new Deadline("course", "task", 03, 01);
        deadline2 = new Deadline("course", "task", 02, 11);
        deadline3 = new Deadline("course", "task", 05, 21);
        testListEmpty = new Agenda();
        testList1 = new Agenda();
        testList2 = new Agenda();
        testList1.add(deadline1);
        testList2.add(deadline1);
        testList2.add(deadline2);


    }

    @Test
    public void deadlineEditTaskTest() {
        Deadline dt = deadline1;
        dt.editTask("edited task");

        assertEquals("edited task", dt.getTask());
    }

    @Test
    public void deadlineEditDateTest() {
        Deadline dt = deadline2;
        dt.editDate(3, 4);

        assertEquals(3, dt.getMonth());
        assertEquals(4, dt.getDay());
    }
    @Test
    public void deadlineEditCourseTest() {
        Deadline dt = deadline1;
        dt.editCourse("math");

        assertEquals("math", dt.getCourse());
    }


    @Test
    public void addDeadlineTestEmpty() {
        Agenda test1 = testListEmpty;
        test1.add(deadline1);

        assertEquals(1, test1.size());
        assertEquals(deadline1, test1.get(0));
    }

    @Test
    public void addDeadlineTest1() {
        Agenda test2 = testList1;
        test2.add(deadline2);

        assertEquals(2, test2.size());
        assertEquals(deadline1, test2.get(0));
        assertEquals(deadline2, test2.get(1));
    }

    @Test
    public void addDeadlineTest2() {
        Agenda test3 = testList2;
        test3.add(deadline3);

        assertEquals(3, test3.size());
        assertEquals(deadline1, test3.get(0));
        assertEquals(deadline2, test3.get(1));
        assertEquals(deadline3, test3.get(2));

    }

    @Test
    public void removeDeadlineTest2() {
        Agenda test3 = testList2;
        test3.remove(deadline3);

        assertEquals(2, test3.size());
        assertEquals(deadline1, test3.get(0));
        assertEquals(deadline2, test3.get(1));
        assertFalse(test3.contains(deadline3));

    }

    @Test
    public void containsDeadlineTestTrue() {
        Agenda test3 = testList2;
        boolean t = test3.contains(deadline1);

        assertEquals(2, test3.size());
        assertTrue(t);

    }

    @Test
    public void containsDeadlineTestFalse() {
        Agenda test3 = testList2;
        boolean t = test3.contains(deadline3);

        assertEquals(2, test3.size());
        assertFalse(t);

    }

}


