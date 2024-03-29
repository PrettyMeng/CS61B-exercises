public class ArrayDequeTest {
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct, 
     * finally printing the results. 
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addLast("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        lld1.addLast("back");

//        lld1.removeFirst();
//        lld1.removeFirst();
//        lld1.removeLast();
//        lld1.removeLast();

        lld1.addLast("front");
        lld1.addLast("front");
        lld1.addLast("front");
        lld1.addLast("front");
        System.out.println(lld1.removeFirst());
        System.out.println(lld1.removeLast());
//        lld1.addFirst("front");
//        lld1.addFirst("front");

//        System.out.println(lld1.get(7));

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    }

    public static void removeLastTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addFirst(0);
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);
//        lld1.addLast(8);
//        lld1.addLast(9);
        int x1 = lld1.removeFirst();
        int x2 = lld1.removeFirst();
        int x3 = lld1.removeFirst();
        int x4 = lld1.removeFirst();
        int x5 = lld1.removeFirst();
        int x6 = lld1.removeFirst();
        int x7 = lld1.removeFirst();
        int x8 = lld1.removeFirst();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addLast(5);
        lld1.addLast(6);
        lld1.addLast(7);

//        System.out.println(lld1.removeFirst());
    }

    public static void nullTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addLast(5);
        lld1.addLast(6);
        lld1.addLast(7);
        lld1.addLast(8);

//        lld1.addLast(8);
//        lld1.addLast(9);
        int x1 = lld1.removeFirst();
        int x2 = lld1.removeLast();

//        System.out.println(lld1.removeFirst());
    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);

    }

    public static void getTest() {

        System.out.println("Running get test.");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        lld1.addFirst(0);
        lld1.get(0);
        lld1.removeLast();
        lld1.addFirst(3);
        lld1.removeFirst();
        lld1.addFirst(5);
        lld1.removeFirst();
        lld1.addFirst(7);
        lld1.addFirst(8);
        lld1.addLast(9);
        lld1.addLast(10);
        lld1.addFirst(11);
        lld1.get(0);
        lld1.get(2);
        lld1.addFirst(14);
        lld1.get(5);
        lld1.removeFirst();
        lld1.addLast(17);
        lld1.addLast(18);
        lld1.printDeque();
        lld1.addLast(19);
        lld1.addLast(20);
        lld1.addLast(21);
        lld1.removeLast();
        int x = lld1.get(5);


        System.out.println(lld1.get(0));
//        System.out.println(lld1.getRecursive(0));
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
//        addIsEmptySizeTest();
//        addRemoveTest();
        getTest();
//        removeLastTest();
//        nullTest();
    }
}
