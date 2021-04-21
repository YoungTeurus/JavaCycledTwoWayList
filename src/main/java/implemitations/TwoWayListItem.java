package implemitations;

public class TwoWayListItem<T> implements interfaces.TwoWayListItem<T> {
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

    @Override
    public int compareItem(T other) throws Exception {
        if(!(item instanceof Comparable)){
            throw new Exception("TwoWayListItem::compareItem: other не реализует интерфейс Comparable.");
        }
        //noinspection unchecked
        return ((Comparable<T>)item).compareTo(other);
    }

    public TwoWayListItem<T> getPrevious() {
        return previous;
    }

    public T getItem() {
        return item;
    }

    boolean isSelfCycled(){
        return next == this || previous == this;
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
