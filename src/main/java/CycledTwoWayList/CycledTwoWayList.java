package CycledTwoWayList;

import Interfaces.ITwoWayList;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * Циклический двусвязный список.
 * @author Азаренко Сергей, 18-ИВТ-1
 */
public class CycledTwoWayList<T> implements ITwoWayList<T> {
    private TwoWayListItem<T> head;
    private TwoWayListItem<T> tail;

    static int NOT_FOUND = -1;

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
        TwoWayListItem<T> listItemWithObject = findListItemWithItemEqualsTo(object);
        if(listItemWithObject == null){
            return;
        }
        remove(listItemWithObject);
    }

    private TwoWayListItem<T> findListItemWithItemEqualsTo(T object){
        // TODO: убрать повторение кода (см. map) - проблема с получением доступа к it.currentListItem (нарушение инкапсуляции)
        // TODO: убрать уродское приведение типа
        CycledTwoWayIterator<T> it = (CycledTwoWayIterator<T>) iterator();
        while (it.hasNext() && !it.reachedEnd()){
            if (it.isCurrentItemEquals(object)){
                // TODO: убрать уродское приведение типа
                return (TwoWayListItem<T>)it.currentListItem;
            }
            it.getNext();
        }
        return null;
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
        // Если голова зацикленна сама на себя - в списке находится лишь один элемент:
        if (isHeadSelfCycled()){
            return 1;
        }

        // Проходимся по списку, инкрементируя length на каждом шаге:
        AtomicInteger length = new AtomicInteger(0);

        Function<T, Boolean> increaseLength = item -> {
            length.getAndIncrement();
            return true;
        };

        map(increaseLength);

        return length.get();
    }

    public boolean isEmpty(){
        return head == null || tail == null;
    }

    private boolean isHeadSelfCycled(){
        return head.isSelfCycled();
    }

    // Применяет функцию functionToApply для всех элементов списка.
    // Функции передаётся текущее значение элемента списка.
    // Если функция возвращает false, обход завершается преждевременно.
    public void map(Function<T, Boolean> functionToApply){
        Interfaces.CycledTwoWayIterator<T> it = iterator();
        while (!it.reachedEnd()){
            if(!functionToApply.apply(it.getNext())){
                break;
            }
        }
    }

    public int indexOf(T object) {
        AtomicInteger index = new AtomicInteger(0);
        AtomicBoolean hasFoundObject = new AtomicBoolean(false);

        Function<T, Boolean> increaseIndexWhileObjectNotFound = item -> {
            if(item.equals(object)){
                hasFoundObject.set(true);
                return false;  // Как-только нашли нужный объект прекращаем обход
            }
            index.getAndIncrement();
            return true;
        };

        this.map(increaseIndexWhileObjectNotFound);

        if (!hasFoundObject.get()){
            return NOT_FOUND;
        }
        return index.get();
    }

    public Interfaces.CycledTwoWayIterator<T> iterator() {
        return new CycledTwoWayIterator<T>(this.head);
    }
}
