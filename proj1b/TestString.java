public class TestString {
    public static void main(String[] args) {
        char s = '%';
        char s2 = '&';
        s = Character.toLowerCase(s);
        s2 = Character.toLowerCase(s2);
        CharacterComparator offByOne = new OffByOne();
        System.out.println(offByOne.equalChars(s, s2));
    }
}
