public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> deq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            deq.addLast(ch);
        }
        return deq;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deq = wordToDeque(word);
        while (deq.size() > 1) {
            char head = deq.removeFirst();
            char tail = deq.removeLast();
            if (head != tail) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deq = wordToDeque(word);
        while (deq.size() > 1) {
            char head = deq.removeFirst();
            char tail = deq.removeLast();
            if (!cc.equalChars(head, tail)) {
                return false;
            }
        }
        return true;
    }
}
