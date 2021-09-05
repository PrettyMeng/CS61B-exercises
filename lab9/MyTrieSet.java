import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Set;

public class MyTrieSet implements TrieSet61B {

    private static final int R = 128;
    private Node root;

//    private static class DataIndexedCharMap<V> {
//        private V[] items;
//        public DataIndexedCharMap(int R) {
//            items = (V[]) new Object[R];
//        }
//        public void put(char c, V val) {
//            items[c] = val;
//        }
//        public V get(char c) {
//            return items[c];
//        }
//        public boolean containsKey(char c) {
//            if (items[c] == null) {
//                return false;
//            } else {
//                return true;
//            }
//        }
//    }

    private static class Node {
        private char ch;
        private boolean isKey;
        private HashMap map;
        private Node(char c, boolean b) {
            isKey = b;
            ch = c;
            map = new HashMap<Character, Node>(R);
        }
    }

    public MyTrieSet() {
        root = new Node('a', false);
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        if (root == null) {
            return false;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = (Node) curr.map.get(c);
        }
        return true;
    }


    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> x = new ArrayList();
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            curr = (Node) curr.map.get(c);
        }
        Set<Character> keySet = curr.map.keySet();
        for (char c : keySet) {
            colHelp(prefix + c, x, (Node) curr.map.get(c));
        }
        return x;
    }

    private void colHelp(String s, List<String> x, Node n) {
        if (n.isKey) {
            x.add(s);
        }
        Set<Character> keySet = n.map.keySet();
        for (Character c: keySet) {
            Node nextOne = (Node) n.map.get(c);
            colHelp(s + c, x, nextOne);
        }
    }

    /* from 61B website */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = (Node) curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}

