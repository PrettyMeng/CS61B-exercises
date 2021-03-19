public class LinkedListDeque<T> {
    private Node<T> sentinal;
    private int size;

    public LinkedListDeque() {
        sentinal = new Node<T>(null, sentinal, sentinal);
        size = 0;
    }

    //@source https://www.youtube.com/watch?v=JNroRiEG7U4
    public LinkedListDeque(LinkedListDeque other) {
        sentinal = new Node<T>(null, sentinal, sentinal);
        size = 0;
        int s = other.size();
        for (int i = 0; i < s; i++) {
            //  Wierd! Why do I need a type transition here??
            // Could be put in the signature as well: LinkedListDeque<T> Other
            T item = (T) other.get(i);
            this.addFirst(item);
            size++;
        }
    }

    private class Node<TT> {
        private TT item;
        private Node<TT> next;
        private Node<TT> prev;

        Node(TT i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    public void addFirst(T item) {
        if (size == 0) {
            Node<T> newNode = new Node<>(item, sentinal, sentinal);
            sentinal.next = newNode;
            sentinal.prev = newNode;
        }   else {
            Node<T> newNode = new Node<>(item, sentinal.next, sentinal);
            sentinal.next.prev = newNode;
            sentinal.next = newNode;
        }
        size++;
    }

    public void addLast(T item) {
        if (size == 0) {
            Node<T> newNode = new Node<>(item, sentinal, sentinal);
            sentinal.next = newNode;
            sentinal.prev = newNode;
        }   else {
            Node<T> newNode = new Node<>(item, sentinal, sentinal.prev);
            sentinal.prev.next = newNode;
            sentinal.prev = newNode;
        }
        size++;
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
        Node<T> p = sentinal.next;
        while (p != sentinal) {
            System.out.println(p.item);
            p = p.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }   else {
            T deletedItem = sentinal.next.item;
            sentinal.next = sentinal.next.next;
            sentinal.next.prev = sentinal;
            size--;
            return deletedItem;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }   else {
            T deletedItem = sentinal.prev.item;
            sentinal.prev = sentinal.prev.prev;
            sentinal.prev.next = sentinal;
            size--;
            return deletedItem;
        }
    }

    public T get(int index) {
        if (index < size) {
            Node<T> p = sentinal.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.item;
        }   else {
            return null;
        }
    }

    public T getRecursive(int index) {
        T item = getRecursive(index, sentinal.next);
        return item;
    }

    public T getRecursive(int index, Node<T> p) {
        if (index == 0) {
            return p.item;
        }   else {
            return getRecursive(index - 1, p.next);
        }
    }




}
