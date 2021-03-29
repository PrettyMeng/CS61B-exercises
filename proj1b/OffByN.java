public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int n) {
        N = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (y == x + N || x == y + N){
            return true;
        }
        return false;
    }
}
