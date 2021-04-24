import java.util.Arrays;

public class UnionFind {

    private int[] parents;
    private int[] sizes;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parents = new int[n];
        Arrays.fill(parents, -1);
        sizes = new int[n];
        Arrays.fill(sizes, 1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= parents.length) {
            throw new RuntimeException("invalid index:" + vertex);
        }
        return;
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return sizes[v1];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        if (parents[v1] != -1) {
            return parents[v1];
        } else {
            return -parents.length;
        }
    }

    private void updateSize(int newSize, int curNode) {
        while (curNode != -1) {
            sizes[curNode] = newSize;
            curNode = parents[curNode];
        }
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if (connected(v1, v2)) {
            return;
        }
        if (sizes[v1] <= sizes[v2]) {
            parents[find(v1)] = find(v2);
        }   else {
            parents[find(v2)] = find(v1);
        }
        int unionedSize = sizes[v1] + sizes[v2];
        // maintain the size
        // now the root should be the same
        assert find(v1) == find(v2);
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == find(v1)) {
                updateSize(unionedSize, i);
            }
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        if (parents[vertex] == -1) {
            return vertex;
        } else {
            return find(parents[vertex]);
        }
    }


}
