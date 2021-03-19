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

    private int fix_index(int index) {
            if (index < 0) {
                index += items.length;
            }
            else if (index >= items.length) {
                index -= items.length;
            }

        return index;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst--;
        size++;
        if (size < items.length) {
            nextFirst = fix_index(nextFirst);
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        nextLast++;
        size++;
        if (size < items.length) {
            nextLast = fix_index(nextLast);
        }
    }

    public T removeFirst() {
        int targetPosition = nextFirst + 1;
        targetPosition = fix_index(targetPosition);
        nextFirst++;
        size--;
        nextFirst = fix_index(nextFirst);
        return items[targetPosition];
    }

    public T removeLast() {
        int targetPosition = nextLast - 1;
        targetPosition = fix_index(targetPosition);
        nextLast--;
        size--;
        nextLast = fix_index(nextLast);
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


    /** If no such item exists, returns null. */
    public T get(int index) {
        if (nextLast == nextFirst) {
            return null;
        }
        return items[nextFirst + 1 + index];
    }


}
