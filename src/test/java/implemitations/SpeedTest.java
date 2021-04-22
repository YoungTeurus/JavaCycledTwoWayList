package implemitations;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpeedTest {
    @Test
    void speedTest() throws Exception {
        CycledTwoWayList<Integer> testList = new CycledTwoWayList<>();
        LinkedList<Integer> standartList = new LinkedList<>();


        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            testList.append(i);
        }
        System.out.println("Вставка 100000 в кастомный список за " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            standartList.add(i);
        }
        System.out.println("Вставка 100000 в стандартный список за " + (System.currentTimeMillis() - start));

        System.out.println();

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            assertEquals(i, testList.atIndex(i));
        }
        System.out.println("Получение 100000 из кастомного списка за " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            assertEquals(i, standartList.get(i));
        }
        System.out.println("Получение 100000 их стандартного списка за " + (System.currentTimeMillis() - start));
    }
}