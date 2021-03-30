import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String s1 = "a";
        String s2 = "noon";
        assertTrue(palindrome.isPalindrome(s1));
        assertTrue(palindrome.isPalindrome(s2));
        String s3 = "hello";
        assertFalse(palindrome.isPalindrome(s3));
    }

    @Test
    public void testGeneralizedIsPalindrome() {
        OffByOne obo = new OffByOne();
        String s1 = "a";
        String s2 = "mopn";
        assertTrue(palindrome.isPalindrome(s1, obo));
        assertTrue(palindrome.isPalindrome(s2, obo));
        String s3 = "hello";
        assertFalse(palindrome.isPalindrome(s3, obo));
    }
}
