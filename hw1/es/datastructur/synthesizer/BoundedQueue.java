package es.datastructur.synthesizer;

public interface BoundedQueue<T> {
    /**
     * return size of the buffer
     */
    int capacity();

    /**
     * @return number of items currently in the buffer
     */
    int fillCount();

    /**
     * add item x to the end
     * @param x
     */
    void enqueue(T x);

    /**
     * delete and return item from the front
     * @return
     */
    T dequeue();

    /**
     * return (but not delete) item from the front
     * @return
     */
    T peek();

    default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        }
        return false;
    }

    default boolean isFull() {
        if (fillCount() == capacity()) {
            return true;
        }
        return false;
    }
}
