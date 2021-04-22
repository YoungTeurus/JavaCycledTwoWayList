package implemitations;

import interfaces.TwoWayList;
import interfaces.TwoWayListItem;

import java.security.InvalidParameterException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

/**
 * Циклический двусвязный список.
 *
 * Данная реализация списка поддерживает следующие операции над собой:
 * 1) append(T) - добавление элемента в конец списка
 *    remove(T) - удаление элемента из списка
 * 2) indexOf(T) - получение индекса элемента в списке
 *    atIndex(int) - получение элемента по индексу в списке
 * 3) length() - получение длины списка
 *    isEmpty() - проверка списка на пустоту
 * 4) iterator() - получение итератора для прохождения списка
 *    iteratorTail() - получение итератора для прохождения списка в обратном порядке
 *    map(Function<T, Boolean>) - выполнение функции для каждого элемента списка
 *
 * @author Азаренко Сергей, 18-ИВТ-1
 */
public class CycledTwoWayList<T> implements TwoWayList<T> {
    private implemitations.TwoWayListItem<T> head;
    private implemitations.TwoWayListItem<T> tail;

    static final int NOT_FOUND = -1;

    public CycledTwoWayList(){
        makeEmpty();
    }

    private void makeEmpty(){
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
        head = new implemitations.TwoWayListItem<>(object);
        head.setNext(head);
        head.setPrevious(head);
        tail = head;
    }

    private void createAndConnectNewListItem(T object){
        implemitations.TwoWayListItem<T> newListItem = new implemitations.TwoWayListItem<>(head, tail, object);
        head.setPrevious(newListItem);
        tail.setNext(newListItem);
        tail = newListItem;
    }

    public void remove(T object) {
        findAndRemoveListItemWithItemEqualsTo(object);
    }

    private void findAndRemoveListItemWithItemEqualsTo(T object){
        implemitations.TwoWayListItem<T> listItemWithObject = findListItemWithItemEqualsTo(object);
        removeListItem(listItemWithObject);
    }

    private implemitations.TwoWayListItem<T> findListItemWithItemEqualsTo(T object){
        AtomicReference<implemitations.TwoWayListItem<T>> foundListItem = new AtomicReference<>(null);

        mapThroughListItems(listItem -> {
            // Если содержимое listItem равно object, сохраняем ссылку на него в foundListItem и выходим из цикла,
            // иначе - проходим по списку до конца.
            if(isItemInListItemEquals(listItem, object)){
                foundListItem.set((implemitations.TwoWayListItem<T>)listItem);  // TODO: убрать приведение типа?
                return false;
            }
            return true;
        });

        return foundListItem.get();
    }

    private boolean isItemInListItemEquals(TwoWayListItem<T> listItem, T object){
        try {
            return listItem.compareItem(object) == 0;
        }
        catch (Exception e){
            // Если данные несравнимы, то они всегда не равны.
            return false;
        }
    }

    private void removeListItem(implemitations.TwoWayListItem<T> listItem){
        if (listItem == null){
            return;
        }
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

    private void correctHeadOrTailIfHeadOrTailEquals(implemitations.TwoWayListItem<T> listItem){
        if(isHeadListItem(listItem)){
            head = listItem.getNext();
        }
        if(isTailListItem(listItem)){
            tail = listItem.getPrevious();
        }
    }

    private boolean isHeadListItem(implemitations.TwoWayListItem<T> listItem){
        return listItem == head;
    }

    private boolean isTailListItem(implemitations.TwoWayListItem<T> listItem){
        return listItem == tail;
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

    public T atIndex(int index) throws Exception {
        if (index < 0){
            throw new InvalidParameterException("Index must be positive!");
        }
        if (isEmpty()){
            throw new Exception("Array is empty!");
        }

        implemitations.TwoWayListItem<T> currentElement = head;
        for(int i = 0; i < index; i++){
            currentElement = currentElement.getNext();
        }

        return currentElement.getItem();
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

    public CycledTwoWayIterator<T> iterator() {
        return new CycledTwoWayIterator<>(this.head);
    }
    public CycledTwoWayIterator<T> iteratorTail(){
        return new CycledTwoWayIterator<>(tail);
    }

    public void map(Function<T, Boolean> functionToApply){
        mapThroughListItems(listItem -> functionToApply.apply(listItem.getItem()));
    }

    /**
     * Применяет функцию functionToApply для каждого элемента списка. Функции передаётся текущий элемент списка.
     * Если функция возвращает false, обход завершается преждевременно.
     * @param functionToApply Функция для применения.
     */
    private void mapThroughListItems(Function<TwoWayListItem<T>, Boolean> functionToApply){
        CycledTwoWayIterator<T> it = iterator();
        while (!it.reachedEnd()){
            it.getNext();
            if(!functionToApply.apply(it.currentListItem)){
                break;
            }
        }
    }
}
