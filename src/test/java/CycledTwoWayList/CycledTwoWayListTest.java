package CycledTwoWayList;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

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
        CycledTwoWayList<Integer> testList = new CycledTwoWayList<Integer>();
        testList.append(1);
        testList.append(2);
        testList.append(3);
        testList.append(4);
        testList.append(5);
        testList.append(6);

        assertEquals(6, testList.length());
        assertEquals(5, testList.indexOf(6));

        testList.remove(3);

        assertEquals(5, testList.length());
        assertEquals(4, testList.indexOf(6));

        testList.remove(6);

        assertEquals(4, testList.length());
        assertEquals(CycledTwoWayList.NOT_FOUND, testList.indexOf(6));

        testList.remove(6);

        assertEquals(4, testList.length());
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
        CycledTwoWayList<Integer> testList = new CycledTwoWayList<Integer>();
        testList.append(1);
        testList.append(2);
        testList.append(3);
        testList.append(4);
        testList.append(5);
        testList.append(6);

        assertEquals(0, testList.indexOf(1));
        assertEquals(1, testList.indexOf(2));
        assertEquals(2, testList.indexOf(3));
        assertEquals(3, testList.indexOf(4));
        assertEquals(4, testList.indexOf(5));
        assertEquals(5, testList.indexOf(6));
    }

    @Test
    void map() {
        CycledTwoWayList<Integer> testList = new CycledTwoWayList<Integer>();
        testList.append(1);
        testList.append(2);
        testList.append(3);
        testList.append(4);
        testList.append(5);
        testList.append(6);

        AtomicInteger sum = new AtomicInteger(0);

        testList.map(item -> {sum.getAndAdd(item); return true;});

        assertEquals(1+2+3+4+5+6, sum.get());
    }
}