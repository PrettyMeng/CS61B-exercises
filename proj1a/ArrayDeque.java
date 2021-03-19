public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextLast;
    private int nextFirst;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextLast = 4;
        nextFirst = 3;
    }

    private int fixIndex(int index) {
        if (index < 0) {
            index += items.length;
        }  else if (index >= items.length) {
            index -= items.length;
        }

        return index;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst--;
        size++;
        nextFirst = fixIndex(nextFirst);
        if (size == items.length) {
            resize(size * 4);
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        nextLast++;
        size++;
        nextLast = fixIndex(nextLast);
        if (size == items.length) {
            resize(size * 4);
        }
    }

    public T removeFirst() {
        int targetPosition = nextFirst + 1;
        targetPosition = fixIndex(targetPosition);
        nextFirst++;
        size--;
        nextFirst = fixIndex(nextFirst);
        if (size <= items.length / 4) {
            resize(items.length / 2);
        }
        return items[targetPosition];
    }

    public T removeLast() {
        int targetPosition = nextLast - 1;
        targetPosition = fixIndex(targetPosition);
        nextLast--;
        size--;
        nextLast = fixIndex(nextLast);
        if (size <= items.length / 4) {
            resize(items.length / 2);
        }
        return items[targetPosition];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void printDeque() {
        int position = nextFirst + 1;
        while (position != nextLast) {
            System.out.println(items[position]);
            position++;
            if (position == items.length) {
                position -= items.length;
            }
        }
    }

    private void resize(int newSize) {
        /** nextFirst==nextLast means the deque is full, we need to enlarge.*/
        if (nextFirst >= nextLast - 1) {
            T[] newItems = (T[]) new Object[newSize];
            System.arraycopy(items, nextFirst + 1, newItems, 0, items.length - nextFirst - 1);
            System.arraycopy(items, 0, newItems, items.length - nextFirst - 1, nextLast);
            nextLast = size;
            nextFirst = newSize - 1;
            items = newItems;
        }   else {
            T[] newItems = (T[]) new Object[newSize];
            System.arraycopy(items, nextFirst + 1, newItems, 0, nextLast - nextFirst - 1);
            nextLast = size;
            nextFirst = newSize - 1;
            items = newItems;
        }

    }


    /** If no such item exists, returns null. */
    public T get(int index) {
        if (size == 0) {
            return null;
        }
        index = fixIndex(nextFirst + 1 + index);
        return items[index];
    }


}
