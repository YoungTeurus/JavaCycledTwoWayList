package CycledTwoWayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoWayIteratorTest {

    @Test
    void getNextAndPrevious() {
        TwoWayListItem<Integer> first = new TwoWayListItem<>(1);
        TwoWayListItem<Integer> second = new TwoWayListItem<>(2);
        TwoWayListItem<Integer> third = new TwoWayListItem<>(3);

        first.setNext(second); first.setPrevious(third);
        second.setNext(third); second.setPrevious(first);
        third.setNext(first); third.setPrevious(second);

        TwoWayIterator<Integer> it = new TwoWayIterator<>(first);
        //                                                    прошл.  текущ.  вывод
        assertTrue(it.hasNext()); assertTrue(it.hasPrevious());
        assertEquals(first.getItem(), it.getNext());        // 1   ->   2   :   1
        assertTrue(it.hasNext()); assertTrue(it.hasPrevious());
        assertEquals(second.getItem(), it.getNext());       // 2   ->   3   :   2
        assertTrue(it.hasNext()); assertTrue(it.hasPrevious());
        assertEquals(third.getItem(), it.getNext());        // 3   ->   1   :   3
        assertTrue(it.hasNext()); assertTrue(it.hasPrevious());
        assertEquals(first.getItem(), it.getNext());        // 1   ->   2   :   1
        assertTrue(it.hasNext()); assertTrue(it.hasPrevious());

        assertEquals(second.getItem(), it.getPrevious());   // 2   ->   1   :   2
        assertTrue(it.hasNext()); assertTrue(it.hasPrevious());
        assertEquals(first.getItem(), it.getPrevious());    // 1   ->   3   :   1
        assertTrue(it.hasNext()); assertTrue(it.hasPrevious());
        assertEquals(third.getItem(), it.getPrevious());    // 3   ->   2   :   3
        assertTrue(it.hasNext()); assertTrue(it.hasPrevious());
        assertEquals(second.getItem(), it.getPrevious());   // 2   ->   1   :   2
        assertTrue(it.hasNext()); assertTrue(it.hasPrevious());
        assertEquals(first.getItem(), it.getPrevious());    // 1   ->   3   :   1
        assertTrue(it.hasNext()); assertTrue(it.hasPrevious());

        TwoWayListItem<Integer> forth = new TwoWayListItem<>(4);

        TwoWayIterator<Integer> it_2 = new TwoWayIterator<>(forth);
        assertTrue(it_2.hasNext()); assertTrue(it_2.hasPrevious());
        assertEquals(forth.getItem(), it_2.getNext());      // 4   ->   N/A :   4
        assertFalse(it_2.hasNext()); assertFalse(it_2.hasPrevious());

        TwoWayListItem<Integer> fifth = new TwoWayListItem<>(5);

        TwoWayIterator<Integer> it_3 = new TwoWayIterator<>(fifth);
        assertTrue(it_3.hasNext()); assertTrue(it_3.hasPrevious());
        assertEquals(fifth.getItem(), it_3.getPrevious());  // 5   ->   N/A :   5
        assertFalse(it_3.hasNext()); assertFalse(it_3.hasPrevious());
    }

    @Test
    void hasNext() {
        TwoWayListItem<Integer> first = new TwoWayListItem<>(1);
        TwoWayListItem<Integer> second = new TwoWayListItem<>(2);
        TwoWayListItem<Integer> third = new TwoWayListItem<>(3);

        second.setNext(third);
        third.setPrevious(second);

        // 1 элемент:
        TwoWayIterator<Integer> it = new TwoWayIterator<>(first);
        assertTrue(it.hasNext());
        it.getNext();
        assertFalse(it.hasNext());

        // 2+ элемента:
        TwoWayIterator<Integer> it_2 = new TwoWayIterator<>(second);
        assertTrue(it_2.hasNext());
        it_2.getNext();
        boolean temp = it_2.hasNext();
        assertTrue(temp);
        it_2.getNext();
        assertFalse(it_2.hasNext());
    }

    @Test
    void hasPrevious() {
    }

    @Test
    void remove() {
    }
}