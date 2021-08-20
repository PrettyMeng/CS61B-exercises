import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private TreeNode<K, V> root;
    private int size;

    private class TreeNode<K extends Comparable<K>, V>{
        private K key;
        private V value;
        private TreeNode<K, V> left;
        private TreeNode<K, V> right;

        TreeNode(K key, V value, TreeNode<K, V> left, TreeNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns true if and only if this dictionary contains KEY as the
     *  key of some key-value pair. */
    @Override
    public boolean containsKey(K key) {
        V gotV = get(key);
        if (gotV == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(TreeNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new TreeNode<K, V>(key, value, null, null);
            size++;
            return;
        }
        put(root, key, value);
    }

    public void put(TreeNode<K, V> node, K key, V value) {
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            if (node.left == null) {
                node.left = new TreeNode<K, V>(key, value, null, null);
                size++;
            } else {
                put(node.left, key, value);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode<K, V>(key, value, null, null);
                size++;
            } else {
                put(node.right, key, value);
            }
        }
    }

    @Override
    public Set<K> keySet() { throw new UnsupportedOperationException(); }

    @Override
    public V remove(K key) { throw new UnsupportedOperationException(); }

    @Override
    public V remove(K key, V value) { throw new UnsupportedOperationException(); }

    @Override
    public Iterator<K> iterator() { throw new UnsupportedOperationException(); }
}
