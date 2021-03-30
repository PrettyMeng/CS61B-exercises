public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        x = Character.toLowerCase(x);
        y = Character.toLowerCase(y);
        if (y == x + 1 || x == y + 1) {
            return true;
        }
        return false;
    }
}
