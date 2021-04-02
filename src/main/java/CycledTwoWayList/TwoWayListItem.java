package CycledTwoWayList;

public class TwoWayListItem<T> implements Interfaces.TwoWayListItem<T> {
    private TwoWayListItem<T> next;
    private TwoWayListItem<T> previous;
    private T item;

    TwoWayListItem(T item){
        setNext(null);
        setPrevious(null);
        setItem(item);
    }

    TwoWayListItem(TwoWayListItem<T> next, TwoWayListItem<T> previous, T item){
        setNext(next);
        setPrevious(previous);
        setItem(item);
    }

    void setNext(TwoWayListItem<T> next) {
        this.next = next;
    }

    void setPrevious(TwoWayListItem<T> previous) {
        this.previous = previous;
    }

    void setItem(T item) {
        this.item = item;
    }

    public TwoWayListItem<T> getNext() {
        return next;
    }

    public TwoWayListItem<T> getPrevious() {
        return previous;
    }

    public T getItem() {
        return item;
    }

    boolean isSelfCycled(){
        return next == this;
    }

    void connectPreviousToNextAndResetConnections(){
        connectPreviousToNext();
        resetConnections();
    }

    void connectPreviousToNext(){
        previous.setNext(next);
        next.setPrevious(previous);
    }

    void resetConnections(){
        next = null;
        previous = null;
    }

}
