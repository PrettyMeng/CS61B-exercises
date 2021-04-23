import org.junit.Test;
import static org.junit.Assert.*;

public class UnionFindTest {
    @Test
    public void testUnion() {
        UnionFind uf = new UnionFind(10);
        uf.union(1, 2);
        uf.union(6, 7);
        uf.union(2, 7);
        assertEquals(uf.find(7), 7);
        assertEquals(uf.find(2), 7);
        assertEquals(uf.find(1), 7);
        assertEquals(uf.find(6), 7);
        assertTrue(uf.connected(1, 6));
        assertTrue(uf.connected(2, 6));
        assertTrue(uf.connected(2, 7));
        assertEquals(uf.sizeOf(2), 4);
        assertEquals(uf.sizeOf(9), 1);

    }
}
