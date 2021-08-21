package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* contains does not only check hashcode, but also checks
    * equals(). It's true only if equals() also returns true. */
//    @Test
//    public void testHashCodeCollide() {
//        Set<SimpleOomage> ooaSet = new HashSet<>();
//        SimpleOomage ooa1 = new SimpleOomage(0, 5, 0);
//        ooaSet.add(ooa1);
//        SimpleOomage ooa2 = new SimpleOomage(5, 0, 0);
//        ooaSet.contains(ooa2);
//        ooaSet.add(ooa2);
//        System.out.println(ooaSet.size());
//        ooaSet.contains(ooa2);
//        ooaSet.contains(ooa1);
//    }

    @Test
    public void testHashCodePerfect() {
        /* Test that hashcode is perfect
         */
        Set<Integer> ooaSet = new HashSet<>();
        for (int i = 0; i <= 255; i += 5) {
            for (int j = 0; j <= 255; j+= 5) {
                for (int k = 0; k <= 255; k += 5) {
                    int hash = new SimpleOomage(i, j, k).hashCode();
                    assertFalse(ooaSet.contains(hash));
                    ooaSet.add(hash);
                }
            }
        }

    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* TODO: Uncomment this test after you finish haveNiceHashCodeSpread in OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
