public class OffByN implements CharacterComparator{
    private int _N;

    public OffByN(int N) {
        _N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int t = x - y;
        return t == _N || t == - _N;
    }
}
