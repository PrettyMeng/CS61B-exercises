public class MyHashMap<K, V> implements Map61B<K, V>{

    private int size;
    public MyHashMap() {
        size = 0;
    }
    
    public MyHashMap(int initialSize) {
        size = 0;
    }
    
    public MyHashMap(int initialSize, double loadFactor) {
        size = 0;
    }

    public void clear() {

    }

    public boolean containsKey(K key) {
        return true;
    }

    public V get(K key) {
        return null;
    }

    public int size() {
        return size;
    }
}
