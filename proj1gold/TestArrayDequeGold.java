import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        ArrayDequeSolution<Integer> solutionDeque = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        String debuggingInfo = "";
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                debuggingInfo += String.format("\naddLast(%d)", i);
                solutionDeque.addLast(i);
                studentDeque.addLast(i);
            } else {
                debuggingInfo += String.format("\naddFirst(%d)", i);
                solutionDeque.addFirst(i);
                studentDeque.addFirst(i);
            }
        }

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                debuggingInfo += String.format("\nremoveLast(%d)", i);
                assertEquals(debuggingInfo, solutionDeque.removeLast(), studentDeque.removeLast());
            } else {
                debuggingInfo += String.format("\nremoveFirst(%d)", i);
                assertEquals(debuggingInfo, solutionDeque.removeFirst(), studentDeque.removeFirst());
            }

        }
    }
}
