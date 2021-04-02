package CycledTwoWayList;

import Interfaces.TwoWayList;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * Циклический двусвязный список.
 * @author Азаренко Сергей, 18-ИВТ-1
 */
public class CycledTwoWayList<T> implements TwoWayList<T> {
    private TwoWayListItem<T> head;
    private TwoWayListItem<T> tail;

    public CycledTwoWayList(){
        head = null;
        tail = null;
    }

    public void append(T object) {
        if (isEmpty()){
            createdSelfCycledHeadTail(object);
            return;
        }
        createAndConnectNewListItem(object);
    }

    private void createdSelfCycledHeadTail(T object){
        head = new TwoWayListItem<T>(object);
        head.setNext(head);
        head.setPrevious(head);
        tail = head;
    }

    private void createAndConnectNewListItem(T object){
        TwoWayListItem<T> newListItem = new TwoWayListItem<T>(head, tail, object);
        head.setPrevious(newListItem);
        tail.setNext(newListItem);
        tail = newListItem;
    }

    public void remove(T object) {

    }

    private void remove(TwoWayListItem<T> listItem){
        // Если единственный элемент: очищаем список
        if(length() == 1){
            listItem.resetConnections();
            makeEmpty();
            return;
        }
        correctHeadOrTailIfHeadOrTailEquals(listItem);

        // Основной алгоритм:
        listItem.connectPreviousToNextAndResetConnections();
    }

    private void makeEmpty(){
        head = null;
        tail = null;
    }

    private void correctHeadOrTailIfHeadOrTailEquals(TwoWayListItem<T> listItem){
        if(isHeadListItem(listItem)){
            head = listItem.getNext();
        }
        if(isTailListItem(listItem)){
            tail = listItem.getPrevious();
        }
    }

    private boolean isHeadListItem(TwoWayListItem<T> listItem){
        return listItem == head;
    }

    private boolean isTailListItem(TwoWayListItem<T> listItem){
        return listItem == tail;
    }

    public int length() {
        if (isEmpty()){
            return 0;
        }
        // Если голова зацикленна сама на себя - в списке находится лишь один элемент
        if (head.isSelfCycled()){
            return 1;
        }

        // int length = 0;
        AtomicInteger length = new AtomicInteger(0);

        // CycledTwoWayIterator<T> it = iterator();
        // while (it.hasNext() && !it.reachedEnd()){
        //     length++;
        //     it.getNext();
        // }

        map(obj -> {
            length.getAndIncrement();
            return null;
        });

        // return length;
        return length.get();
    }

    public void map(Function<T, Void> functionToApply){
        CycledTwoWayIterator<T> it = iterator();
        while (it.hasNext() && !it.reachedEnd()){
            functionToApply.apply(it.getNext());
        }
    }

    public boolean isEmpty(){
        return head == null || tail == null;
    }

    public int indexOf(T object) {
        return 0;
    }

    public CycledTwoWayIterator<T> iterator() {
        return new CycledTwoWayIterator<T>(this.head);
    }
}
