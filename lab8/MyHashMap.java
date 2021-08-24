import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

public class MyHashMap<K, V> implements Map61B<K, V>{

    private int N;
    private int M;
    private double loadFactor;
    private bucketMap[] buckets = null;

    private class bucketMap<K, V> {
        private ArrayList<K> key_bucket;
        private ArrayList<V> value_bucket;

        public bucketMap() {
            key_bucket = new ArrayList<>();
            value_bucket = new ArrayList<>();
        }

        public void addToBucket(K key, V value) {
            if (key_bucket.contains(key)) {
                int index = key_bucket.indexOf(key);
                value_bucket.set(index, value);
            } else {
                key_bucket.add(key);
                value_bucket.add(value);
                N++;
            }
        }

        public V findKey(K key) {
            int index = key_bucket.indexOf(key);
            if (index != -1) {
                return value_bucket.get(index);
            } else {
                return null;
            }
        }

        public void iterKeysToSet(Set<K> allKeys) {
            for (K key: key_bucket) {
                allKeys.add(key);
            }
        }
    }

    public MyHashMap() {
        this(16, 0.75);
    }
    
    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }
    
    public MyHashMap(int initialSize, double loadFactor) {
        N = 0;
        this.M = initialSize;
        this.loadFactor = loadFactor;
        buckets = new bucketMap[M];
        for (int i = 0; i < M; i++) {
            buckets[i] = new bucketMap();
        }
    }

    private void resize(int new_M) {
        // this will be added in addToBucket
        N = 0;
        bucketMap<K, V>[] new_buckets = new bucketMap[new_M];
        for (int i = 0; i < new_M; i++) {
            new_buckets[i] = new bucketMap();
        }
        Set<K> allKeys = keySet();
        for (K key: allKeys) {
            int index = (key.hashCode() & 0x7fffffff) % new_M;
            new_buckets[index].addToBucket(key, get(key));
        }
        M = new_M;
        buckets = new_buckets;
    }

    @Override
    public void clear() {
        N = 0;
        buckets = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (buckets == null) {
            return false;
        }
        int index = (key.hashCode() & 0x7fffffff) % M;
        V value = (V) buckets[index].findKey(key);
        if (value != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public V get(K key) {
        if (buckets == null) {
            return null;
        }
        int index = (key.hashCode() & 0x7fffffff) % M;
        V value = (V) buckets[index].findKey(key);
        return value;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void put(K key, V value) {
        if ((double) N / (double) M > loadFactor) {
            resize(M * 2);
        }
        int index = (key.hashCode() & 0x7fffffff) % M;
        buckets[index].addToBucket(key, value);
    }

    @Override
    public Set<K> keySet() {
        Set<K> allKeys = new HashSet<>();
        for(int i = 0; i < M; i++) {
            bucketMap<K, V> bucket = buckets[i];
            bucket.iterKeysToSet(allKeys);
        }
        return allKeys;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

}
