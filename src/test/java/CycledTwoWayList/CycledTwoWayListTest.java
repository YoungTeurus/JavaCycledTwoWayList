package CycledTwoWayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CycledTwoWayListTest {

    @Test
    void append() {
        CycledTwoWayList<Integer> testList = new CycledTwoWayList<Integer>();
        testList.append(1);
        testList.append(2);
        testList.append(3);
        testList.append(4);
        testList.append(5);
        testList.append(6);

        assertEquals(6, testList.length());
        assertEquals(5, testList.indexOf(6));
    }

    @Test
    void remove() {
    }

    @Test
    void length() {
        CycledTwoWayList<Integer> testList = new CycledTwoWayList<Integer>();

        assertEquals(0, testList.length());

        testList.append(5);
        assertEquals(1, testList.length());

        testList.append(5);
        assertEquals(2, testList.length());

        testList.append(5);
        assertEquals(3, testList.length());

        testList.append(5);
        assertEquals(4, testList.length());
    }

    @Test
    void isEmpty() {
        CycledTwoWayList<Integer> testList = new CycledTwoWayList<Integer>();

        assertTrue(testList.isEmpty());

        testList.append(5);

        assertFalse(testList.isEmpty());

        testList.append(6);

        assertFalse(testList.isEmpty());
    }

    @Test
    void indexOf() {
    }

    @Test
    void iterator() {
    }
}