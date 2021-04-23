package synthesizer;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int pos;

        ArrayRingBufferIterator() {
            pos = first;
        }

        public boolean hasNext() {
            return (pos != last);
        }

        public T next() {
            T returnItem = rb[pos];
            pos += 1;
            if (pos == capacity()) {
                pos = 0;
            }
            return returnItem;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (fillCount == rb.length) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        last += 1;
        if (last == rb.length) {
            last = 0;
        }
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T item = rb[first];
        fillCount -= 1;
        first += 1;
        if (first == rb.length) {
            first = 0;
        }
        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("The Queue is empty");
        }
        T item = rb[first];
        return item;
    }

    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    public boolean contains(T item) {
        return Arrays.asList(rb).contains(item);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (this.capacity() != other.capacity()) {
            return false;
        }
        for (T item : this) {
            if (!other.contains(item)) {
                return false;
            }
        }
        return true;
    }

}

